package com.leyou.user.service;

import com.leyou.common.utils.CodecUtils;
import com.leyou.common.utils.NumberUtils;
import com.leyou.pojo.User;
import com.leyou.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Guqiancheng
 * @date: 2020-05-27 0027 14:59
 * @description:
 */
@Service
public class UserService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    static final String KEY_PREFIX = "user:code:phone:";
    static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    public Boolean checkData(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(record) == 0;
    }


    public Boolean sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);
        try {
            // 发送短信
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);
            logger.info("phone:{},code:{}",phone,code);
            try {
                this.amqpTemplate.convertAndSend("leyou.sms.exchange", "sms.verify.code", msg);
            }catch (Exception e){
                logger.error("exchange 出错");
            }

            // 将code存入redis
            try {
                this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 60, TimeUnit.MINUTES);

            } catch (Exception e) {
               logger.error("redis出错");
            }finally {
                RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
            }
            return true;
        } catch (Exception e) {
            logger.error("发送短信失败。phone：{}， code：{}", phone, code);
            return false;
        }
    }

    /*用户注册*/
    public Boolean register( User user, String code) {
        // 校验短信验证码
        String cacheCode;
        try {
            cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        } finally {
            RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
        }
        if (!StringUtils.equals(code, cacheCode)) {
            return false;
        }

        // 生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        // 对密码加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        logger.info("用户密码：{}"+CodecUtils.md5Hex(user.getPassword(), salt));

        // 强制设置不能指定的参数为null
        user.setId(null);
        user.setCreated(new Date());
        // 添加到数据库
        boolean b = false;
        try {
            b = this.userMapper.insertSelective(user) == 1;
            logger.info("用户加入数据库成功");
        } catch (Exception e) {
            logger.error("用户加入数据库失败");
        }

        if(b){
            // 注册成功，删除redis中的记录
            try {
                this.redisTemplate.delete(KEY_PREFIX + user.getPhone());
            } finally {
                RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
            }
        }
        return b;
    }

    public User queryUser(String username, String password) {
        // 查询
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        // 校验用户名
        if (user == null) {
            return null;
        }
        // 校验密码
        if (!user.getPassword().equals(CodecUtils.md5Hex(password, user.getSalt()))) {
            return null;
        }
        // 用户名密码都正确
        return user;
    }
}

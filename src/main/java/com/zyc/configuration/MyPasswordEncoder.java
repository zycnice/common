package com.zyc.configuration;

import com.zyc.util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 进行数据库密码加密的处理，md5
 * @author zyc
 * @version 1.0
 */
public class MyPasswordEncoder implements PasswordEncoder {

    /**
     * 进行编码处理
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * 进行验证处理
     * @param rawPassword 用户传的密码
     * @param encodedPassword 数据库的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //利用工具类MD5Util
        return MD5Util.getSaltverifyMD5(rawPassword.toString(),encodedPassword);
    }
}

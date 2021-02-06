package com.example.volunteer.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.example.volunteer.constants.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class TokenUtil {

    private static Cache<String, byte[]> tokenCache = Caffeine.newBuilder()
            .expireAfterWrite(3, TimeUnit.DAYS)
            .initialCapacity(50)
            .maximumSize(500)
            .build();

    private static byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

    public String generateUserToken(long userId, HttpServletRequest request, HttpServletResponse response) {
        String remoteAddr = request.getRemoteAddr();
        long timeStamp = System.currentTimeMillis();

        byte[] encrypt = encrypt(userId + "_" + remoteAddr + "_" + timeStamp);
        String token = UUID.nameUUIDFromBytes(encrypt).toString().replace("-","");

        response.setHeader(CommonConstant.TOKEN, token);
        tokenCache.put(token, encrypt);

        return token;
    }

    public String getToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(CommonConstant.TOKEN);
        if (StringUtils.isBlank(token)) {
            return null;
        }

        // TODO jwt expire time
        byte[] encryptContent = tokenCache.getIfPresent(token);
        if (encryptContent == null) {
            return null;
        }
        String decryptContent = decrypt(encryptContent);
        String userId = decryptContent.split("_")[0];

        return userId;
    }

    private byte[] encrypt(String encryptContent) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        return aes.encrypt(encryptContent);
    }

    private String decrypt(byte[] token) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        return aes.decryptStr(token);
    }
}

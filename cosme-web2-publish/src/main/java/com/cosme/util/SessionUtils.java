package com.cosme.util;

import com.google.common.base.Splitter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-25 9:54
 **/

public class SessionUtils {

    public static final String APP_KEY = "USERNAME_KEY";
    public static final int EXPIRE_TIME_SECOND = 60;
    public static final String PUBLIC_KEY = "39D9625FABD93D46561871240D6624C5";

    public static String getUserId(HttpServletResponse response, HttpServletRequest request) {
        CookieUtils cookieUtils = new CookieUtils(request, response, request.getServerName());
        String cookieValue = cookieUtils.getCookieValue(APP_KEY);
        if (!StringUtils.hasText(cookieValue)) {
            return "";
        }
        String userIdAndTime = AESUtil.decrypt(cookieValue, PUBLIC_KEY);
        List<String> userIdAndTimes = Splitter.on("|").trimResults().splitToList(userIdAndTime);
        String userId = userIdAndTimes.get(0);
        int cookieExpireTime = Integer.valueOf(userIdAndTimes.get(1)) + EXPIRE_TIME_SECOND;
        if (System.currentTimeMillis() /1000 > cookieExpireTime) {
            return "";
        }
        return userId;
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String userId) {
        CookieUtils cookieUtils = new CookieUtils(request, response, request.getServerName());
        cookieUtils.setExpireTimeBySeconds(EXPIRE_TIME_SECOND);
        String encryptUserId = AESUtil.encrypt(userId + "|" + System.currentTimeMillis() / 1000, PUBLIC_KEY);
        cookieUtils.addCookie(APP_KEY, encryptUserId);
//        request.setAttribute("sessionId", userId);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils cookieUtils = new CookieUtils(request, response, request.getServerName());
        cookieUtils.delCookie(APP_KEY);
    }
}

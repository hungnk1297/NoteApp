package com.vmo.hungnk.noteapp.util;

import static com.vmo.hungnk.noteapp.constant.Constant.ModelAttributeConstant.CLEAR_SESSION_ATTRIBUTES;
import static com.vmo.hungnk.noteapp.constant.Constant.ModelAttributeConstant.LOGGED_IN_USER_ID;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MvcUtil {

    public static HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest().getSession(true);
    }

    public static boolean loggedIn() {
        HttpSession session = getSession();
        return session.getAttribute(LOGGED_IN_USER_ID) != null;
    }

    public static Long loggedInUserID() {
        HttpSession session = getSession();
        return (Long) session.getAttribute(LOGGED_IN_USER_ID);
    }

    public static void clearSession() {
        HttpSession session = getSession();
        for (String att : CLEAR_SESSION_ATTRIBUTES) {
            session.removeAttribute(att);
        }
    }
}

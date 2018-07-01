package cn.edu.jxnu.seckill.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}

package com.is.payments.spr;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Executions;

public class CookieUtil {
	public static void setCookie(String name, String value) {
		Cookie c = new Cookie(name, value);
		c.setMaxAge(60 * 60 * 24 * 365 * 10);
		((HttpServletResponse) Executions.getCurrent().getNativeResponse()).addCookie(c);
	}

	public static String getCookie(String name) {
		Cookie[] cookies = ((HttpServletRequest) Executions.getCurrent().getNativeRequest())
				.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}

package JpaUtils;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import Entity.User;

public class SessionUtils {
	public static User user = null;

	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

	public static Object get(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		return session.getAttribute(name);
	}

	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
	}

	public static boolean isLogin(HttpServletRequest request) {
		return get(request, "username") != null;
	}

	public static String getLoginedUsername(HttpServletRequest request) {
		Object username = get(request, "username");
		return username == null ? null : username.toString();
	}

	public static boolean isAdmin() {
		return isAdmin() && user.getAdmin() == true;
	}
}

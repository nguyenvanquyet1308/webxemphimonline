package UX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDAO;
import Entity.User;

@WebServlet({ "/editUsers/*", "/user/delete/*", "/user/update" })
public class editUser extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println(id);
		if (uri.contains("editUsers")) {
			editUs(req, resp);
		} else if (uri.contains("delete")) {
			// String uri = req.getRequestURI();
//			String id = uri.substring(uri.lastIndexOf("/") + 1);
			try {

				Udao.delete(id);
				req.setAttribute("message", "Xóa thành công");
				req.setAttribute("view", "/views/QLNguoiDung.jsp");
			} catch (Exception e) {

				System.out.println(e);
				req.setAttribute("view", "/views/QLNguoiDung.jsp");
				req.setAttribute("error", "Xóa thất bại");
			}

		} else if (uri.contains("update")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				Udao.update(user);
				req.setAttribute("message", "Cập nhật thành công");
				req.setAttribute("view", "/views/QLNguoiDung.jsp");
			} catch (Exception e) {
				req.setAttribute("error", "Cập nhật thất bại");
			}

		}
		req.setAttribute("userAll", Udao.selectAll());
		req.setAttribute("form", user);
		req.getRequestDispatcher("/views/TrangCTest.jsp").forward(req, resp);
	}

	UserDAO Udao = new UserDAO();
	User user = new User();

	private void editUs(HttpServletRequest req, HttpServletResponse resp) {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		user.setId(id);

		user = Udao.selectById(user.getId());
		req.setAttribute("view", "/views/QLNguoiDung.jsp");
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println(id);
		try {

			Udao.delete(id);
			req.setAttribute("message", "Xóa thành công");
		} catch (Exception e) {

			System.out.println(e);
			// req.getParameter(id);
			req.setAttribute("error", "Xóa thất bại");
		}

		req.setAttribute("view", "/views/QLNguoiDung.jsp");
	}
}

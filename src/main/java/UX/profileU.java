package UX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import Entity.User;

@WebServlet({"/change/pass","/change/passNew"})
public class profileU extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("/change/pass")) {
			saveProfile(req,resp);
			req.setAttribute("view", "/views/profile/profile.jsp");
		}else if (uri.contains("/change/passNew")) {
			changePass(req, resp);
			req.setAttribute("view", "/views/profile/profile.jsp");
		}
		req.getRequestDispatcher("/views/TrangCTest.jsp").forward(req, resp);
	}
	DAO.UserDAO dao = new DAO.UserDAO();
	private void saveProfile(HttpServletRequest req, HttpServletResponse resp) {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {			
			try {
				User usID = (User) req.getSession().getAttribute("us");
				DAO.UserDAO dao = new DAO.UserDAO();
				User entity = new User();				
				BeanUtils.populate(entity, req.getParameterMap());
				entity.setAdmin(usID.getAdmin());
				dao.update(entity);
				req.setAttribute("message", "Lưu thành công!");

				
			} catch (Exception e) {

				System.out.println(e);
				req.setAttribute("message", "Lỗi Lưu!");
			}
		}
	}
	
	private void changePass(HttpServletRequest req, HttpServletResponse resp) {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {			
			try {
				User usID = (User) req.getSession().getAttribute("us");
			
				DAO.UserDAO dao = new DAO.UserDAO();
				User entity = new User();				
				BeanUtils.populate(entity, req.getParameterMap());
				entity.setAdmin(usID.getAdmin());
				dao.update(entity);
				req.setAttribute("message", "Lưu thành công!");

				
			} catch (Exception e) {

				System.out.println(e);
				req.setAttribute("message", "Lỗi Lưu!");
			}
		}
	}
}

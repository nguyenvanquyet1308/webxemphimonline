package UX;

import java.io.IOException;


import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import DAO.*;
import Entity.User;
import JpaUtils.CookieUtils;

@WebServlet({ "/sign-in", "/sign-up", "/sign-out", "/forgetPassword", "/nhapMa", "/Datlaimatkhau" })
public class userLogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		if (uri.contains("sign-in")) {
			this.doSignIn(req, resp);
		} else if (uri.contains("sign-up")) {
			this.doSignUp(req, resp);
		} else if (uri.contains("sign-out")) {
			req.getSession().setAttribute("user", null);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else if (uri.contains("forgetPassword")) {
			forgetPassword(req, resp);
		} else if (uri.contains("nhapMa")) {
			NhapMa(req, resp);
		} else if (uri.contains("Datlaimatkhau")) {
			updatePassword(req, resp);
		}
	}

	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {

			String userName = req.getParameter("id");
			String pass = req.getParameter("password");
			UserDAO dao = new UserDAO();
			try {
				User us = dao.selectById(userName);
				if (us != null && us.getPassword().equals(pass)) {
					int hours = 30 * 24;
					req.setAttribute("message", "Thành Công");
					req.getSession().setAttribute("user", us);
					CookieUtils.add("id", userName, hours, resp);
					CookieUtils.add("password", pass, hours, resp);
					req.getRequestDispatcher("/user/TrangChu").forward(req, resp);
					return; // Chuyển hướng đã được thực hiện, không cần tiếp tục thực hiện lệnh dưới đây
				} else {
					req.setAttribute("error", "Sai Mật Khẩu");
				}
			} catch (Exception e) {
				req.setAttribute("error", "Sai tài khoản");
			}
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			// TODO: ĐĂNG KÝ
			try {
				User entity = new User();
				BeanUtils.populate(entity, req.getParameterMap());
				entity.setAdmin(false);
				UserDAO dao = new UserDAO();
				dao.insert(entity);
				req.setAttribute("message", "thành công!");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi đăng ký!");
			}
		}
		req.getRequestDispatcher("/views/Dk.jsp").forward(req, resp);
	}

	int emailbody = (int) (Math.random() * 90000) + 10000;
	private String emailTo = "nguyenquyet2017zz@fpt.edu.com";

	private void forgetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailTo = request.getParameter("to");
		if(emailTo !=null) {
			if (emailTo.equals("")) {
				request.setAttribute("error", "Không đúng định dạng gmail !!");
				request.getRequestDispatcher("/views/ForgetPassword.jsp").forward(request, response);
				return;
			}
		}
		
		final String username = "quyetnvpd07712@fpt.edu.vn";
		final String password = "h a c z n u h k p k m w x n a z";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		String emailSubject = "Mã gửi lấy lại mật khẩu";
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(emailSubject);
			message.setText(String.valueOf(emailbody));
			Transport.send(message);
			Checkgmail(request, response);
			System.out.println("gửi mail thành công");
			request.setAttribute("message", "Gửi mail thành công");
			request.getRequestDispatcher("/views/Nhapma.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("lỗi gửi mail" + e);
//			request.setAttribute("error", "Không đúng định dạng email !!");
			// TODO: handle exception

			request.getRequestDispatcher("/views/ForgetPassword.jsp").forward(request, response);
		}

	}

	private void NhapMa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String so = request.getParameter("so1");
		String so1 = request.getParameter("so2");
		String so2 = request.getParameter("so3");
		String so3 = request.getParameter("so4");
		String so4 = request.getParameter("so5");
		int conso = Integer.parseInt(so + so1 + so2 + so3 + so4);
		System.out.println(conso);

		if (emailbody == conso) {
			request.getRequestDispatcher("/views/datLaiMatKhau.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Mã không tồn tại !!");
		}

		request.getRequestDispatcher("/views/Nhapma.jsp").forward(request, response);
	}

	private void Checkgmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		emailTo = request.getParameter("to");
		UserDAO dao = new UserDAO();
		List<User> listUser = dao.selectAll();
		boolean found = false;
		for (User user : listUser) {
			if (user.getEmail().equalsIgnoreCase(emailTo)) {
				found = true;
				break; // Đã tìm thấy email tương ứng, không cần kiểm tra nữa
			}
		}
		if (!found) {
			// Không tìm thấy email tương ứng trong danh sách
			request.setAttribute("error", "Gmail không tồn tại !!");
			request.getRequestDispatcher("/views/ForgetPassword.jsp").forward(request, response);
		}
	}

	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			String password = request.getParameter("passwordnew");
			String EnterThePassword = request.getParameter("EnterThePassword");
			if (password.equalsIgnoreCase(EnterThePassword)) {
				UserDAO dao = new UserDAO();
				dao.UpdatePassword(password, emailTo);

				
				request.setAttribute("message", "Cập nhật mật khẩu Thành công!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			} else {
				request.setAttribute("error", "Password không trùng khớp !!");
			}
			request.getRequestDispatcher("/views/datLaiMatKhau.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	

}

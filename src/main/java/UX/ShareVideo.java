package UX;

import java.io.IOException;


import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;

import Entity.Email;
import Entity.Share;
import Entity.User;
import Entity.Video;
import JpaUtils.EmailUtils;
import JpaUtils.SessionUtils;

/**
 * Servlet implementation class ShareVideoServlet
 */
@WebServlet({ "/ShareVideo", "/share" })
public class ShareVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("login.jsp");
			return;
		}
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			response.sendRedirect("/user/TrangChu");
			return;
		}
		request.setAttribute("videoId", videoId);
	}

	@Override
	protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Share share = new Share();
			Video video = new Video();
			User user = new User();
			String id = SessionUtils.getLoginedUsername(request);
			String emailForm = "quyetnvpd07712@fpt.edu.vn";
			String passFrom = "h a c z n u h k p k m w x n a z";
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");
			System.out.println("ddax chayj toi day");
			request.setAttribute("videoId", videoId);
			if (videoId == null) {
				System.out.println("loxoiiiiiiiiiiiiiiiiiiiiiiiiii");
				request.setAttribute("error", "Video Id is null");
			} else {
				Email email = new Email();
				email.setFrom(emailForm);
				email.setFromPassword(passFrom);
				email.setTo(emailAddress);
				email.setSubject("Share Video | PhimMoi.com");
				String msg = "Chào bạn,  <br>Tôi muốn chia sẻ video này đến bạn, bạn hãy xem nhé<br/>"
						+ "Vui lòng nhấp vào liên kết <a href='https://youtu.be/K0cpoXlHFpQ"
						+ videoId + "'> Xem video </a><br/>quyetnvpd07712<br/>";
				email.setContent(msg);
				EmailUtils.send(email);
				BeanUtils.populate(share, request.getParameterMap());
				share.setShareDate(new Date());
				user.setId(id);
				video.setId(videoId);
				// dao.insert(share);
				request.setAttribute("message", "Video link has been sent!");
			}
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		request.getRequestDispatcher("/ChiTiet").forward(request, response);
	}

}

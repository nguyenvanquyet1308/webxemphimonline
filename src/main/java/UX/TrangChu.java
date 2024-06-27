package UX;

import java.io.IOException;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.FavoriteDAO;
import DAO.ReportDAO;
import DAO.UserDAO;
import DAO.VideoDAO;

import Entity.User;
import Entity.Video;
import JpaUtils.CookieUtils;




@WebServlet({ "/user/TrangChu", "/user/videoLike", "/user/historyWatch", "/user/change", "/user/profile",
		"/manager/TrangChu", "/manager/QLvideo", "/manager/QLnguoiDung", "/manager/thongKe", "/ChiTiet",
		"/selectVideoKey", "/views/Information/lienhe", "/views/Information/chungtoi", "/views/Information/hoidap" })

public class TrangChu extends HttpServlet {

	
	protected void Page(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("chạy tới đây 0");
	    String username = CookieUtils.get("username", request);
	    if (username == null) {
	        response.sendRedirect("/user/TrangChu");
	        return;
	    }
	    try {
	        VideoDAO dao = new VideoDAO();
	        request.setAttribute("numberPage", dao.getNumberPage());
	        String index = request.getParameter("page");
	        if (index == null) {
	            index = "1";
	        }
	        System.out.println("chạy tới đây 1");
	        int indexPage = Integer.parseInt(index);
	        request.setAttribute("indexPage", indexPage);
			List<Video> list = dao.FindAllVideoView((indexPage - 1) * 1, 12);

	        FavoriteDAO favdao = new FavoriteDAO();
	        List<Video> favlist = favdao.findAllByUserId(username);
	        request.setAttribute("favlist", favlist);
	        request.setAttribute("videosAll", list);
	        request.setAttribute("username", username);
	        System.out.println("đây cả rồi  : "+list);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", e.getMessage());
	    }
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		VideoDAO Vdao = new VideoDAO();
		UserDAO Udao = new UserDAO();
		User user = new User();
		if (uri.contains("/user/change")) {
			req.setAttribute("view", "/views/profile/profile.jsp");
		}
		if (uri.contains("/user/historyWatch")) {
			req.setAttribute("view", "/views/videolike.jsp");
		} else if (uri.contains("/user/profile")) {
//			String uriprofile = req.getRequestURI();
//			if (uriprofile.contains("/user/profile")) {
			req.getSession().setAttribute("us", req.getSession().getAttribute("user"));
			req.getRequestDispatcher("/change/pass").forward(req, resp);
//			} else if (uriprofile.contains("/change/passNew")) {
//				req.getRequestDispatcher("/change/passNew").forward(req, resp);
//			}

		} else if (uri.contains("/user/TrangChu")) {
			VideoDAO dao = new VideoDAO();
			Page(req, resp);
			if (uri.contains("/TrangChu")) {
				req.setAttribute("AllPhim", dao.selectAll());
			}
			req.setAttribute("views", "/views/slide.jsp");
			req.setAttribute("view", "/views/PHIM.jsp");

		} else if (uri.contains("/selectVideoKey")) {
			String title = req.getParameter("title");
			// req.setAttribute("VideoKey", dao.selecByKeyword(title));
			req.setAttribute("views", "/views/searchPhim.jsp");
		} else if (uri.contains("/ChiTiet")) {
			VideoDAO dao = new VideoDAO();
			boolean demso = false;
//			int dem = 0;					
//			if( == null) {
//				dem = 0;			
//			}	
//				dem++;
//				System.out.println(dem);
//				if(dem % 2 ==0) {
//					demso = true;
//				}else {
//					demso = false;
//				}
			req.setAttribute("AllPhim", dao.selectAll());
			req.setAttribute("actip", demso);
			req.setAttribute("view", "/views/ChiTiet.jsp");

		} else if (uri.contains("/user/videoLike")) {
			req.setAttribute("views", "/views/videolike.jsp");
		} else if (uri.contains("/manager/QLvideo")) {
			if (uri.contains("editVideo")) {
				req.getRequestDispatcher("/editVideo/*").forward(req, resp);
			}
			req.setAttribute("view", "/views/QLVideo.jsp");
		} else if (uri.contains("/manager/QLnguoiDung")) {
			if (uri.contains("editUsers")) {
				req.getRequestDispatcher("/editUsers/*").forward(req, resp);
			}
			req.setAttribute("view", "/views/QLNguoiDung.jsp");

		} else if (uri.contains("/manager/thongKe")) {
			// req.getRequestDispatcher("/Favorites").forward(req, resp);
			ReportDAO dao = new ReportDAO();
			VideoDAO vdao = new VideoDAO();
			String title1 = req.getParameter("CHÌA KHÓA 100 TỶ | OFFICIAL MV");
			if (title1 == null) {
				title1 = "CHÌA KHÓA 100 TỶ | OFFICIAL MV";
			}
			System.out.println("title1 " + title1);
			req.setAttribute("like", dao.SumLike());
			// req.setAttribute("user",dao.SelectByLikeTitle(title1) );
			req.setAttribute("titleVideo", vdao.selectAll());
			req.setAttribute("views", "/views/ThongKe.jsp");
		} else if (uri.contains("lienhe")) {
			req.setAttribute("viewInformation", "/views/Information/lienhe.jsp");
		} else if (uri.contains("hoidap")) {
			req.setAttribute("viewInformation", "/views/Information/hoidap.jsp");
		} else if (uri.contains("chungtoi")) {
			req.setAttribute("viewInformation", "/views/Information/chungtoi.jsp");
		}
		String username = CookieUtils.get("username", req);
		String password = CookieUtils.get("password", req);
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		req.setAttribute("videoALL", Vdao.selectAll());
		req.setAttribute("userAll", Udao.selectAll());
		req.getRequestDispatcher("/views/TrangCTest.jsp").forward(req, resp);
	}

//	private void saveProfile(HttpServletRequest req, HttpServletResponse resp) {
//		String method = req.getMethod();
//		if(method.equalsIgnoreCase("POST")) {
//								
//			try {
//				Entity.User entity = new Entity.User();		
//				BeanUtils.populate(entity, req.getParameterMap());				
//				dao.update(entity);
//				req.setAttribute("message", "Lưu thành công!");
//				
//				
//			} catch (Exception e) {
//				
//				System.out.println(e);
//				req.setAttribute("message", "Lỗi Lưu!");
//			}
//		}
//	}

	public boolean dem() {

		return false;

	}
//	private void doGetIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// Chia page
//	    VideoDAO daovideo = new VideoDAO();
//		List<Video> countVideo = daovideo.selectAll();
//		int maxPage = (int) Math.ceil(countVideo.size() / (double) 12);
//		req.setAttribute("maxPage", maxPage);
//		// For example:
//		// 10 video , muon chia 1 trang co 4 video >>> 10/4 thi 2.5 trang => 3 TRANG
//		List<Video> videos;
//		String pageNumber = req.getParameter("page");
//		if (pageNumber == null ||  Integer.valueOf(pageNumber) > maxPage ) {
//			//Return to Page 1 neu ma Page rong or tham so truyen cua page > maxPage trong List 
//			videos = daovideo.FindAllVideoView(1, 12);
//			req.setAttribute("currentPage", 1);
//		} else {
//			videos = daovideo.FindAllVideoView(Integer.valueOf(pageNumber), 12);
//			req.setAttribute("currentPage",Integer.valueOf(pageNumber));
//		}
//
//		req.setAttribute("videoShow", videos);
//		req.getRequestDispatcher("/user/TrangChu").forward(req, res);
//	}
}

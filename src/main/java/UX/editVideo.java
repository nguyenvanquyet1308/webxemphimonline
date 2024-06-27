package UX;

import java.io.File;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.VideoDAO;
import Entity.Video;


@MultipartConfig
@WebServlet({ "/editVideo/*", "/user/createVideo", "/user/deleteVideo/*", "/user/updateVideo" })
public class editVideo extends HttpServlet {


	Video vdeo = new Video();
	VideoDAO Vdao = new VideoDAO();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		String idvd = req.getParameter("id");
	
		if (uri.contains("editVideo")) {
			editVD(req, resp);
		} else if (uri.contains("createVideo")) {
			try {
				req.setCharacterEncoding("utf-8");
				resp.setCharacterEncoding("utf-8");
	      
				Part photo = req.getPart("cover");
				String fileName = photo.getSubmittedFileName();
				String uploadDir = "/hinhanh"; // Thư mục lưu trữ ảnh
				String savePath = req.getServletContext().getRealPath(uploadDir);
				String filePath = savePath + File.separator + fileName;
				photo.write(filePath);
				// Lưu đường dẫn ảnh vào cơ sở dữ liệu
				vdeo.setPoster(uploadDir + "/" + fileName);
				// Tiếp tục xử lý các thông tin khác của video
				
				BeanUtils.populate(vdeo, req.getParameterMap());
				// Chèn đối tượng video vào cơ sở dữ liệu
				Vdao.insert(vdeo);
				req.setAttribute("view", "/views/QLVideo.jsp");
				req.setAttribute("video", vdeo);
				req.setAttribute("message", "Tạo thành công");
			} catch (Exception e) {
				System.out.println(e);
				req.setAttribute("error", "Thêm thất bại");
			}
			

			req.setAttribute("view", "/views/QLVideo.jsp");
		}

		else if (uri.contains("deleteVideo")) {
			// String uri = req.getRequestURI();
//			String id = uri.substring(uri.lastIndexOf("/") + 1);
			try {
				Vdao.delete(idvd);
				req.setAttribute("message", "Xóa thành công");
				req.setAttribute("view", "/views/QLVideo.jsp");
			} catch (Exception e) {
				System.out.println(e);
				req.setAttribute("view", "/views/QLVideo.jsp");
				req.setAttribute("error", "Xóa thất bại");
			}

		} else if (uri.contains("updateVideo")) {
			try {
				req.setCharacterEncoding("utf-8");
				resp.setCharacterEncoding("utf-8");
				BeanUtils.populate(vdeo, req.getParameterMap());
				Vdao.update(vdeo);
				req.setAttribute("message", "Cập nhật thành công");
			} catch (Exception e) {
				System.out.println(e);
				req.setAttribute("error", "Cập nhật thất bại");
			}
			req.setAttribute("view", "/views/QLVideo.jsp");
		}
		req.setAttribute("videoALL", Vdao.selectAll());
		req.setAttribute("form", vdeo);
		req.getRequestDispatcher("/views/TrangCTest.jsp").forward(req, resp);
	}

	private void editVD(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		vdeo.setId(id);

		vdeo = Vdao.selectById(vdeo.getId());		
		req.setAttribute("view", "/views/QLVideo.jsp");
	}

//	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id = request.getParameter("videoId");
//		if (id == null) {
//			request.setAttribute("error", "Video ID is required!");
//			request.setAttribute("view", "/views/QLVideo.jsp");
//			return;
//		}
//		try {
//			VideoDAO dao = new VideoDAO();
//			Video video = dao.selectById(id);
//			request.setAttribute("video", video);
//			Vdao.selectAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("error", "Error: " + e.getMessage());
//		}
//		request.setAttribute("view", "/views/QLVideo.jsp");
//	}
	

}

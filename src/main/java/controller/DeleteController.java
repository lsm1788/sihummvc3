package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeleteServiceImpl;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String num = request.getParameter("num");
		String realSaveFileName = request.getParameter("realSaveFileName");
		
		//파일 삭제
		String saveFolder = "upload";

		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		File delFile = new File(realFolder, realSaveFileName);
		File delFile2 = new File(realFolder,"sm_"+realSaveFileName);
		if(delFile.exists()) {
			delFile.delete();
		}
		if(delFile2.exists()) {
			delFile2.delete();
		}
		//글 삭제
		DeleteServiceImpl service = new DeleteServiceImpl(); 
		service.delete(Integer.parseInt(num));
		
		//페이지 이동
		response.sendRedirect("list");
	}

}

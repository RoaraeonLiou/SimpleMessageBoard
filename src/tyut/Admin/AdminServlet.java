package tyut.Admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyut.myBean.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User admin = (User)session.getAttribute("admin");
		if(admin==null) {
			response.sendRedirect("/MessageBoard/admin/login");
			return;
		}else {
			String url = "/Admin/admin.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
			return;
		}
	}
}

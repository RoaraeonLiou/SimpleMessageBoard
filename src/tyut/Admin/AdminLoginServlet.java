package tyut.Admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tyut.myBean.User;
import tyut.myBean.UserDao;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User admin = new User();
		String message="用户名或密码错误";
		String url="/MessageBoard/admin";
		
		if(email!=null && password!=null) {
			admin.setEmail(email);
			admin.setPassword(password);
			UserDao userdao = new UserDao();
			if(!userdao.check(admin)) {
				url="/Admin/adminLogin.jsp";
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher(url).forward(request, response);
				return;
			}else{
				request.removeAttribute("message");
				userdao.getOtherInfo(admin);
				if(!admin.getType().equals("admin")) {
					message="您没有权限";
					url="/Admin/adminLogin.jsp";
					request.setAttribute("message", message);
					getServletContext().getRequestDispatcher(url).forward(request, response);
					return;
				}
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				session.setAttribute("user", admin);
				response.sendRedirect(url);
				return;
			}
		}else {
			url="/Admin/adminLogin.jsp";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/Admin/adminLogin.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}

package tyut.MessageBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import tyut.myBean.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/register.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取form提交信息
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_check = request.getParameter("password_check");
		
		// 创建必要元素
		String url = "/register.jsp";
		String message = "";
		UserDao userdao = new UserDao();
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		// 检查提交信息是否为空
		if(email==null || password==null || password_check==null) {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		//检查用户是否已经存在
		if(userdao.exist(user)) {
			message = "用户已存在!";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		// 检查两次输入密码是否相同
		if(!password.equals(password_check)) {
			message = "两次输入密码不同!";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		// 创建用户
		userdao.insertUser(user);
		url = "/MessageBoard/login";
		
		response.sendRedirect(url);
	}
}

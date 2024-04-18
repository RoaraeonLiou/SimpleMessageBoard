package tyut.MessageBoard;

import javax.servlet.http.*;

import com.mysql.cj.Session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import tyut.myBean.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String flag = request.getParameter("remember_me");
		boolean remember = flag.equals("Yes");
		User user = new User();
		String message="用户名或密码错误";
		String url="/";
		
		if(email!=null && password!=null) {
			System.out.println(flag);
			System.out.println(remember);
			if(remember) {
				// 选择记住我，添加Cookie，修改remeber Cookie
				Cookie email_cookie = new Cookie("email", email);
				Cookie password_cookie = new Cookie("password", password);
				Cookie remember_flag = new Cookie("remember","Yes");
				response.addCookie(email_cookie);
				response.addCookie(password_cookie);
				response.addCookie(remember_flag);
			}else {
				// 不选择记住我，删除Cookie，修改remeber Cookie
				Cookie[] cookies = request.getCookies();
				for(Cookie cookie:cookies) {
					if(cookie.getName().equals("email")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					if(cookie.getName().equals("password")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
				Cookie remember_flag = new Cookie("remember","No");
				response.addCookie(remember_flag);
			}
			
			user.setEmail(email);
			user.setPassword(password);
			UserDao userdao = new UserDao();
			if(!userdao.check(user)) {
				url="/login.jsp";
				request.setAttribute("message", message);
			}else{
				request.removeAttribute("message");
				userdao.getOtherInfo(user);
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}
		}else {
			url="/login.jsp";
			request.setAttribute("message", message);
		}
		System.out.println(url);
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/login.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}

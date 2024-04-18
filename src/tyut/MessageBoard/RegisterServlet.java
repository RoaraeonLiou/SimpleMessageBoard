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
		
		// ��ȡform�ύ��Ϣ
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_check = request.getParameter("password_check");
		
		// ������ҪԪ��
		String url = "/register.jsp";
		String message = "";
		UserDao userdao = new UserDao();
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		// ����ύ��Ϣ�Ƿ�Ϊ��
		if(email==null || password==null || password_check==null) {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		//����û��Ƿ��Ѿ�����
		if(userdao.exist(user)) {
			message = "�û��Ѵ���!";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		// ����������������Ƿ���ͬ
		if(!password.equals(password_check)) {
			message = "�����������벻ͬ!";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		// �����û�
		userdao.insertUser(user);
		url = "/MessageBoard/login";
		
		response.sendRedirect(url);
	}
}

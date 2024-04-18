package tyut.MessageBoard;

import java.io.IOException;

import tyut.myBean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/writeMessage")
public class WriteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�û�
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			response.sendRedirect("/MessageBoard/login");
			return;
		}
		
		// ��ȡ����
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// ��������
		Message message = new Message();
		message.setEmail(user.getEmail());
		message.setTitle(title);
		message.setContent(content);
		
		// �������
		MessageDao mdao = new MessageDao();
		mdao.insert(message);
		
		response.sendRedirect("/MessageBoard");
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			response.sendRedirect("/MessageBoard/login");
			return;
		}else {
			getServletContext().getRequestDispatcher("/write.jsp").forward(request, response);
		}
	}
}

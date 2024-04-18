package tyut.MessageBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tyut.myBean.*;
import tyut.myBean.*;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id_string = request.getParameter("id");
		int id = Integer.parseInt(id_string);
		MessageDao mdao = new MessageDao();
		String url = "";
		if(action.equals("update")){
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			mdao.updateById(id, title, content);
			url = "/MessageBoard/message?id="+id_string;
			response.sendRedirect(url);
		}else if(action.equals("delete")) {
			mdao.deleteById(id);
			url = "/MessageBoard/home";
			response.sendRedirect(url);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/message.jsp";
		// 获取用户
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		if(user==null) {
			response.sendRedirect("/MessageBoard/login");
			return;
		}

		// 获取消息
		String id_string = request.getParameter("id");
		System.out.println(id_string);
		if(id_string==null || id_string.equals("")) {
			url = "/404.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}else {
			boolean flag=true;
			for(int i = id_string.length();--i>=0;){   
				if (!Character.isDigit(id_string.charAt(i))){
					flag=false;
					break;
				}
			}
			if(flag==false) {
				url = "/404.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
		}
		int id = Integer.parseInt(id_string);
		MessageDao mdao = new MessageDao();
		Message message = mdao.getMessageById(id);
		
		// 返回页面
		request.setAttribute("message", message);
		System.out.println(message.getTitle());
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}

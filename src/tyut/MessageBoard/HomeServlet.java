package tyut.MessageBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tyut.myBean.Message;
import tyut.myBean.MessageDao;
import tyut.myBean.User;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�û�
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			response.sendRedirect("/MessageBoard/login");
			return;
		}
		
		String url="/home.jsp";
		String page_no = request.getParameter("page");
		int page, pre_page, next_page, last_page;
		
		// ��õ�ǰҳ��
		if(page_no==null||page_no=="") {
			page=0;
		}else {
			page = Integer.parseInt(page_no);
		}
		

		// ��ȡ��һҳ
		if(page == 0) {
			pre_page = -1;
		}else {
			pre_page = page-1;
		}
		
		// ��ȡ���һҳ
		MessageDao mdao = new MessageDao();
		last_page = mdao.getUserLastPageId(user);
		
		// ��ȡ��һҳ
		if(mdao.getUserMessage(page+1,user)!=null && page+1<=last_page){
			next_page = page+1;
		}else {
			next_page = -1;
		}

		// ��ȡ��Ϣ�б�
		ArrayList<Message> messages = mdao.getUserMessage(page,user);
		
		// ��ֵ����
		request.setAttribute("pre_page", pre_page);
		request.setAttribute("next_page", next_page);
		request.setAttribute("messages", messages);
		request.setAttribute("last_page", last_page);
		request.setAttribute("messages",messages);
		
		// ת������
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			response.sendRedirect("/MessageBoard/login");
			return;
		}else {
			doPost(request, response);
		}
	}
}

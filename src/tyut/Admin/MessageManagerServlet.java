package tyut.Admin;

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

@WebServlet("/admin/messageManager")
public class MessageManagerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id_string = request.getParameter("id");
		int id = Integer.parseInt(id_string);
		MessageDao mdao = new MessageDao();
		String url = "";
		if(action.equals("delete")) {
			mdao.deleteById(id);
			url = "/MessageBoard/admin/messageManager";
			response.sendRedirect(url);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�û�
		HttpSession session = request.getSession();
		User admin = (User)session.getAttribute("admin");
		if(admin==null) {
			response.sendRedirect("/MessageBoard/admin/login");
			return;
		}else {
			String url="/Admin/messageManager.jsp";
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
			last_page = mdao.getLastPageId();
			
			// ��ȡ��һҳ
			if(mdao.getMessage(page+1)!=null && page+1<=last_page){
				next_page = page+1;
			}else {
				next_page = -1;
			}

			// ��ȡ��Ϣ�б�
			ArrayList<Message> messages = mdao.getMessage(page);
			
			// ��ֵ����
			request.setAttribute("pre_page", pre_page);
			request.setAttribute("next_page", next_page);
			request.setAttribute("last_page", last_page);
			request.setAttribute("messages", messages);
			
			// ת������
			getServletContext().getRequestDispatcher(url).forward(request, response);
			return;
		}
	}
}

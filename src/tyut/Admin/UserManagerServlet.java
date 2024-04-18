package tyut.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tyut.myBean.*;

@WebServlet("/admin/userManager")
public class UserManagerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id_string = request.getParameter("id");
		int id = Integer.parseInt(id_string);
		UserDao udao = new UserDao();
		String url = "";
		if(action.equals("delete")) {
			udao.deletetUser(id);
		}else if(action.equals("upgrade")) {
			udao.updateUserType(id, "admin");
		}else if(action.equals("cancel")) {
			udao.updateUserType(id, "user");
		}
		url = "/MessageBoard/admin/userManager";
		response.sendRedirect(url);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User admin = (User)session.getAttribute("admin");
		if(admin==null) {
			response.sendRedirect("/MessageBoard/admin/login");
			return;
		}else {
			String url = "/Admin/userManager.jsp";
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
			UserDao udao = new UserDao();
			last_page = udao.getLastPageId();
			
			// ��ȡ��һҳ
			if(udao.getUsers(page+1)!=null && page+1<=last_page){
				next_page = page+1;
			}else {
				next_page = -1;
			}

			// ��ȡ��Ϣ�б�
			ArrayList<User> users = udao.getUsers(page);
			
			// ��ֵ����
			request.setAttribute("pre_page", pre_page);
			request.setAttribute("next_page", next_page);
			request.setAttribute("last_page", last_page);
			request.setAttribute("users", users);
			
			// ת������
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}
}

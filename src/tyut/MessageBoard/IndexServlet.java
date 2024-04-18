package tyut.MessageBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tyut.myBean.*;

@WebServlet("")
public class IndexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/index.jsp";
		String page_no = request.getParameter("page");
		int page, pre_page, next_page, last_page;
		
		// 获得当前页面
		if(page_no==null||page_no=="") {
			page=0;
		}else {
			page = Integer.parseInt(page_no);
		}
		

		// 获取上一页
		if(page == 0) {
			pre_page = -1;
		}else {
			pre_page = page-1;
		}
		
		// 获取最后一页
		MessageDao mdao = new MessageDao();
		last_page = mdao.getLastPageId();
		
		// 获取下一页
		if(mdao.getMessage(page+1)!=null && page+1<=last_page){
			next_page = page+1;
		}else {
			next_page = -1;
		}

		// 获取消息列表
		ArrayList<Message> messages = mdao.getMessage(page);
		
		// 赋值属性
		request.setAttribute("pre_page", pre_page);
		request.setAttribute("next_page", next_page);
		request.setAttribute("messages", messages);
		request.setAttribute("last_page", last_page);
		request.setAttribute("messages",messages);
		
		// 转发请求
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

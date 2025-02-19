package phonebook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc2up/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PhonebookService service=new PhonebookService();
	
    public MainController() {}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String page="phonebookmvc2up/list.jsp";
		System.out.println(uri);
		
		if(uri.equals("/mvc2up/list")) { //controller
 			List<PhonebookVO> list=service.findAll();//model
			request.setAttribute("list",list);   //model
			page="phonebookmvc2up/list.jsp"; //view
		}else if(uri.equals("/mvc2up/pagelist")) {
			String _currentPage=request.getParameter("currentPage");
			int currentPage=1;
			if(_currentPage!=null) {
				currentPage=Integer.parseInt(_currentPage);
			}
			//PageList pagelist=service.pageList();
			PhonebookService phonebookservice = new PhonebookService();
			Pagelist pagelist=phonebookservice.pageList(currentPage);
			request.setAttribute("pagelist",pagelist);//model
			page="phonebookmvc2up/pagelist.jsp"; //view
			
		}
		
		request.getRequestDispatcher("/WEB-INF/"+page)
		.forward(request, response); //WEB-INF 내부에 있는 파일 접근가능
	}

}

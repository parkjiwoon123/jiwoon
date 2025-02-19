package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


//@WebFilter("/*")// /*   
public class LoginFilter extends HttpFilter implements Filter {
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	System.out.println("login Filter");
    	HttpServletRequest httprequest=(HttpServletRequest)request;
		//HttpSessionȹ��
		HttpSession session=httprequest.getSession();
		
		/*
		if(session.getAttribute("id")==null) {
			request.getRequestDispatcher("/WEB-INF/login.jsp")
			 .forward(request,response); 
			return;
		}
		*/
		if(httprequest.getRequestURI().contains("/css/")) {
			chain.doFilter(request, response);
			return;
		}

		  if(session.getAttribute("id")==null &&
		  !(httprequest.getRequestURI().equals("/loginProc")) ) {
			  request.setAttribute("mainpage", "login/login.jsp");
		  request.getRequestDispatcher("/WEB-INF/index.jsp")
		  .forward(request,response); 
		  
		  return; 
		  }
		 
			
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("new filter2");
	}
	
	public LoginFilter() {
		super();
		System.out.println("new filter1");
	    
    }

	public void destroy() {
	
	}

}

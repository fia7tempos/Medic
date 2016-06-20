package user_auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.UserVO;


public class AuthFilter implements Filter {

    public AuthFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)srequest;
		HttpServletResponse response = (HttpServletResponse)sresponse;
		
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		
		if(userInfo == null){
			request.setAttribute("msg", "먼저 로그인을 하셔야 합니다.");
			request.setAttribute("url", "../user/Login");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/result.jsp");
			dispatcher.forward(request, response);
			return;
		} 
		
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}

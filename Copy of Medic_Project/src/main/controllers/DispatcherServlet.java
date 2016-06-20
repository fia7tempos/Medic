package main.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private static Map<String, AbstractController> controllerMap = new HashMap<String, AbstractController>();
	
	@Override
	public void init() throws ServletException {
		InputStream is = null;
		Properties pr = new Properties();
		String filePath = this.getClass().getResource("").getPath();	//���� Ŭ����(this)�� Ŭ���������� ���ҽ������� �������� �н��� �о�´�.
		
		try {
			is = new FileInputStream(filePath + "dispatcher-servlet.properties");
			pr.load(is);
			
			for(Object object : pr.keySet()) {
				String key = (String) object;
				String classPathValue = pr.getProperty(key).trim();				
				try {
					Class className = Class.forName(classPathValue);					
					controllerMap.put(key, (AbstractController)className.newInstance()); //new�� ���� �ν��Ͻ� ������
					logger.info("loaded : " + key);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("failure : " + key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null) try{is.close();} catch(Exception e){}
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//logger.info("RequestURI : " + request.getRequestURI());	//����ȣ��Ʈ ������ �ּҸ� �����´�. http://localhost/m2board/article/*
		//logger.info("contextPath : " + request.getContextPath());	//���� ������Ʈ �̸��� �����´�.
		//logger.info("action : " + request.getRequestURI().trim().substring(request.getContextPath().length()));
		String action = request.getRequestURI().substring(request.getContextPath().length());

		AbstractController controller = null;
		ModelAndView mav = null;
		controller = controllerMap.get(action);
		if(controller == null) {
			logger.info("url에 해당하는 ActionContoroller가 없습니다.");
			return;
		}
		
		mav = controller.handleRequestInternal(request, response);
		
		if(mav != null) {
			String viewName = mav.getViewName();
			if(viewName.startsWith("redirect:")){
				response.sendRedirect(viewName.substring(9));
				return;
			}
			
			for(String key : mav.getModel().keySet()) {
				request.setAttribute(key, mav.getModel().get(key));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
			dispatcher.forward(request, response);
		} else {
			logger.info("DispatcherServlet에서 길을 잃었다네");
		}		
	}
}

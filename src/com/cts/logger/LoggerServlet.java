package com.cts.logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class LoggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOG=Logger.getLogger(LoggerServlet.class);
    public LoggerServlet() {
        super();
 
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();
		String realPath=context.getRealPath("/");
		String fileName=realPath+"WEB-INF\\log4j.properties";
		PropertyConfigurator.configure(fileName);
		LOG.info("Logger Initialized");
	}
	
	
}

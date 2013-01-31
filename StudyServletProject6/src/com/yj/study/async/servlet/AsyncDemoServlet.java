package com.yj.study.async.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yj.study.thread.MyExecutor;

public class AsyncDemoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AsyncDemoServlet.class);
       
    public AsyncDemoServlet() {
        super();
        log.info("****AsyncDemoServlet构造函数被调用*****");
    }

	public void init(ServletConfig config) throws ServletException {
		log.info("****AsyncDemoServlet.init(ServletConfig config)******");
	}
	public void destroy() {
		log.info("****AsyncDemoServlet.destroy()******");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info("****AsyncDemoServlet.doGet(request,response)******");
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("进入Servlet的时间：" + new Date() + ".");
		log.info("*****进入Servlet的时间："+ new Date().getTime()+"****");
		out.flush();
		
		//接下来，启动异步调用业务处理逻辑线程,并由其负责输出响应，主线程(以下两行代码外的代码)退出  ---begin
		//下面的MyExecutor线程与业务逻辑相关
		AsyncContext act = request.startAsync();
		new Thread(new MyExecutor(act)).start();
		//启动异步调用业务处理逻辑线程,并由其负责输出响应，主线程(以下两行代码外的代码)退出  ---end
		
		out.println("结束Servlet的时间：" + new Date() + ".");
		log.info("*****结束Servlet的时间："+ new Date().getTime()+"*****");
        out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info("****AsyncDemoServlet.doPost(request,response)******");
		doGet(request,response);
	}

}

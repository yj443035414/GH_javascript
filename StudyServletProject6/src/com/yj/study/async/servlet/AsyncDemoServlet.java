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
        log.info("****AsyncDemoServlet���캯��������*****");
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
		out.println("����Servlet��ʱ�䣺" + new Date() + ".");
		log.info("*****����Servlet��ʱ�䣺"+ new Date().getTime()+"****");
		out.flush();
		
		//�������������첽����ҵ�����߼��߳�,�����为�������Ӧ�����߳�(�������д�����Ĵ���)�˳�  ---begin
		//�����MyExecutor�߳���ҵ���߼����
		AsyncContext act = request.startAsync();
		new Thread(new MyExecutor(act)).start();
		//�����첽����ҵ�����߼��߳�,�����为�������Ӧ�����߳�(�������д�����Ĵ���)�˳�  ---end
		
		out.println("����Servlet��ʱ�䣺" + new Date() + ".");
		log.info("*****����Servlet��ʱ�䣺"+ new Date().getTime()+"*****");
        out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info("****AsyncDemoServlet.doPost(request,response)******");
		doGet(request,response);
	}

}

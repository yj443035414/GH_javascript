package com.yj.study.thread;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;

import org.apache.log4j.Logger;

public class MyExecutor implements Runnable {

	private static final Logger log = Logger.getLogger(MyExecutor.class);
	private AsyncContext ctx = null;
	
    public MyExecutor(AsyncContext ctx){
        this.ctx = ctx;
    }

    public void run(){
        try {
            //�ȴ�ʮ���ӣ���ģ��ҵ�񷽷���ִ��
            Thread.sleep(10000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("ҵ������ϵ�ʱ�䣺" + new Date() + ".");
            log.info("*****ҵ������ϵ�ʱ��:"+ new Date().getTime());
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

 

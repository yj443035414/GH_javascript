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
            //等待十秒钟，以模拟业务方法的执行
            Thread.sleep(10000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("业务处理完毕的时间：" + new Date() + ".");
            log.info("*****业务处理完毕的时间:"+ new Date().getTime());
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

 

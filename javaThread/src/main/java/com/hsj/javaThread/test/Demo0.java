package com.hsj.javaThread.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Demo0.java的源码
class MyThread0 extends Thread {
	
 private final static Logger LOGGER = LoggerFactory.getLogger(MyThread0.class);
 
 public MyThread0(String name) {
     super(name);
 }

 @Override
 public void run() {
     try {  
         int i=0;
         while (!isInterrupted()) {
             Thread.sleep(100); // 休眠100ms
             i++;
             LOGGER.info("当前的线程的名字=》{}，当前的状态=》{} loop ",Thread.currentThread().getName(),this.getState());
         }
     } catch (InterruptedException e) {  
         LOGGER.info("当前的线程的名字=》{}，当前的状态=》{} catch InterruptedException. ",Thread.currentThread().getName(),this.getState());
     }
 }
}

/**
 * 测试Interrupted
 * 正常退出线程，try在while外面！
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年11月13日 下午3:30:22
 */
public class Demo0 {
 private final static Logger LOGGER_MAIN = LoggerFactory.getLogger(Demo0.class);
 public static void main(String[] args) {  
     try {  
         Thread t1 = new MyThread0("t1");  // 新建“线程t1”
         LOGGER_MAIN.info("线程=》{} ,当前的状态{} is new !",t1.getName(),t1.getState());

         t1.start();                      // 启动“线程t1”
         LOGGER_MAIN.info("线程=》{} ,当前的状态{} is started !",t1.getName(),t1.getState());

         // 主线程休眠300ms，然后主线程给t1发“中断”指令。
         Thread.sleep(300);
         t1.interrupt();
         LOGGER_MAIN.info("线程=》{} ,当前的状态{} !",t1.getName(),t1.getState());

         // 主线程休眠300ms，然后查看t1的状态。
         Thread.sleep(300);
         LOGGER_MAIN.info("线程=》{} ,当前的状态{} is interrupted now!",t1.getName(),t1.getState());
     } catch (InterruptedException e) {  
         e.printStackTrace();
     }
 } 
}
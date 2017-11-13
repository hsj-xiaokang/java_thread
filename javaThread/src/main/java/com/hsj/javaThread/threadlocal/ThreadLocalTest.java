package com.hsj.javaThread.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Treadlocal测试fork from：https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadLocal.html
 * 
 * 用处：保存线程的独立变量。对一个线程类（继承自Thread)
                     当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
                     所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
                     常用于用户登录控制，如记录session信息。

       实现：每个Thread都持有一个TreadLocalMap类型的变量
                 （该类是一个轻量级的Map，功能与map一样，区别是桶里放的是entry而不是entry的链表。功能还是一个map。）
                  以本身为key，以目标为value。
                  主要方法是get()和set(T a)，set之后在map里维护一个threadLocal -> a，get时将a返回。ThreadLocal是一个特殊的容器。
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年11月13日 下午4:01:28
 */
public class ThreadLocalTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ThreadLocalTest.class);

    // Thread local variable containing each thread's ID
    public static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override 
            protected Integer initialValue() {
            	  //每个线程的初始化值
            	  return 1;
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
    
    public static void main(String[] args) {
    	new MyThread_main_1("MyThread_main_1",threadId).start();
    	new MyThread_main_2("MyThread_main_2",threadId).start();
    	new MyThread_main_3("MyThread_main_3",threadId).start();
    	LOGGER.info("main thread [ThreadLocal<Integer>] value is = {}",get());
	}
}

class MyThread_main_1 extends Thread {
	
	 private final static Logger LOGGER = LoggerFactory.getLogger(MyThread_main_1.class);
	 
	 private ThreadLocal<Integer> threadId ;
	 
	 public MyThread_main_1(String name,ThreadLocal<Integer> threadId) {
	     super(name);
	     this.threadId = threadId;
	 }

	 @Override
	 public void run() {
		 threadId.set(threadId.get() + 1000);
		 LOGGER.info("{} thread [ThreadLocal<Integer>] value is = {}",this.currentThread().getName(),this.threadId.get());
	 }
	}


class MyThread_main_2 extends Thread {
	
	 private final static Logger LOGGER = LoggerFactory.getLogger(MyThread_main_2.class);
	 
     private ThreadLocal<Integer> threadId ;
	 
	 public MyThread_main_2(String name,ThreadLocal<Integer> threadId) {
	     super(name);
	     this.threadId = threadId;
	 }

	 @Override
	 public void run() {
		 threadId.set(threadId.get() + 1200);
		 LOGGER.info("{} thread [ThreadLocal<Integer>] value is = {}",this.currentThread().getName(),this.threadId.get());
	 }
	}

class MyThread_main_3 extends Thread {
	
	 private final static Logger LOGGER = LoggerFactory.getLogger(MyThread_main_3.class);
	 
    private ThreadLocal<Integer> threadId ;
	 
	 public MyThread_main_3(String name,ThreadLocal<Integer> threadId) {
	     super(name);
	     this.threadId = threadId;
	 }

	 @Override
	 public void run() {
		 LOGGER.info("{} thread [ThreadLocal<Integer>] value is = {}",this.currentThread().getName(),this.threadId.get());
	 }
	}

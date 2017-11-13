package com.hsj.javaThread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * entry
 * 博客：   https://www.cnblogs.com/wxd0108/p/5479442.html
 *      https://www.cnblogs.com/skywang12345/p/3479949.html
 * 
 * interrupt():不要以为它是中断某个线程！它只是线线程发送一个中断信号，让线程在无限等待时（如死锁时）能抛出抛出，从而结束线程，但是如果你吃掉了这个异常，那么这个线程还是不会中断的！
 * 
 * 
 * 最后谈谈 interrupted() 和 isInterrupted()。
   interrupted() 和 isInterrupted()都能够用于检测对象的“中断标记”。
         区别是，interrupted()除了返回中断标记之外，它还会清除中断标记(即将中断标记设为false)；而isInterrupted()仅仅返回中断标记。
 * 
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年11月13日 下午2:15:18
 */
@SpringBootApplication
public class App{
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

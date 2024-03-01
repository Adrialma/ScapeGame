package com.adrialma.model;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		LocalTime t1 = LocalTime.now();
		try {
			TimeUnit.SECONDS.sleep(100);
			LocalTime t2 = LocalTime.now();
			int  t3;
			t3 = t2.getSecond() - t1.getSecond();
			
			System.out.println(t1);
			System.out.println(t2);
			System.out.println(t3);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

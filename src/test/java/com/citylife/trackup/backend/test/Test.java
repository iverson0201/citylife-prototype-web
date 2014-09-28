package com.citylife.trackup.backend.test;

import junit.framework.TestCase;
/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月26日 下午5:17:42
 */
public class Test extends TestCase {
	
	 public Test(String name){  
		 super(name);  
	 } 

	public void aa(){
		System.out.println("aaaaaaa");
	}
	
    public static void main (String[] args) {  
        junit.textui.TestRunner.run (new Test("firstTest"));  
    
      }  
}

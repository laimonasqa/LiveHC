//package test.java;

import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import javax.swing.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import java.net.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.runner.RunWith;
import com.googlecode.jeeunit.concurrent.Concurrent;
import com.googlecode.jeeunit.concurrent.ConcurrentRunner;

//AUTOMATION APPLICATION
//VERSION 0.023




//import tests;
//import juega1;


//@RunWith(ConcurrentRunner.class)
//@Concurrent(threads = 2)

public class main2Test {
	
	//AUTOMATION APPLICATION
	//VERSION 0.021
	
	
	
	
	//import tests;
	//import juega1;
	
	
	//@RunWith(ConcurrentRunner.class)
	//@Concurrent(threads = 2)
	
	String browser2=String.valueOf(System.getProperty("browser2"));
	String browser3=String.valueOf(System.getProperty("browser3"));

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public int retry=0;
	public int retry2=0;
	public int retry3=0;
	public int i=0;
	public int o=0;
	
	/*private static final int MAX_LOOP = 5;

    private void runMethod(int methodNum) throws InterruptedException {
        for (int i = 0; i < MAX_LOOP; i++) {
            System.out.println(String.format("method%d: %d", methodNum, i));
            Thread.sleep(500);
        }
    }

    @Test
    public void method1() throws InterruptedException {
        runMethod(1);
    }

    @Test
    public void method2() throws InterruptedException {
        runMethod(2);
    }*/
	
	@Test
		
	public void main() throws Exception {
		// TODO Auto-generated method stub

		//for (int i=0; i<args.length; i++) System.out.println(args[i]);
		
		
		String args=String.valueOf(System.getProperty("totest"));
		String url=String.valueOf(System.getProperty("url"));
		String xpath=String.valueOf(System.getProperty("xpath"));
		
		//remember another system property called batch
		
		
		//System.out.println(args.length);
		tests test = new tests();
		tests2 test3 = new tests2();
		tests3 test4 = new tests3();
		
		
		
		
		if(!args.isEmpty()){
			
			
				String[] options={args,url,xpath};
				//System.out.println(args+"     "+url+"      "+xpath);
				//test.setUp(options);
				
				if(!browser2.equals("null")){
					
					test3.setUp(options);
					
				}
				/*
				if(!browser3.equals("null")){
					
					test4.setUp(options);
					
				}*/
					
				
			
				
			}
		
		
		
		//test.setUp(args);
		//test.setUp(options);
		//test2.setUp();
		//call to tests class and run it.
	}
		
	 @After
	 public void tearDown() throws Exception {
		 	
		 if(!browser2.equals("null")){
		 	//tests test = new tests();	
		 	tests2 test2 = new tests2();
		 	//tests3 test3 = new tests3();
		 	
		 	
		 	//if(test3.started==0){test3.overall="FAILED";}
		 	//if(test3.started!=test3.finished){test3.overall="FAILED";}
		 	
		 	if(test2.started==0){test2.overall="FAILED";}
		 	if(test2.started!=test2.finished){test2.overall="FAILED";}
		 	
		 	//if(test.started==0){test.overall="FAILED";}
		 	//if(test.started!=test.finished){test.overall="FAILED";}
		 	
		 	
		 	String buildurl=String.valueOf(System.getProperty("buildurl"));
		 	
		 	
			File file2=new File("target/reports/result.html");
			//file.delete();
			
			
			FileWriter write2 = new FileWriter(file2,true);
			String color=(char)34+"RED"+(char)34;
			/*
		 	if(test.overall.equals("FAILED")&&retry==0){
	    	
		 		File file = new File("target/reports/"+test.timesta+"it1brw1.html");
		 		FileWriter write = new FileWriter(file,true);
		 		
				System.out.println("Generating Reports");
			    System.out.println("-----------------------------------");
				
		    	write2.write("<p><p><font color="+(char)34+"red"+(char)34+">FIRST ITERATION FAILED</font><p><p>USERNAME USED===>" + test.genlogin+"<p><p>");
		    	
		    	
		    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
		    	
		    	write.write(test.result);
		    	write2.write(test.result2);
		    	
		    	write2.write("</tr></table></font>");
		 		
		    	if(!buildurl.equals("null")){
		    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test.timesta + "it1brw1.html"+(char)34+"> LINK </a> for a full report for First Iteration<p>");
		    	}else{
		    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test.timesta + "it1brw1.html"+(char)34+"> LINK </a> for a full report for First Iteration<p>");
		    	}
		    	
		    	write2.write("--------------------------------------------");
		    	write2.write("--------------------------------------------");
		    	write2.write("--------------------------------------------");
		    	
		    	write.close();
		    	
		 		System.out.println("--------------------------------------");
		 		System.out.println("  Starting 2nd Iteration on failure");
		 		System.out.println("--------------------------------------");
		 		test.overall="PASSED";
		 		test.result="";
		 		test.result2="";
		 		test.started=0;
		 		test.finished=0;
		 		retry=1;
		 		String[] options=new String[1];
				options[0]="nothing";
				//test.driver.close();
				test.setUp(options);
		 		
		 	}
		 	
		 	String color="";
	    	
	    	if(test.overall.equals("FAILED")){
	    		
	    		color=(char)34+"RED"+(char)34;
	    		write2.write("<p><p><font color="+(char)34+"red"+(char)34+">SECOND ITERATION FAILED</font><p><p>USERNAME USED===>" + test.genlogin+"<p><p>");
	    	
	    	}else{
	    		
	    		if(retry>0){	    		
	    			write2.write("<p><p><font color="+(char)34+"black"+(char)34+">SECOND ITERATION PASSED</font><p><p>USERNAME USED===>" + test.genlogin+"<p><p>");
	    		}else{
	    			write2.write("<p><p><font color="+(char)34+"black"+(char)34+">FIRST ITERATION PASSED</font><p><p>USERNAME USED===>" + test.genlogin+"<p><p>");
	    		}
	    			
	    		
	    	}
	    	
	    	
	    	
	    	
	    	//else{
	    		//color=(char)34+"BLACK"+(char)34;
	    	//}
		 
	    	
		 	
		 	
		 
		 	File folder=new File("target/reports");
			File folder2=new File("target/screenshots");
			
			if(!folder.exists()){folder.mkdirs();}
			if(!folder2.exists()){folder2.mkdirs();}
			
			File file3 = new File("target/reports/"+test.timesta+"brw1.html");
			FileWriter write3 = new FileWriter(file3,true);
			
			System.out.println("Generating Reports");
		    System.out.println("-----------------------------------");
			
	    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
	    	
	    	write3.write(test.result);
	    	write2.write(test.result2);
	    	
	    	write2.write("</tr></table></font>");
	    	
	    	
	    	write2.write("<p> OVERALL STATUS=<font color="+ color+">"+test.overall +"</font> <p>");
	    	
	    	if(!buildurl.equals("null")){
	    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test.timesta + "brw1.html"+(char)34+"> LINK </a> for a full report<p>");
	    		write2.write("<p></p><p></p><p>Console Output can be found <a href="+(char)34+ buildurl+"console"+(char)34+"> HERE </a></p>");
	    	}else{
	    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test.timesta + "brw1.html"+(char)34+"> LINK </a> for a full report<p>");
	    	}
	    	
	    	write2.write("--------------------------------------------");
	    	write2.write("--------------------------------------------");
	    	write2.write("--------------------------------------------");
	    	
			write3.close();
			*/
	    	
			
				
				if(test2.overall.equals("FAILED")&&retry2==0){
			    	
			 		File file = new File("target/reports/"+test2.timesta2+"it1brw2.html");
			 		FileWriter write = new FileWriter(file,true);
			 		
					System.out.println("Generating Reports");
				    System.out.println("-----------------------------------");
					
			    	write2.write("<p><p><font color="+(char)34+"red"+(char)34+">FIRST ITERATION FAILED</font><p><p>USERNAME USED===>" + test2.genlogin+"<p><p>");
			    	
			    	color=(char)34+"RED"+(char)34;
			    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
			    	
			    	write.write(test2.result3);
			    	write2.write(test2.result4);
			    	
			    	write2.write("</tr></table></font>");
			 		
			    	if(!buildurl.equals("null")){
			    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test2.timesta2 + "it1brw2.html"+(char)34+"> LINK </a> for a full report for First Iteration<p>");
			    	}else{
			    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test2.timesta2 + "it1brw2.html"+(char)34+"> LINK </a> for a full report for First Iteration<p>");
			    	}
			    	
			    	write2.write("--------------------------------------------");
			    	write2.write("--------------------------------------------");
			    	write2.write("--------------------------------------------");
			    	write.close();
			    	
			 		System.out.println("--------------------------------------");
			 		System.out.println("  Starting 2nd Iteration on failure");
			 		System.out.println("--------------------------------------");
			 		test2.overall="PASSED";
			 		test2.result3="";
			 		test2.result4="";
			 		test2.started=0;
			 		test2.finished=0;
			 		retry2=1;
			 		String[] options=new String[1];
					options[0]="nothing";
					//test.driver.close();
					test2.setUp(options);
			 		
			 	}
			 	
			 	color="";
		    	
			 	if(test2.overall.equals("FAILED")){
		    		
		    		color=(char)34+"RED"+(char)34;
		    		write2.write("<p><p><font color="+(char)34+"red"+(char)34+">SECOND ITERATION FAILED</font><p><p>USERNAME USED===>" + test2.genlogin+"<p><p>");
		    		
			 	}else{
			 		
			 		if(retry2>0){	    		
		    			write2.write("<p><p><font color="+(char)34+"black"+(char)34+">SECOND ITERATION PASSED</font><p><p>USERNAME USED===>" + test2.genlogin+"<p><p>");
		    		}else{
		    			write2.write("<p><p><font color="+(char)34+"black"+(char)34+">FIRST ITERATION PASSED</font><p><p>USERNAME USED===>" + test2.genlogin+"<p><p>");
		    		}
			 	}
		    	
			 	
			 	
			 
			 	File file4 = new File("target/reports/"+test2.timesta2+"brw2.html");
				FileWriter write4 = new FileWriter(file4,true);
				
				System.out.println("Generating Reports");
			    System.out.println("-----------------------------------");
				
		    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
		    	
		    	write4.write(test2.result3);
		    	write2.write(test2.result4);
		    	
		    	write2.write("</tr></table></font>");
		    	
		    	
		    	write2.write("<p> OVERALL STATUS=<font color="+ color+">"+test2.overall +"</font> <p>");
		    	
		    	if(!buildurl.equals("null")){
		    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test2.timesta2 + "brw2.html"+(char)34+"> LINK </a> for a full report<p>");
		    		write2.write("<p></p><p></p><p>Console Output can be found <a href="+(char)34+ buildurl+"console"+(char)34+"> HERE </a></p>");
		    	}else{
		    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test2.timesta2 + "brw2.html"+(char)34+"> LINK </a> for a full report<p>");
		    	}
				
		    	write2.write("--------------------------------------------");
		    	write2.write("--------------------------------------------");
		    	write2.write("--------------------------------------------");
		    	write4.close();
				
				
		//	}
	    		
			/*
			if(!browser3.equals("null")){
				
				if(test3.overall.equals("FAILED")&&retry3==0){
			    	
			 		File file = new File("target/reports/"+test3.timesta3+"it1brw3.html");
			 		FileWriter write = new FileWriter(file,true);
			 		
					System.out.println("Generating Reports");
				    System.out.println("-----------------------------------");
					
			    	write2.write("<p><p><font color="+(char)34+"red"+(char)34+">FIRST ITERATION FAILED</font><p><p>USERNAME USED===>" + test2.genlogin+"<p><p>");
			    	
			    	color=(char)34+"RED"+(char)34;
			    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
			    	
			    	write.write(test3.result5);
			    	write2.write(test3.result6);
			    	
			    	write2.write("</tr></table></font>");
			 		
			    	if(!buildurl.equals("null")){
			    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test3.timesta3 + "it1brw3.html"+(char)34+"> LINK </a> for a full report for First Iteration<p>");
			    	}else{
			    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test3.timesta3 + "it1brw3.html"+(char)34+"> LINK </a> for a full report for First Iteration<p>");
			    	}
			    	
			    	write2.write("--------------------------------------------");
			    	write2.write("--------------------------------------------");
			    	write2.write("--------------------------------------------");
			    	
			    	write.close();
			    	
			 		System.out.println("--------------------------------------");
			 		System.out.println("  Starting 2nd Iteration on failure");
			 		System.out.println("--------------------------------------");
			 		test3.overall="PASSED";
			 		test3.result5="";
			 		test3.result6="";
			 		test3.started=0;
			 		test3.finished=0;
			 		retry3=1;
			 		String[] options=new String[1];
					options[0]="nothing";
					//test.driver.close();
					test3.setUp(options);
			 		
			 	}
			 	
			 	color="";
		    	
			 	if(test3.overall.equals("FAILED")){
		    		
		    		color=(char)34+"RED"+(char)34;
		    		write2.write("<p><p><font color="+(char)34+"red"+(char)34+">SECOND ITERATION FAILED</font><p><p>USERNAME USED===>" + test3.genlogin+"<p><p>");
		    	
			 	}else{
		    		
		    		if(retry3>0){	    		
		    			write2.write("<p><p><font color="+(char)34+"black"+(char)34+">SECOND ITERATION PASSED</font><p><p>USERNAME USED===>" + test3.genlogin+"<p><p>");
		    		}else{
		    			write2.write("<p><p><font color="+(char)34+"black"+(char)34+">FIRST ITERATION PASSED</font><p><p>USERNAME USED===>" + test3.genlogin+"<p><p>");
		    		}
		    	}
		    		
			 
		    	
			 	
			 	
			 
			 	File file5 = new File("target/reports/"+test3.timesta3+"brw3.html");
				FileWriter write5 = new FileWriter(file5,true);
				
				System.out.println("Generating Reports");
			    System.out.println("-----------------------------------");
				
		    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
		    	
		    	write5.write(test3.result5);
		    	write2.write(test3.result6);
		    	
		    	write2.write("</tr></table></font>");
		    	
		    	
		    	write2.write("<p> OVERALL STATUS=<font color="+ color+">"+test3.overall +"</font> <p>");
		    	
		    	if(!buildurl.equals("null")){
		    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test3.timesta3 + "brw3.html"+(char)34+"> LINK </a> for a full report<p>");
		    		write2.write("<p></p><p></p><p>Console Output can be found <a href="+(char)34+ buildurl+"console"+(char)34+"> HERE </a></p>");
		    	}else{
		    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test3.timesta3 + "brw3.html"+(char)34+"> LINK </a> for a full report<p>");
		    	}
				
		    	write2.write("--------------------------------------------");
		    	write2.write("--------------------------------------------");
		    	write2.write("--------------------------------------------");
		    	write5.close();
				
				
			}*/
			if(!buildurl.equals("null")){
	    		
				System.out.println("All Tests Finished, please refer to " + buildurl +"artifact/target/reports/result.html to see the report");
			
			}else{
			
				System.out.println("All Tests Finished, please refer to email to see the report");
				
			}
			
	    	
	    	
			System.out.println("-----------------------------------");
						
			
		 write2.close();
		 //tests.driver.quit();
		 if(!browser2.equals("null")){tests2.driver2.quit();}
		 //if(!browser3.equals("null")){tests3.driver3.quit();}
	 }}

}



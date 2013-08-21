package test.java;

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

import main.java.tests;
import main.java.juega1;




public class mainTest {

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	
public String hola(){
		
		return("Ola k ase?");
	}

	@Test
	
	
	
	public void main() throws Exception {
		// TODO Auto-generated method stub

		//for (int i=0; i<args.length; i++) System.out.println(args[i]);
		
		
		String args=String.valueOf(System.getProperty("totest"));
		String url=String.valueOf(System.getProperty("url"));
		String xpath=String.valueOf(System.getProperty("xpath"));
		//remember another system property called batch
		
		
		//System.out.println(args.length);
		main.java.tests test = new main.java.tests();
		main.java.juega1 test2 = new main.java.juega1();
		
		
		
		if(!args.isEmpty()){
			
			if (args.equals("juega1")){
				
				test2.setUp();
			}else{
				String[] options={args,url,xpath};
				//System.out.println(args+"     "+url+"      "+xpath);
				test.setUp(options);
			}
				
			}else{
				String[] options=new String[1];
				options[0]="nothing";
				test.setUp(options);
				
		}
		
		
		
		//test.setUp(args);
		//test.setUp(options);
		//test2.setUp();
		//call to tests class and run it.
	}
		
	 @After
	 public void tearDown() throws Exception {
		 	
		 	main.java.tests test = new main.java.tests();	
		 
		 	String color="";
	    	
	    	if(test.overall.equals("FAILED")){color=(char)34+"RED"+(char)34;
	    	}//else{
	    		//color=(char)34+"BLACK"+(char)34;
	    	//}
		 
	    	
		 	String buildurl=String.valueOf(System.getProperty("buildurl"));
		 	
		 
		 	File folder=new File("target/reports");
			File folder2=new File("target/screenshots");
			
			if(!folder.exists()){folder.mkdirs();}
			if(!folder2.exists()){folder2.mkdirs();}
			
			File file = new File("target/reports/"+test.timesta+".html");
			File file2=new File("target/reports/result.html");
			file.delete();
			
			FileWriter write = new FileWriter(file,true);
			FileWriter write2 = new FileWriter(file2,true);
			System.out.println("Generating Reports");
		    System.out.println("-----------------------------------");
			
	    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+" bgcolor="+color+"><tr><th>TEST</th><th>STATUS</th></tr>");
	    	
	    	write.write(test.result);
	    	write2.write(test.result2);
	    	
	    	write2.write("</tr></table></font>");
	    	
	    	
	    	write2.write("<p> OVERALL STATUS=<font color="+ color+">"+test.overall +"</font> <p>");
	    	
	    	if(!buildurl.equals("null")){
	    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+test.timesta + ".html"+(char)34+"> LINK </a> for a full report<p>");
	    	}else{
	    		write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ test.timesta + ".html"+(char)34+"> LINK </a> for a full report<p>");
	    	}
	    	
	    	if(!buildurl.equals("null")){
	    		
				System.out.println("All Tests Finished, please refer to " + buildurl +"artifact/target/reports/result.html to see the report");
			
			}else{
			
				System.out.println("All Tests Finished, please refer to email to see the report");
				
			}
			
	    	
	    	
			System.out.println("-----------------------------------");
	    	
	    		    	
			write.close();
			write2.close();
						
			
		 
		 tests.driver.quit();
	 }

}



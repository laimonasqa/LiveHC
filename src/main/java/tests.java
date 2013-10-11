//package main.java;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import java.lang.reflect.Method;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyDownAction;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.Augmenter;





import javax.swing.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import java.net.*;

public class tests {
  		
  static String separator="<p>\n------------------------------------------------------------------------------------------</p>\n\n";
  public static String result="";
  public static String overall="PASSED";
  public static int started=0; //Control Variable
  public static int finished=0;//Control Variable
  public static String regcss=""; //will store the registration css locator for second iteration
  public static String result2="";
  public static WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  

  
  
  static Connection con = null;
	//static String servername="db4free.net";
	//static String username="hellpine";
	//static String db="firsttry";
	//static String pass="111111";
  	static String servername="192.168.100.214";
	static String username="daniel";
	static String db="automation_dev";
	static String pass="daniel";
	public static ResultSet rs=null;
	public static ResultSet ls=null;
	public static ResultSet ss=null;
	public static ResultSet ns=null;
	public static ResultSet l1rs=null;
	public static ResultSet l1rs2=null;
	public static ResultSet l1rs3=null;
	public static ResultSet l2rs1=null;
	public static ResultSet l2rs2=null;
	public static ResultSet l2rs3=null;
	public long timesta=new Date().getTime()/1000;
	public String batchid; 
	public String language;
	public boolean is64bit = false;
	public static Statement stat=null;
	public static Statement stat2=null;
	public static Statement stat3=null;
	public static Statement stat4=null;
	public int total=0;
	public int failed=0;
	
	public static String browser;
	

	@Test
		
	public void readdatabase() throws Exception {
		
		
		String tkind;
		String tid;
		timesta=timesta%1000000000;
		//File folder=new File("target/reports");
		//File folder2=new File("target/screenshots");
		
		//if(!folder.exists()){folder.mkdirs();}
		//if(!folder2.exists()){folder2.mkdirs();}
		
		//File file = new File("target/reports/"+timesta+".html");
		//File file2=new File("target/reports/result.html");
		//file.delete();
		//file2.delete();
		//System.out.println(new Timestamp(date.getTime()));
		
		if (System.getProperty("os.name").contains("Windows")) {
		    is64bit = (System.getenv("ProgramFiles(x86)") != null);
		} else {
		    is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
		}
		
		if(is64bit=false){
			
			System.out.println("------------------------");
			System.out.println("Running on 32 bit system");
			System.out.println("------------------------");
			
		}else{
			
			System.out.println("------------------------");
			System.out.println("Running on 64 bit system");
			System.out.println("------------------------");
			
		}
		
		
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver"); //load driver for Mysql
			
			
			con=DriverManager.getConnection("jdbc:mysql://"+servername+"/"+db, username, pass); //establish teh connection to the database 
			}catch(ClassNotFoundException e){
				System.out.println("Class Not Found: "+e.getMessage());
						
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}	

		stat=con.createStatement(); //declare statements variables
		stat2= con.createStatement();
		
		System.out.println("-----------------------------------");
		System.out.println("Automation Application Rev 0.020");
		System.out.println("-----------------------------------");
		
		System.out.println("Now Acquiring Batch from Database");
		
		
		rs= stat.executeQuery("select * from gotest"); //execute sql query
						
		rs.first();
		batchid=String.valueOf(System.getProperty("batch"));
		if(batchid.equals("null")){
		batchid=(rs.getString("batchid"));} //read from recordset
		System.out.println("Batch successfully Adquired====>" + batchid);
		System.out.println("-----------------------------------");
		System.out.println("Acquiring Data from Batch");
		System.out.println("-----------------------------------");
		//System.out.println(rs.getString("batchid"));
		//System.out.println(batchid);
		//rs.close();
		stat.clearBatch();
		rs= stat.executeQuery("select * from batch where batchid='"+batchid+"'");
		//System.out.println(rs.getString("testid"));
		//rs.beforeFirst();
		String url="";
		if(!rs.next()){
			
			result=result+"<p>BATCHID is not correct<p>";
			overall="FAILED";
			System.out.println("Batchid Not found");
			Date date = new Date();
			result2=result2+"<p><p>Date and time of running: "+date;
			//System.exit(0);
		}else{
		
		rs.first();
		url=rs.getString("url");
		
		}
		
		if(!url.equals("")){

		System.out.println("Data Successfully Adquired");
		System.out.println("-----------------------------------");
		System.out.println("Opening Site To Test");
		System.out.println("-----------------------------------");
		
		
		//System.out.println(url);
		//if(url.contains("http://")){
		
			//	url= url.replace("http://", "https://4646:4646@");
				
		//}else{
			
			//	url= url.replace("https://", "https://4646:4646@");
		//}
	
		//System.out.println(url);
		
		browser=String.valueOf(System.getProperty("browser"));
		baseUrl=(url);
		 //FirefoxBinary binary = new FirefoxBinary();  
		 //File firefoxProfileFolder = new 
		 //File("profile");
		 //FirefoxProfile profile = new FirefoxProfile(firefoxProfileFolder);
		 //profile.setAcceptUntrustedCertificates(true);
		 //profile.addExtension("autoauth-2.1-fx+fn.xpi");
		 //driver = new FirefoxDriver(profile);
		 //driver = new FirefoxDriver();
		
		//System.out.println(browser);
		
			if(!browser.equals("null")){			
		
			if(browser.equals("chrome")){
				
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--disable-loggin"));
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver(capabilities);
			
			}
			
			if(browser.equals("ie")){
				
				File file = new File("IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
			
			}
		
		
			
			if(browser.equals("firefox")){
			
				driver = new FirefoxDriver();
			}
			
		}else{
			
			browser="firefox";
			driver = new FirefoxDriver();
		}
		
		//driver.manage().deleteAllCookies();
		
		//FirefoxProfile ffprofile = new FirefoxProfile("c:\");
		//ffprofile.setPreference("network.automatic-ntlm-auth.trusted-uris", "stminver-demo.com");
		//ffprofile.setPreference("network.http.phishy-userpass-length", 255);
		//ffprofile.setAcceptUntrustedCertificates(true);
		//ffprofile.setAssumeUntrustedCertificateIssuer(false);
		//driver = new FirefoxDriver(ffprofile);
		
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    //driver.get(baseUrl);
	    driver.get(baseUrl);
	    driver.manage().window().maximize();
	    try{ //Try to bypass company privacy policy
	    	driver.findElement(By.linkText("Click here to accept this statement and access the Internet.")).click();
	    }catch (Exception e){
	    	
	    }
	    
	    language="null";
	    while(language.equals("null")){
	    	
	    	String source=driver.getPageSource();
	    	System.out.println("Acquiring Site Language");
	    	System.out.println("-----------------------------------");
	    
	    	if (source.contains("ontact")||source.contains("esponsible")){ language="english";}
	    	if (source.contains("ontakt")){ language="norwegian";}
	    	if (source.contains("ö")){ language="swedish";}
	    
	    	if(language.equals(null)){
	    		driver.navigate().refresh();
	    	}
	    
	    }
	    
	    System.out.println("Site Language=="+language);
	    System.out.println("-----------------------------------");
	    		
	    
	    
						
		//System.out.println(rs.getString("url"));
		//driver = new FirefoxDriver();
	    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    //driver.get(baseUrl);
	    try{
	    	driver.switchTo().alert().accept();
	    }catch (Exception e){  //Sometimes a pop up appears when launching site
	    	//System.out.println(e);
	    }
	    
		//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		//System.out.println(rs.getRow());
		rs.last();
		int n,s;
		n=rs.getRow();
		s=0;
		rs.beforeFirst();
		
		//FileWriter write = new FileWriter(file,true);
		//FileWriter write2 = new FileWriter(file2,true);
		Date date = new Date();
		String header="<p><FONT COLOR="+(char)34+"black"+(char)34+">\n------------------------------------------------------------------------------------------</p>\n\n<strong>BATCH ID=" + batchid + "<p><p>URL= " + baseUrl + "<p><p>Date and Time:"+date+"</p><p></p><p>Browser=" + browser + "</FONT></strong></p>";
		result=result+header;
		result2=result2+header;
		System.out.println("Acquiring tests from batch");
	    System.out.println("-----------------------------------");
		
		while(s != n){
			
			if (rs.next()){
			//System.out.println(rs.getRow());
			//System.out.println(rs.getString("testid"));
			stat2.clearBatch();
			ls=stat2.executeQuery("select * from tests where testid='"+rs.getString("testid")+"'");
					
			ls.first();
			//System.out.println(ls.getString("testkind"));
			
			//System.out.println(ls.getString("testid")+"    "+ls.getString("testkind"));	
			//System.out.println(ls.getString("testkind"));
			s=s+1;
			if(ls.getString("testkind").equals("single")){
				//System.out.println(ls.getString("testkind"));	
				single(ls.getString("testid"));
			
			}
			
			if(ls.getString("testkind").equals("l1test")){
				
				//result=result+"<p><H1>L1 Registering Test-----"+ls.getString("testid")+"</H1><p>";
				//System.out.println(ls.getString("testkind"));	
				l1test(ls.getString("testid"));
			
			}
			//}
			
			}}
		
		
		
		
		//write.write(header);
    	//write.write("<p>"+result+"<p>");
    	//write.write(footer);
    	//write.write("<p> OVERALL STATUS= "+ overall +" <p>");
		//System.out.println("Generating Reports");
	    //System.out.println("-----------------------------------");
		//result=result+"<p> OVERALL STATUS= "+ overall +" <p>";
    	//result2=result2+"<p><p><p><p><table border="+(char)34+"1"+(char)34+"><tr><th>TEST</th><th>STATUS</th></tr>";
    	//write.write((<p><p><p><p><<table border="1"><tr><th>TEST</th><th>STATUS</th></tr>);
		//write.write(result);
    	//write2.write(result2);
    	//write2.write("</tr></table>");
    	//String currentDir = System.getProperty("user.dir");
    	//if(!buildurl.equals("null")){
    		//result2=result2+"<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ buildurl+"artifact/target/reports/"+timesta + ".html"+(char)34+"> LINK </a> for a full report<p>";
    	//}else{
    		//result2=result2+"<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ timesta + ".html"+(char)34+"> LINK </a> for a full report<p>";
    	//}
    //	write.close();
		//write2.close();
		ls.close();
		rs.close();
		con.close();
		//Desktop.getDesktop().open(file);
		//Desktop.getDesktop().open(file2);
		System.out.println("-----------------------------------");
		
		//if(!buildurl.equals("null")){
		
			//System.out.println("All Tests Finished, please refer to " + buildurl +"artifact/target/reports/result.html to see the report");
		
		//}else{
		
			//System.out.println("All Tests Finished, please refer to email to see the report");
			
		//}
		
		//System.out.println("-----------------------------------");

		//driver.close();
		//driver.quit();
		}// if !url="" end
		}
		
		
		
	//}
  

	

	
	public void ibnwithdrawl(String paymentcss,String logname) throws Exception{
		
		
		started=started+1;
		String screenshot = "target/screenshots/withdrawl" + timesta + ".png";
		System.out.println("Launching Withdrawl Test");
	    System.out.println("-----------------------------------");
	    
		result2=result2+"<tr><td>Withdrawl</td>";
		
		String[] wdlink = {"[qa='withdrawal']","a.button_withdraw","#log_account_buttons a.button_withdraw"};
		
		
		String [][] wdmethod= new String [6][3];
		
		if(paymentcss.toLowerCase().contains("neteller")){
		
			wdmethod[0][0]="input[name='withdrawalAmount']";
			wdmethod[0][1]="text";
			wdmethod[0][2]="100";
			wdmethod[1][0]="[id='submit']";
			wdmethod[1][1]="button";
			wdmethod[1][2]="";
			
		}
		
		
		if(language.equals("english")){
			
			wdmethod[0][2]="10";
		}
		
		int success=0;
		int i=0;
		
		while(i<wdlink.length){
			
			try{
				
				driver.findElement(By.cssSelector(wdlink[i])).click();
				System.out.println("Withdrawl Link clicked");
				System.out.println("-----------------------------------");
				Thread.sleep(1000);
				break;
				
				
			}catch(Exception e1){
				
				if(i>=wdlink.length){
				System.out.println("Withdrawl Link not found");
				System.out.println("-----------------------------------");
				result=result+"<p>Withdrawl Link not Found<p>";
				success=1;
     
					takesc(screenshot);
					result=result+"<p>withdrwl Error Screenshot  <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
				}
				
			}i=i+1;}
			
				if (success==0){				
				
					
					int j=0;
					while(j<=wdmethod.length-1){
					
						//System.out.println(wdmethod.length);
					  if(!wdmethod.equals(null)){
						if(wdmethod[j][1].equals("text")){
						
							try{
							
								driver.findElement(By.cssSelector(wdmethod[j][0])).clear();
								driver.findElement(By.cssSelector(wdmethod[j][0])).sendKeys(wdmethod[j][2]);
								//System.out.println("Withdrawl field found and filled");
								
							
							}catch(Exception e1){
							
								System.out.println("Withdrawl field not found");
								success=1;
								result=result+"<p>WithDrawl: Some field not found<p>";
								try {
					                
									takesc(screenshot);
									
									result=result+"<p>wdrwfield Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									//System.out.println("Deposit correctly placed");
									//result2=result2+"<td>PASS</td></tr>";
			                
								} catch (IOException e2) {
									System.out.println("Screenshot Failed");
									System.out.println("-----------------------------------");
								}
							}
						
						
						}
					
						if(wdmethod[j][1].equals("button")){
						
							try{
							
								driver.findElement(By.cssSelector(wdmethod[j][0])).click();
								//System.out.println("Withdrawl button found and clicked");
							
								Thread.sleep(1000);
							
									try{
							
										driver.switchTo().alert().accept();
										//System.out.println("Alert present and confirmed");
										Thread.sleep(1000);
							
									}catch(Exception e1){
								
										//System.out.println("Alert not present");
									}
							
									try{
								
										driver.switchTo().alert().accept();
										//System.out.println("Alert present and confirmed");
										Thread.sleep(1000);
							
									}catch(Exception e1){
								
										//System.out.println("Alert not present");
									}
							
									try{
									
										if((driver.getCurrentUrl().contains("lobby")||driver.getCurrentUrl().contains("home")) && driver.findElement(By.cssSelector("[id='the_usernameright']")).getText().equals(logname)){
								
										System.out.println("Withdrawl complete and user correctly redirected to lobby");
										System.out.println("-----------------------------------");
																		
										
										try {
					                
											takesc(screenshot);
											result=result+"<p>Screenshot for Withdrawal <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
											//System.out.println("Deposit correctly placed");
											//result2=result2+"<td>PASS</td></tr>";
					                
										} catch (IOException e2) {
											System.out.println("Screenshot Failed");
											System.out.println("-----------------------------------");
										}
										
										break;
									
									
									}else{
								
										System.out.println("Redirection failed");
										System.out.println("-----------------------------------");
										success=1;
									}
									}catch(Exception e21){
										
									}					
							}catch(Exception e1){
							
								System.out.println("Withdrawl button not found");
								System.out.println("-----------------------------------");
								success=1;
								result=result+"<p>Withdrawl: Button not found<P>";
									                
									takesc(screenshot);
									result=result+"<p>wdrwlbutt Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									//System.out.println("Deposit correctly placed");
									//result2=result2+"<td>PASS</td></tr>";
			                
															
							}
												
						}j=j+1;
					}}
										
				}
				
				
				if (success==0){
					
					result2=result2+"<td>PASS</td></tr>";
					System.out.println("Withdrawl Test Passed");
					System.out.println("-----------------------------------");
					
				}else{
					
					result2=result2+"<td>FAILED</td></tr>";
					System.out.println("Withdrawl Test Failed");
					System.out.println("-----------------------------------");
					overall="FAILED";
				}
				finished=finished+1;
	}
	
	
		
	public int paymenterrorcheck(String payment,int success) throws Exception{
		
		success=0;
		started=started+1;
		System.out.println("Checking ====>"+payment+"<===== communication");
		System.out.println("-----------------------------------");
		String ct1,mb1,mb2,uk1,uk2,uke,nt1,nt2,nt3,nte,pp1,ps1,button,button2;
		
		mb1="html body div#wrapper div#full_col div#main_col div#contentPanel div.innerpanelContainer div.innerpanel div#cmsPayContainer div#submitTrack form#moneybookerdepositform fieldset div input#pay_from_email.cmsPayInputField";
		mb2="html body div#wrapper div#full_col div#main_col div#contentPanel div.innerpanelContainer div.innerpanel div#cmsPayContainer div#submitTrack form#moneybookerdepositform fieldset div input#amount.cmsPayInputField";
		uk1="input[name='voucherNumber']";
		uk2="input[name='voucherValue']";
		uke="#regerrors span";
		//uke=uke.toUpperCase();
		nt1="input[name='accountId']";
		nt2="input[name='secureId']";
		nt3="input[name='amount']";
		nte="#regerrors span";
		pp1="[name='amount']";
		ps1="[name='amount']";
		ct1="[name='amount']";
		button="#submit > span";
		button2="a#submit.btn";
		String Loadmask="/html/body/div[@id='wrapper']/div[@id='full_col']/div[@id='main_col']/div[@id='contentPanel']/div[@class='innerpanelContainer']/div[@class='innerpanel']/div[@id='cmsPayContainer']/form[@id='netellerdepositform']/div[@class='loadmask-msg']/div";
		Loadmask=Loadmask.toUpperCase();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String message="";
		
		//if(batchid.contains("labels")){
			
			mb1="[qa='skemid']";
			mb2="[qa='skamount']";
			uk1="[qa='ukvoucher']";
			uk2="[qa='ukvalue']";
			uke="[qa='depositerror']";
			nt1="[qa='nanumber']";
			nt2="[qa='nsnumber']";
			nt3="[qa='namount']";
			nte="[qa='depositerror']";
			pp1="[qa='ppamount']";
			ps1="[qa='psamount']";
			ct1="[qa='ctamount']";
			button="[qa='dbutton']";
					
		//}
		
		String winHandleBefore = driver.getWindowHandle();
		
		if(payment.equals("paypal")){
			
			try{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button)));
				
			}catch(Exception e){
				
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button2)));
				}catch(Exception e1){
					
				}
			}
			
			try{
				
				driver.findElement(By.cssSelector(pp1)).clear();
				driver.findElement(By.cssSelector(pp1)).sendKeys("200");
				
			}catch(Exception e){
			
				result=result+"<p>Some Field Failed in PayPal test<p>";
				System.out.println("Some field failed in PayPal");
				success=1;
								
			}
			
			try{
				driver.findElement(By.cssSelector(button)).click();
			}catch(Exception e){
				driver.findElement(By.cssSelector(button2)).click();
			}
			
			
			
			int timeCount=0;
			
			timeCount=0;
				
			while(!driver.getCurrentUrl().contains("paypal")){
					
					Thread.sleep(200);
					timeCount++;
					if(timeCount>50){
						break;
					}
					
			}
				
			if(driver.getCurrentUrl().contains("paypal")){
					
					System.out.println("PayPal Communnication Confirmed");
					result=result+"<p>Paypal Commuication Confirmed<p>";
					System.out.println("-----------------------------------");
					String screenshot = "target/screenshots/paypalsuite" + timesta + ".png";
					takesc(screenshot);
					result=result+"<p>Screenshot of PayPal payment suite <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
					
				}else{
					
					System.out.println(driver.getCurrentUrl().toString());
					result=result+"<p>PayPal Commuication Failed<p>";
					//result2=result2+"<td>FAILED</td></tr>";
					success=1;
					
				}
				
				driver.navigate().back();
			
					
			
			if(success==1){
				
				System.out.println("SKRILL Commuication Failed");
				System.out.println("-----------------------------------");
				result=result+"<p>SKRILL Commuication Failed<p>";
				result2=result2+"<td>FAILED</td></tr>";
				
			}
			
		}
		
		if(payment.equals("paysafe")){
			
			try{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button)));
				
			}catch(Exception e){
				
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button2)));
				}catch(Exception e1){
					
				}
			}
			
			try{
				
				driver.findElement(By.cssSelector(ps1)).clear();
				driver.findElement(By.cssSelector(ps1)).sendKeys("200");
				
			}catch(Exception e){
			
				result=result+"<p>Some Field Failed in Paysafe test<p>";
				System.out.println("Some field failed in Paysafe");
				success=1;
								
			}
			
			try{
				driver.findElement(By.cssSelector(button)).click();
			}catch(Exception e){
				driver.findElement(By.cssSelector(button2)).click();
			}
			
			
			
			int timeCount=0;
			
			while ( driver.getWindowHandles().size() == 1 ){	
			
			   driver.getWindowHandles();
			   Thread.sleep(200);
			   timeCount++;
			   if ( timeCount > 50 ) 
			   {
			       break;
			   }
			}
			
			
			
			try{
				
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				
				timeCount=0;
				
				while(!driver.getCurrentUrl().contains("paysafecard")){
					
					Thread.sleep(200);
					timeCount++;
					if(timeCount>50){
						break;
					}
					
				}
				
				if(driver.getCurrentUrl().contains("paysafecard")){
					
					System.out.println("PaySafe Communnication Confirmed");
					result=result+"<p>PaySafe Commuication Confirmed<p>";
					System.out.println("-----------------------------------");
					String screenshot = "target/screenshots/paysafesuite" + timesta + ".png";
					takesc(screenshot);
					result=result+"<p>Screenshot of PaySafe payment suite <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
					
				}else{
					
					System.out.println(driver.getCurrentUrl().toString());
					result=result+"<p>PaySafe Commuication Failed<p>";
					//result2=result2+"<td>FAILED</td></tr>";
					success=1;
					
				}
				
				driver.close();
				driver.switchTo().window(winHandleBefore);
				
			}catch(Exception e1){
				
				System.out.println("Something happens with PaySafe Pop Up");
				success=1;
				result=result+"<p>PaySafe Commuication Failed<p>";
				
			}
			
			if(success==1){
				
				System.out.println("PaySafe Commuication Failed");
				System.out.println("-----------------------------------");
				result=result+"<p>PaySafe Commuication Failed<p>";
				result2=result2+"<td>FAILED</td></tr>";
				
			}
			
		}
		
		if(payment.equals("citadel")){
			
			try{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button)));
				
			}catch(Exception e){
				
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button2)));
				}catch(Exception e1){
					
				}
			}
			
			try{
				
				driver.findElement(By.cssSelector(ct1)).clear();
				driver.findElement(By.cssSelector(ct1)).sendKeys("200");
				
			}catch(Exception e){
			
				result=result+"<p>Some Field Failed in Citadel test<p>";
				System.out.println("Some field failed in Citadel");
				success=1;
								
			}
			
			try{
				driver.findElement(By.cssSelector(button)).click();
			}catch(Exception e){
				driver.findElement(By.cssSelector(button2)).click();
			}
			
			
			
			int timeCount=0;
			
			while ( driver.getWindowHandles().size() == 1 ){	
			
			   driver.getWindowHandles();
			   Thread.sleep(200);
			   timeCount++;
			   if ( timeCount > 50 ) 
			   {
			       break;
			   }
			}
			
			
			
			try{
				
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				
				timeCount=0;
				
				while(!driver.getCurrentUrl().contains("internetbanking")){
					
					Thread.sleep(200);
					timeCount++;
					if(timeCount>50){
						break;
					}
					
				}
				
				if(driver.getCurrentUrl().contains("internetbanking")){
					
					System.out.println("Citadel Communnication Confirmed");
					result=result+"<p>Citadel Commuication Confirmed<p>";
					System.out.println("-----------------------------------");
					String screenshot = "target/screenshots/citadelsuite" + timesta + ".png";
					takesc(screenshot);
					result=result+"<p>Screenshot of Citadel payment suite <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
					
				}else{
					
					System.out.println(driver.getCurrentUrl().toString());
					result=result+"<p>Citadel Commuication Failed<p>";
					//result2=result2+"<td>FAILED</td></tr>";
					success=1;
					
				}
				
				driver.close();
				driver.switchTo().window(winHandleBefore);
				
			}catch(Exception e1){
				
				System.out.println("Something happens with Citadel Pop Up");
				success=1;
				result=result+"<p>Citadel Commuication Failed<p>";
				
			}
			
			if(success==1){
				
				System.out.println("Citadel Commuication Failed");
				System.out.println("-----------------------------------");
				result=result+"<p>Citadel Commuication Failed<p>";
				result2=result2+"<td>FAILED</td></tr>";
				
			}
			
		}
		
		if(payment.equals("skrill")){
			
			try{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button)));
				
			}catch(Exception e){
				
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button2)));
				}catch(Exception e1){
					
				}
			}
			
			try{
				
				driver.findElement(By.cssSelector(mb1)).clear();
				driver.findElement(By.cssSelector(mb1)).sendKeys("rebecca.dorward@stminverltd.com");
				driver.findElement(By.cssSelector(mb2)).clear();
				driver.findElement(By.cssSelector(mb2)).sendKeys("200");
				
			}catch(Exception e){
			
				result=result+"<p>Some Field Failed in Skrill test<p>";
				System.out.println("Some field failed in Skrill");
				success=1;
								
			}
			
			try{
				driver.findElement(By.cssSelector(button)).click();
			}catch(Exception e){
				driver.findElement(By.cssSelector(button2)).click();
			}
			
			
			
			int timeCount=0;
			
			while ( driver.getWindowHandles().size() == 1 ){	
			
			   driver.getWindowHandles();
			   Thread.sleep(200);
			   timeCount++;
			   if ( timeCount > 50 ) 
			   {
			       break;
			   }
			}
			
			
			
			try{
				
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				
				timeCount=0;
				
				while(!driver.getCurrentUrl().contains("payment.pl")){
					
					Thread.sleep(200);
					timeCount++;
					if(timeCount>50){
						break;
					}
					
				}
				
				if(driver.getCurrentUrl().contains("moneybooker")||driver.getCurrentUrl().contains("skrill")){
					
					System.out.println("Skrill Communnication Confirmed");
					result=result+"<p>SKRILL Commuication Confirmed<p>";
					System.out.println("-----------------------------------");
					String screenshot = "target/screenshots/skrillsuite" + timesta + ".png";
					takesc(screenshot);
					result=result+"<p>Screenshot of Skrill payment suite <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
					
				}else{
					
					System.out.println(driver.getCurrentUrl().toString());
					result=result+"<p>SKRILL Commuication Failed<p>";
					//result2=result2+"<td>FAILED</td></tr>";
					success=1;
					
				}
				
				driver.close();
				driver.switchTo().window(winHandleBefore);
				
			}catch(Exception e1){
				
				System.out.println("Something happens with SKRILL Pop Up");
				success=1;
				result=result+"<p>SKRILL Commuication Failed<p>";
				
			}
			
			if(success==1){
				
				System.out.println("SKRILL Commuication Failed");
				System.out.println("-----------------------------------");
				result=result+"<p>SKRILL Commuication Failed<p>";
				result2=result2+"<td>FAILED</td></tr>";
				
			}
			
		}
		
		if(payment.equals("ukash")){
			
			try{
				
				try{
					
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button)));
					
				}catch(Exception e){
					
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button2)));
					}catch(Exception e1){
						
					}
				}
				
				
				driver.findElement(By.cssSelector(uk1)).clear();
				driver.findElement(By.cssSelector(uk1)).sendKeys("6337180355029426806");
				driver.findElement(By.cssSelector(uk2)).clear();
				driver.findElement(By.cssSelector(uk2)).sendKeys("200");
				
				
				
				try{
					driver.findElement(By.cssSelector(button)).click();
				}catch(Exception e){
					driver.findElement(By.cssSelector(button2)).click();
				}
										
					try{
			
						while(driver.findElement(By.xpath(Loadmask)).isDisplayed()){
						
							System.out.println("Waiting for server response");
							Thread.sleep(1000);
						}	
					
					}catch(Exception e1){
						
					}
					
					try{
						
						
						while(!driver.findElement(By.cssSelector(uke)).isDisplayed()){
							System.out.println("Waiting for error message");
							//Thread.sleep(1000);
							
						}
						//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
					
					}catch(Exception e1){
						
						
					}
					//System.out.println("Continue");
					
					try{
						
						if(language.equals("swedish")){ message="Teknisk Misstag. Behaga komma i kontakt med Ukash Köpman Stöd";}
						if(language.equals("english")){ message="Technical Mistake. Please get in contact with Ukash Merchant Support";}
						if(language.equals("norwegian")){ message="";}
						
						String response="";
						int it=0;
						while(!response.contains(message)){
							if(it>=4){break;}
							//response= driver.findElement(By.cssSelector(uke)).getText();
							//System.out.println(response);
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nte)));
							response= driver.findElement(By.cssSelector(uke)).getText();
						//System.out.println(response);
				
											
								
								
							}
							
							if(response.contains(message)){
														
								result=result+"<p>UKASH Commuication Confirmed<p>";
								System.out.println("-----------------------------------");
											
							}else{
								
								success=1;
								it=it+1;
							
						
							}
						
						if(success==1){
							
							System.out.println("UKASH Commuication Failed");
							System.out.println("-----------------------------------");
							result=result+"<p>UKASH Commuication Failed<p>";
							result2=result2+"<td>FAILED</td></tr>";
							
						}
						
						
				
					}catch(Exception e1){
					
						System.out.println("Error Message not found");
						System.out.println("-----------------------------------");
						result=result+"<p>UKASH error message not found<p>";
						result2=result2+"<td>FAILED</td></tr>";
						success=1;
					
					}
					
			}catch(Exception e1){
				
				System.out.println("Something wrong happens in UKASH check");
				System.out.println("-----------------------------------");
				result=result+"<p>Some Field/button not found while UKASH Commuication check<p>";
				success=1;
				result2=result2+"<td>FAILED</td></tr>";
			}
				
		
			
	
			
		}
		
		if(payment.equals("neteller")){	
			
			String amount="";
			
						
				try{
					driver.findElement(By.cssSelector(nt1)).clear();
					driver.findElement(By.cssSelector(nt1)).sendKeys("458591047553");
					driver.findElement(By.cssSelector(nt2)).clear();
					driver.findElement(By.cssSelector(nt2)).sendKeys("123456");
					driver.findElement(By.cssSelector(nt3)).clear();
					driver.findElement(By.cssSelector(nt3)).sendKeys("200");
					
					if(language.equals("swedish")){ message="Var vänlig kontakta NETeller";}
					if(language.equals("english")){ message="No client has been found for the specified net_account variable.";}
					if(language.equals("norwegian")){ message="Hvor vennlig at kontakte NETeller";}
					
					try{
						
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button)));
						
					}catch(Exception e){
						
						try{
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(button2)));
						}catch(Exception e1){
							
						}
					}
					
					try{
						driver.findElement(By.cssSelector(button)).click();
					}catch(Exception e){
						driver.findElement(By.cssSelector(button2)).click();
					}
					
											
						try{
				
							while(driver.findElement(By.xpath(Loadmask)).isDisplayed()){
							
								System.out.println("Waiting for server response");
								Thread.sleep(1000);
							}	
						
						}catch(Exception e1){
							
						}
						
						try{
							
							
							//String error="//fieldset/div[@id='regerrors']";
							//error=error.toUpperCase();
							
							while(!driver.findElement(By.cssSelector(nte)).isDisplayed()){
								System.out.println("Waiting for error message");
								//Thread.sleep(1000);
								
							}
							//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
						
						}catch(Exception e1){
							
							
						}
						//System.out.println("Continue");
						try{
						
																
							
							
							//String errmsg="//fieldset/div[@id='regerrors']";
							//errmsg=errmsg.toUpperCase();
							String response="";
							int it=0;
							while(!response.contains(message)){
								if(it>=4){break;}
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nte)));
								response= driver.findElement(By.cssSelector(nte)).getText();
								System.out.println(response);
					
								if(response.contains(message)){
							
									//System.out.println("Neteller Commuication Confirmed");
									result=result+"<p>Neteller Commuication Confirmed<p>";
									System.out.println("-----------------------------------");
									success=0;
									break;
								}else{
								
									success=1;
									it=it+1;
								
							
								}}
							
							if(success==1){
								
								System.out.println("Neteller Commuication Failed");
								System.out.println("-----------------------------------");
								result=result+"<p>Neteller Commuication Failed<p>";
								result2=result2+"<td>FAILED</td></tr>";
								
							}
							
					
						}catch(Exception e1){
						
							System.out.println("Error Message not found");
							System.out.println("-----------------------------------");
							result=result+"<p>Neteller error message not found<p>";
							result2=result2+"<td>FAILED</td></tr>";
							success=1;
						
						}
						
				}catch(Exception e1){
					
					System.out.println("Something wrong happens in the check");
					System.out.println("-----------------------------------");
					result=result+"<p>Some Field/button not found while Neteller Commuication check<p>";
					result2=result2+"<td>FAILED</td></tr>";
					success=1;
				}
					
			
				
		}
		
		System.out.println(payment+" Communication Finished");		
		System.out.println("-----------------------------------");
		if(success==1){ overall="FAILED";}
		finished=finished+1;
		return(success);
		
	}
		
		
	public void ibndeposit(String paymentcss,String logname) throws Exception{
		
		started=started+1;		
		System.out.println("Starting IBN Deposit");
		//System.out.println("Payment Method Selected====>"+ paymentcss);
		System.out.println("-----------------------------------");
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		String merchant="//div[3]/label";
		String email="//div[5]/label";
		String auth="//div[7]/label";
		String trans="//div[9]/label";
		String tdate="//div[11]/label";
		String surname="//div[13]/label";
		String ttype="//div[15]/label";
		String tid="//div[17]/label";
		
		String merchtxt="merchant name";
		String emailtxt="e-mail";
		String autcodtxt="authorisation";
		String transamtxt="transaction amount";
		String transdattxt="transaction date";
		String surnatxt="surname";
		String trantytxt="transaction type";
		String transidtxt="transaction id";
		
		if(language.equals("swedish")){
			merchtxt="namn";
			emailtxt="e-post id";
			autcodtxt="auktoriseringskod";
			transamtxt="transaktionsbelopp";
			transdattxt="transaktionsdatum";
			surnatxt="efternamn";
			trantytxt="transaktionstyp";
			transidtxt="transaktions id";
		}
		
		if(language.equals("norwegian")){
			merchtxt="kundens navn";
			emailtxt="e-post";
			autcodtxt="autorisasjons kode";
			transamtxt="transaksjonsbeløp";
			transdattxt="transaksjonens dato";
			surnatxt="etternavn";
			trantytxt="transaksjons type";
			transidtxt="transaksjons id";
		}
		
	/*System.out.println(merchtxt);
	System.out.println(emailtxt);
	System.out.println(autcodtxt);
	System.out.println(transamtxt);
	System.out.println(transdattxt);
	System.out.println(surnatxt);
	System.out.println(trantytxt);
	System.out.println(transidtxt);*/

	
		
		//if(batchid.contains("labels")){
			
			merchant="[qa='tmerchant']";
			email="[qa='temail']";
			auth="[qa='tacode']";
			trans="[qa='ttamount']";
			tdate="[qa='ttdate']";
			surname="[qa='tsurname']";
			ttype="[qa=tttype']";
			tid="[qa='ttid']";
			
		//}
		
		/*System.out.println(merchant);
		System.out.println(email);
		System.out.println(auth);
		System.out.println(trans);
		System.out.println(tdate);
		System.out.println(surname);
		System.out.println(ttype);
		System.out.println(tid);*/
		
		//if(browser.equals("ie")){
			//merchant=merchant.toUpperCase();
			//email=email.toUpperCase();
			//auth=auth.toUpperCase();
			//trans=trans.toUpperCase();
			//tdate=tdate.toUpperCase();
			//surname=surname.toUpperCase();
			//ttype=ttype.toUpperCase();
			//tid=tid.toUpperCase();}
		
		String[][] paymethod = new String [5][3];
		
		if(paymentcss.toLowerCase().contains("neteller")){
		
		paymethod[0][0]="input[name='accountId']";
		paymethod[0][1]="458591047553";
		paymethod[0][2]="text"; //Stage
		paymethod[1][0]="input[name='secureId']";
		paymethod[1][1]="411392";
		paymethod[1][2]="text";
		paymethod[2][0]="input[name='amount']";
		paymethod[2][1]="100";
		paymethod[2][2]="text";
		paymethod[3][0]="#submit > span";
		paymethod[3][1]="";
		paymethod[3][2]="button";
		paymethod[4][0]="a#submit.btn";
		paymethod[4][1]="";
		paymethod[4][2]="button";
		
		
								
		//if(batchid.contains("labels")){
			
			paymethod[0][0] ="[qa='nanumber']";
			paymethod[1][0]="[qa='nsnumber']";
			paymethod[2][0]="[qa='namount']";
			paymethod[3][0]="[qa='dbutton']";
						 
		//}
		
		if(batchid.contains("live")){
			
			paymethod[0][1] ="453523465418";
			paymethod[1][1]="664902";
				
		}
		
		if(language.equals("english")){
			
			paymethod[2][1] ="10";
			
		}
		//String[][] paymethod ={ 	{"input[name='accountId']","453523465418","text"}, //Live
			//				{"input[name='secureId']","664902","text"},
				//			{"input[name='amount']","10","text"},
					//		{"#submit > span","","button"},
						//	{"a#submit.btn","","button"}
		
		//};
		}//if paymentcss contains neteller
		
		/*String[][] arr= new String[15][3];
		
		arr[0][0]="hola";
		arr[0][1]="k";
		arr[0][2]="ase?";
		arr[1][0]="Pepe";
		arr[1][1]="q";
		arr[1][2]="toma?";*/
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//int j=0;
		//int l=0;
		
		//System.out.println(arr.length);
		
		//while(j<=arr.length-1){
			
			//while(l<=2){
				
				
				//System.out.println(arr[j][l]);
				//l=l+1;
		//	}
			//l=0;
		//}
		
		
		String screenshot = "target/screenshots/deposit" + timesta + ".png";
		//System.out.println(paymethod[1][1]);
		//System.out.println(paymentcss);
		//System.out.println(paymethod.length);
		
		int success=0;
		
		for(int i=0;i<paymethod.length;i++){
			
			
			if(paymethod[i][2].equals("text")){
				
				try{
				driver.findElement(By.cssSelector(paymethod[i][0])).clear();
				driver.findElement(By.cssSelector(paymethod[i][0])).sendKeys(paymethod[i][1]);
				//System.out.println("Payment Field found and filled");
				//System.out.println("-----------------------------------");
				
				}catch(Exception e1){
					
					//System.out.println("Field not found");
					success=1;
					result=result+"<p>IBNDeposit:One of the fields have not been found<p>";
					System.out.println("Field not found");
					System.out.println("-----------------------------------");
				
					takesc(screenshot);
					result=result+"<p>onfield Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
					//System.out.println("Deposit correctly placed");
					//result2=result2+"<td>PASS</td></tr>";
                										
				}
				
				
								
			}
			
			if(paymethod[i][2].equals("button")){
				
				try{
				
				
				driver.findElement(By.cssSelector(paymethod[i][0])).click();
				System.out.println("Deposit button clicked");
				System.out.println("-----------------------------------");
				//String btext=driver.findElement(By.cssSelector(paymethod[i][0])).getText().toLowerCase();
			
				
				try{
				
				while(driver.findElement(By.cssSelector(paymethod[0][0])).isDisplayed()){
					
					
					System.out.println("Waiting for receipt");
					System.out.println("-----------------------------------");
					Thread.sleep(1000);
					
				}
				
				}catch(Exception e1){
										
				}
					
				
				
				//String source=driver.getPageSource().toLowerCase();
				//System.out.println(source);
				/*
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(paymethod[i][0])));
				}catch(Exception e){
	    			
	    		}*/
				
				takesc(screenshot);
                result=result+"<p>Screenshot for the deposit <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
                System.out.println("Deposit correctly placed");
                System.out.println("-----------------------------------");
                
				if(!batchid.contains("labels")){
				if(driver.findElement(By.xpath(merchant)).isDisplayed() && driver.findElement(By.xpath(merchant)).getText().toLowerCase().contains(merchtxt)){
					
					if(driver.findElement(By.xpath(email)).isDisplayed() && driver.findElement(By.xpath(email)).getText().toLowerCase().contains(emailtxt)){
						
						if(driver.findElement(By.xpath(auth)).isDisplayed() && driver.findElement(By.xpath(auth)).getText().toLowerCase().contains(autcodtxt)){
							
							if(driver.findElement(By.xpath(trans)).isDisplayed() && driver.findElement(By.xpath(trans)).getText().toLowerCase().contains(transamtxt)){
								
								if(driver.findElement(By.xpath(tdate)).isDisplayed() && driver.findElement(By.xpath(tdate)).getText().toLowerCase().contains(transdattxt)){
							
									if(driver.findElement(By.xpath(surname)).isDisplayed() && driver.findElement(By.xpath(surname)).getText().toLowerCase().contains(surnatxt)){
									
										if(driver.findElement(By.xpath(ttype)).isDisplayed() && driver.findElement(By.xpath(ttype)).getText().toLowerCase().contains(trantytxt)){
										
											if(driver.findElement(By.xpath(tid)).isDisplayed() && driver.findElement(By.xpath(tid)).getText().toLowerCase().contains(transidtxt)){
											
												
											
								                
												
								    			//result2=result2+"<td>PASS</td></tr>";
								                
								           											
																				
											}else{
											
											System.out.println("Transaction Id not present in Receipt");
											success=1;
											result=result+"<p>Transaction Id not present in Receipt<p>";
											
								                
												takesc(screenshot);
												result=result+"<p>tid Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
												//System.out.println("Deposit correctly placed");
												//result2=result2+"<td>PASS</td></tr>";
						                
											
											}
										}else{
										
											System.out.println("Transaction Type not present in Receipt");
											success=1;
											result=result+"<p>Transaction Type not present in Receipt<p>";
											
											try {
								                
												takesc(screenshot);
												result=result+"<p>ttype Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
												//System.out.println("Deposit correctly placed");
												//result2=result2+"<td>PASS</td></tr>";
						                
											} catch (IOException e1) {
												System.out.println("Screenshot Failed");
												System.out.println("-----------------------------------");
											}
										
										}
									}else{
									
										System.out.println("Surname not present in Receipt");
										success=1;
										result=result+"<p>Surname not present in Receipt<p>";
										try {
							                
											takesc(screenshot);
											result=result+"<p>surnme Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
											//System.out.println("Deposit correctly placed");
											//result2=result2+"<td>PASS</td></tr>";
					                
										} catch (IOException e1) {
											System.out.println("Screenshot Failed");
											System.out.println("-----------------------------------");
										}
									
									}
									
								}else{
								
									System.out.println("Transaction Date not present in Receipt");
									success=1;
									result=result+"<p>transaction Date not present in Receipt<p>";
									try {
						                
										takesc(screenshot);
										result=result+"<p>tdate Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
										//System.out.println("Deposit correctly placed");
										//result2=result2+"<td>PASS</td></tr>";
				                
									} catch (IOException e1) {
										System.out.println("Screenshot Failed");
										System.out.println("-----------------------------------");
									}
								
								}
							}else{
							
								System.out.println("Transaction Amount not present in Receipt");
								success=1;
								result=result+"<p>Transaction Amount not present in Receipt<p>";
								try {
					                
									takesc(screenshot);
									result=result+"<p>tamo Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									//System.out.println("Deposit correctly placed");
									//result2=result2+"<td>PASS</td></tr>";
			                
								} catch (IOException e1) {
									System.out.println("Screenshot Failed");
									System.out.println("-----------------------------------");
								}
							
							}
							
						}else{
						
							System.out.println("Authorisation Code not present in Receipt");
							success=1;
							result=result+"<p>Authorisation Code not present in Receipt<p>";
							try {
				                
								takesc(screenshot);
								result=result+"<p>authcode Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
								//System.out.println("Deposit correctly placed");
								//result2=result2+"<td>PASS</td></tr>";
		                
							} catch (IOException e1) {
								System.out.println("Screenshot Failed");
								System.out.println("-----------------------------------");
							}
						
						}
						
					}else{
					
						System.out.println("e-mail not present in Receipt");
						success=1;
						result=result+"<p>e-mail not present in Receipt<p>";
						try {
			                
							takesc(screenshot);
							result=result+"<p>temail Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							//System.out.println("Deposit correctly placed");
							//result2=result2+"<td>PASS</td></tr>";
	                
						} catch (IOException e1) {
							System.out.println("Screenshot Failed");
							System.out.println("-----------------------------------");
						}
					
					}
				
				}else{
				
					System.out.println("Merchant Name not present in Receipt");
					success=1;
					result=result+"<p>Merchant Name not present in Receipt<p>";
					try {
		                
						takesc(screenshot);
						result=result+"<p>mname Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
						//System.out.println("Deposit correctly placed");
						//result2=result2+"<td>PASS</td></tr>";
                
					} catch (IOException e1) {
						System.out.println("Screenshot Failed");
						System.out.println("-----------------------------------");
					}
				
				}
				}else{ //!contains labels
					
					if(driver.findElement(By.cssSelector(merchant)).isDisplayed() && driver.findElement(By.cssSelector(merchant)).getText().toLowerCase().contains(merchtxt)){
						
						if(driver.findElement(By.cssSelector(email)).isDisplayed() && driver.findElement(By.cssSelector(email)).getText().toLowerCase().contains(emailtxt)){
							
							if(driver.findElement(By.cssSelector(auth)).isDisplayed() && driver.findElement(By.cssSelector(auth)).getText().toLowerCase().contains(autcodtxt)){
								
								if(driver.findElement(By.cssSelector(trans)).isDisplayed() && driver.findElement(By.cssSelector(trans)).getText().toLowerCase().contains(transamtxt)){
									
									if(driver.findElement(By.cssSelector(tdate)).isDisplayed() && driver.findElement(By.cssSelector(tdate)).getText().toLowerCase().contains(transdattxt)){
								
										if(driver.findElement(By.cssSelector(surname)).isDisplayed() && driver.findElement(By.cssSelector(surname)).getText().toLowerCase().contains(surnatxt)){
										
											if(driver.findElement(By.cssSelector(ttype)).isDisplayed() && driver.findElement(By.cssSelector(ttype)).getText().toLowerCase().contains(trantytxt)){
											
												if(driver.findElement(By.cssSelector(tid)).isDisplayed() && driver.findElement(By.cssSelector(tid)).getText().toLowerCase().contains(transidtxt)){
												
																																														
												
									                
													takesc(screenshot);
									                result=result+"<p>Screenshot for the deposit <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									                System.out.println("Deposit correctly placed");
									                System.out.println("-----------------------------------");
									    			//result2=result2+"<td>PASS</td></tr>";
									                
									           											
																					
												}else{
												
												System.out.println("Transaction Id not present in Receipt");
												success=1;
												result=result+"<p>Transaction Id not present in Receipt<p>";
												
									                
													takesc(screenshot);
													result=result+"<p>tid Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
													//System.out.println("Deposit correctly placed");
													//result2=result2+"<td>PASS</td></tr>";
							                
												
												}
											}else{
											
												System.out.println("Transaction Type not present in Receipt");
												success=1;
												result=result+"<p>Transaction Type not present in Receipt<p>";
												
												try {
									                
													takesc(screenshot);
													result=result+"<p>ttype Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
													//System.out.println("Deposit correctly placed");
													//result2=result2+"<td>PASS</td></tr>";
							                
												} catch (IOException e1) {
													System.out.println("Screenshot Failed");
													System.out.println("-----------------------------------");
												}
											
											}
										}else{
										
											System.out.println("Surname not present in Receipt");
											success=1;
											result=result+"<p>Surname not present in Receipt<p>";
											try {
								                
												takesc(screenshot);
												result=result+"<p>surnme Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
												//System.out.println("Deposit correctly placed");
												//result2=result2+"<td>PASS</td></tr>";
						                
											} catch (IOException e1) {
												System.out.println("Screenshot Failed");
												System.out.println("-----------------------------------");
											}
										
										}
										
									}else{
									
										System.out.println("Transaction Date not present in Receipt");
										success=1;
										result=result+"<p>transaction Date not present in Receipt<p>";
										try {
							                
											takesc(screenshot);
											result=result+"<p>tdate Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
											//System.out.println("Deposit correctly placed");
											//result2=result2+"<td>PASS</td></tr>";
					                
										} catch (IOException e1) {
											System.out.println("Screenshot Failed");
											System.out.println("-----------------------------------");
										}
									
									}
								}else{
								
									System.out.println("Transaction Amount not present in Receipt");
									success=1;
									result=result+"<p>Transaction Amount not present in Receipt<p>";
									try {
						                
										takesc(screenshot);
										result=result+"<p>tamo Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
										//System.out.println("Deposit correctly placed");
										//result2=result2+"<td>PASS</td></tr>";
				                
									} catch (IOException e1) {
										System.out.println("Screenshot Failed");
										System.out.println("-----------------------------------");
									}
								
								}
								
							}else{
							
								System.out.println("Authorisation Code not present in Receipt");
								success=1;
								result=result+"<p>Authorisation Code not present in Receipt<p>";
								try {
					                
									takesc(screenshot);
									result=result+"<p>authcode Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									//System.out.println("Deposit correctly placed");
									//result2=result2+"<td>PASS</td></tr>";
			                
								} catch (IOException e1) {
									System.out.println("Screenshot Failed");
									System.out.println("-----------------------------------");
								}
							
							}
							
						}else{
						
							System.out.println("e-mail not present in Receipt");
							success=1;
							result=result+"<p>e-mail not present in Receipt<p>";
							try {
				                
								takesc(screenshot);
								result=result+"<p>temail Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
								//System.out.println("Deposit correctly placed");
								//result2=result2+"<td>PASS</td></tr>";
		                
							} catch (IOException e1) {
								System.out.println("Screenshot Failed");
								System.out.println("-----------------------------------");
							}
						
						}
					
					}else{
					
						System.out.println("Merchant Name not present in Receipt");
						success=1;
						result=result+"<p>Merchant Name not present in Receipt<p>";
						try {
			                
							takesc(screenshot);
							result=result+"<p>mname Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							//System.out.println("Deposit correctly placed");
							//result2=result2+"<td>PASS</td></tr>";
	                
						} catch (IOException e1) {
							System.out.println("Screenshot Failed");
							System.out.println("-----------------------------------");
						}
					}
				}
				}catch(Exception e1){
				
					if(i==paymethod.length){
					System.out.println("Something wrong with deposit button");
					System.out.println("-----------------------------------");
					success=1;
					result=result+"<p>IBNDeposit: Deposit Button not found<p>";
					try {
		                
						takesc(screenshot);
						result=result+"<p>paymbutt Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
						//System.out.println("Deposit correctly placed");
						//result2=result2+"<td>PASS</td></tr>";
                
					} catch (IOException e2) {
						System.out.println("Screenshot Failed");
						System.out.println("-----------------------------------");
					}
					}
											
				}
				
				try{
					
					if(!batchid.contains("labels")){
						driver.findElement(By.cssSelector(paymethod[i][0])).click();
					}else{
						driver.findElement(By.cssSelector("[qa='tplaynow']")).click();
						//driver.findElement(By.cssSelector("[id='submit']")).click();
					}
					
					System.out.println("Play Now Button Clicked");
					System.out.println("-----------------------------------");
					
					//Thread.sleep(1000);
					try{
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("BODY")));
					}catch(Exception e){
		    			
		    		}
					//System.out.println(driver.getCurrentUrl().toString());
					if(driver.getCurrentUrl().toString().contains("lobby")||driver.getCurrentUrl().toString().contains("home")){
						
						//if(driver.getPageSource().contains(logname)){
						if(driver.findElement(By.cssSelector("[id='the_usernameright']")).getText().equals(logname)){
						
						System.out.println("User successfully redirected to Lobby Page");
						System.out.println("-----------------------------------");
						
						screenshot = "target/screenshots/deposithome" + timesta + ".png";
						
						try {
			                
							takesc(screenshot);
			                result=result+"<p>Screenshot for the redirection <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
			                //System.out.println("Deposit correctly placed");
			                //System.out.println("-----------------------------------");
			    			//result2=result2+"<td>PASS</td></tr>";
			                break;
			            } catch (IOException e1) {
			                System.out.println("Screenshot Failed");
			                System.out.println("-----------------------------------");
			            }
						
						
						
						}else{
							System.out.println("User Name not present in Lobby");
							System.out.println("-----------------------------------");
							success=1;
							result=result+"<p>User Name not present in Lobby<p>";
							try {
				                
								takesc(screenshot);
								result=result+"<p>unamelobby Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
								//System.out.println("Deposit correctly placed");
								//result2=result2+"<td>PASS</td></tr>";
		                
							} catch (IOException e1) {
								System.out.println("Screenshot Failed");
								System.out.println("-----------------------------------");
							}
						
						}
						
					}else{
						
						System.out.println("Redirection after payment does not work well");
						System.out.println("-----------------------------------");
						success=1;
						result=result+"<p>Redirection After payment did not happen<p>";
						try {
			                
							takesc(screenshot);
							result=result+"<p>redirection Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							//System.out.println("Deposit correctly placed");
							//result2=result2+"<td>PASS</td></tr>";
	                
						} catch (IOException e1) {
							System.out.println("Screenshot Failed");
							System.out.println("-----------------------------------");
						}
					
					}
				
					
				
						
					
				}catch(Exception e1){
					
					System.out.println("Something wrong with Play Now button");
					System.out.println("-----------------------------------");
					success=1;
					result=result+"<p>Play Now button failed<p>";
					try {
		                
						File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(scrFile, new File(screenshot));
						result=result+"<p>playnow Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
						//System.out.println("Deposit correctly placed");
						//result2=result2+"<td>PASS</td></tr>";
                
					} catch (IOException e2) {
						System.out.println("Screenshot Failed");
						System.out.println("-----------------------------------");
					}
				
				}
			
			}
			
		}
		
				
		if(success==0){
			result2=result2+"<td>PASS</td></tr>";
			ibnwithdrawl(paymentcss,logname);
		}else{
			result2=result2+"<td>FAILED</td></tr>";
			overall="FAILED";
		}
		finished=finished+1;
	}
	
	
	public void takesc(String screenshot) throws Exception{
		
		
		started=started+1;
		/*
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "png", new File(screenshot));*/
		/*
		try {
	        WebDriver augmentedDriver = new Augmenter().augment(driver);
	        File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(source, new File(screenshot)); 
	    }
	    catch(IOException e) {
	        System.out.println("Failed to capture screenshot: " + e.getMessage());
	    }*/
	    
		/*
		if (browser.equals("ie")){
			
			Screenshot sc = ((InternetExplorerDriver)driver).GetScreenshot();
			sc.SaveAsFile(screenshot, ImageFormat.Png);
			
		}else{
			*/
		try{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(screenshot));
		}catch(Exception e1){
			System.out.println("Screenshot Failed");
		}
		//}
		
		//result=result+"<p>Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
	
		   finished=finished+1;
	}
	
	public void ibnl2(String logname,String email,String l2test) throws Exception{
		
		started=started+1;
		System.out.println("-----------------------------------");
		System.out.println("Starting IBN L2 Test");
		System.out.println("-----------------------------------");
		
		String screenshot = "target/screenshots/IBNL2" + timesta + ".png";
		String phonecss,streetcss,housecss,postcodecss,citycss,answercss,nextbuttoncss,paymentcss;
		String phone,street,house,postcode,city,answer;
		//System.out.println(l2test);
		String testtoget="";
		String testid="";
		String paytest="";
		
		//result=result+"<p><h3>" + l2test + " IBN L2 TEST</h3></p><p></p>";
		result=result+"<p><h3>IBN L2 TEST</h3></p><p></p>";
		String what="";
		int success=0;
		//System.out.println(l2test);
		stat3= con.createStatement();
		stat4=con.createStatement();
		stat=con.createStatement();
		//result2=result2+"<tr><td>"+ l2test+"</td>";
		result2=result2+"<tr><td>L2 Step 1 Test</td>";
		
		l2rs1= stat3.executeQuery("select * from tests where testid='"+l2test+"'");
		l2rs1.first();
		
		String testk=l2rs1.getString("testkind");
		//System.out.println(testk);
				
		if(testk.equals("ibnl2chk")||testk.equals("l2paycheck")){
			
			stat3.clearBatch();
			l2rs1= stat3.executeQuery("select * from ibnl2paymentcheck where testid='"+l2test+"'");
			l2rs1.first();
			testtoget=l2rs1.getString("testtoget");
			testid=l2rs1.getString("testid");
			l2rs2=stat.executeQuery("select * from ibnl2straight where testid='" + testtoget +"'");
			l2rs2.first();
			what="checkonly";
			//System.out.println(testid);
		}
			
		paymentcss="[qa='netellerdeposit']";
				
		if(testk.equals("ibnl2str")){
			
			
			l2rs2=stat.executeQuery("select testid from batch where batchid='" + batchid +"' and testid like '%pay%'");
			
			try{
			
				l2rs2.first();
				paytest=l2rs2.getString("testid");

				if(paytest.toLowerCase().contains("neteller")){
					
					paymentcss="[qa='netellerdeposit']";
					
				}
				
				
			}catch(Exception e){
			
				System.out.println("There is no payment test");
			
			}
			
			testid=l2rs1.getString("testid");
			l2rs2=stat.executeQuery("select * from ibnl2straight where testid='" + testid  +"'");
			l2rs2.first();
			what="YES";
			
			//System.out.println(testid);
			
		}
		
		//Get all the data from database
		
		//phonecss="input[name='newPlayer.address.homePhone']";
		System.out.println("Acquiring IBN L2 Commom data");
		System.out.println("-----------------------------------");
		phonecss=l2rs2.getString("phone");
		phonecss=phonecss.replaceAll("¬","'");
		//streetcss="input[name='newPlayer.address.address1']";
		streetcss=l2rs2.getString("street");
		streetcss=streetcss.replaceAll("¬","'");
		//housecss="input[name='newPlayer.address.address2']";
		housecss=l2rs2.getString("house");
		housecss=housecss.replaceAll("¬","'");
		//postcodecss="input[name='newPlayer.address.pincode']";
		postcodecss=l2rs2.getString("postcode");
		postcodecss=postcodecss.replaceAll("¬","'");
		//citycss="input[name='newPlayer.address.town']";
		citycss=l2rs2.getString("city");
		citycss=citycss.replaceAll("¬","'");
		//answercss="input[name='newPlayer.personalInformation.securityAnswerName']";
		answercss=l2rs2.getString("answer");
		answercss=answercss.replaceAll("¬","'");
		//nextbuttoncss="#next1";
		nextbuttoncss=l2rs2.getString("nextbutton");
		nextbuttoncss=nextbuttoncss.replaceAll("¬","'");
		//paymentcss="#netellerButton";
		//paymentcss=l2rs2.getString("payment");
		//paymentcss=paymentcss.replaceAll("¬","'");
		System.out.println("IBN L2 Commom data adquired");
		System.out.println("-----------------------------------");
		String Loadmask="/html/body/div[@id='wrapper']/div[@id='full_col']/div[@id='main_col']/div[@id='contentPanel']/div[@class='innerpanelContainer']/div[@class='innerpanel']/div[@id='cmsPayContainer']/form[@id='netellerdepositform']/div[@class='loadmask-msg']/div";
		phone="11111111111";
		street="QA street";			//Default Values
		house="QA Number";
		postcode="m333aj";
		city="Manchester";
		answer="Pino";
		
		Thread.sleep(1000);
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(nextbuttoncss)));
		}catch(Exception e){
			
		}
		
		int count=0;
		String paystring ="";
		if(language.equals("english")){
			
			paystring="payment";
		}
		
		if(language.equals("norwegian")){
			
			paystring="betalingsmåte";
		}
		
		if(language.equals("swedish")){
			
			paystring="BETALNINGSMETODER";
		}
		
		paystring=paystring.toLowerCase();
		
		do{
		try{
			
			driver.findElement(By.cssSelector(phonecss)).clear();
			driver.findElement(By.cssSelector(phonecss)).sendKeys(phone);
			
		}catch(Exception e1){
			
			System.out.println("phone field not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> Phone Field Failed</p>";
			
		}
		
		try{
			
			driver.findElement(By.cssSelector(streetcss)).clear();
			driver.findElement(By.cssSelector(streetcss)).sendKeys(street);
			
		}catch(Exception e1){
			
			System.out.println("street field not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> Street Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(housecss)).clear();
			driver.findElement(By.cssSelector(housecss)).sendKeys(house);
			
		}catch(Exception e1){
			
			System.out.println("House field not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> House Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(postcodecss)).clear();
			driver.findElement(By.cssSelector(postcodecss)).sendKeys(postcode);
			
		}catch(Exception e1){
			
			System.out.println("postcode field not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> Post Code Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(citycss)).clear();
			driver.findElement(By.cssSelector(citycss)).sendKeys(city);
			
		}catch(Exception e1){
			
			System.out.println("city field not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> City Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(answercss)).clear();
			driver.findElement(By.cssSelector(answercss)).sendKeys(answer);
			//System.out.println("answer");
		}catch(Exception e1){
			
			System.out.println("answer field not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> Answer Field Failed</p>";
		}
		
		Thread.sleep(1000);
		
		try{
			
			driver.findElement(By.cssSelector(nextbuttoncss)).click();
			//System.out.println("Boton");			
		}catch(Exception e1){
			
			System.out.println("Next Button not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p> Next Button Failed</p>";
		}
		
		Thread.sleep(1000);
		count++;
		try{
		
			if(driver.findElement(By.xpath(Loadmask)).isDisplayed()){
			
				break;
				
			}
		}
		
		catch(Exception e21){
			
		}
		
		if(count==4){break;}
		
		}while(!driver.getPageSource().toLowerCase().contains(paystring));//while page not contains # (That means that L2 Step1 is finished)
		//if(overall.equals("FAILED")){result2=result2+"<td>FAILED</td></tr>";
		//overall="FAILED";}
		
		//Thread.sleep(1000);
		System.out.println("-----------------------------------");
		System.out.println("L2 Step1 Completed");
		System.out.println("-----------------------------------");
		result2=result2+"<td>PASS</td></tr>";
		if(success==1){
		
		System.out.println("-----------------------------------");
		System.out.println("L2 Step1 Failed");
		System.out.println("-----------------------------------");
				
		result=result+"<p>L2 Step1 FAILED";
		result2=result2+"<td>FAILED</td></tr>";
		
		takesc(screenshot);
		result=result+"<p>step1 Error Screenshot  <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
		
		}
				
		if (what.equals("YES") && success==0){
		
		
			System.out.println("------------------------");
			System.out.println("Starting "+paytest+" Payment and withdrawal test");
			System.out.println("------------------------");
			result2=result2+"<tr><td>"+paytest+"</td>";
			
			
		try{
			
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(paymentcss)));
			
		}catch(Exception e){
			
		}
	
		try{
			
			driver.findElement(By.cssSelector(paymentcss)).click();
						
		}catch(Exception e1){
			
			System.out.println("Payment Button not found");
			System.out.println("-----------------------------------");
			overall="FAILED";
			success=1;
			result=result+"<p>Payment Button not Found<p>";
			takesc(screenshot);
			result=result+"<p>paybuttdep Error Screenshot  <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
		}
		
		try{
		wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("[id='the_usernameright']"),logname));
		}catch(Exception e){
			
		}
		
		if(driver.findElement(By.cssSelector("[id='the_usernameright']")).getText().equals(logname)){
			
			
			ibndeposit(paymentcss,logname);
			
			//System.out.println("User ==>"+ logname + "<== with email ==>" + email +"<== succesfully registered as L2 'No payment at the moment'");
			//result2=result2+"<td>PASS</td></tr>";
			//String screenshot = "target/screenshots/screenshot" + timesta + ".png";
			
			//
			
		}else{
			
			System.out.println("UserName not showed in deposit page");
			System.out.println("-----------------------------------");
			success=1;
			overall="FAILED";
			result=result+"<p> User Name Not displayed in deposit page</p>";
			
		}
		
		/*
		if(success==1){
			
			System.out.println("-----------------------------------");
			System.out.println("L2 Step2 Failed");
			System.out.println("-----------------------------------");
			
			result=result+"<p>L2 Step2 FAILED<p>";
			result2=result2+"<td>FAILED</td></tr>";
			
			overall="FAILED";
			takesc(screenshot);	}
			result=result+"<p>l2s2 Error Screenshot  <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
		*/
		
		}
		
		if(what.equals("checkonly") && success==0){
			
			//result2=result2+"<td>PASS</td></tr>";
			
			//Start payment methods present and functional
			
			//String fimgcss, fdepositcss, simgcss,sdepositcss,timgcss,tdepositcss,foimgcss,fodepositcss,fiimgcss,fidepositcss,siimgcss,sidepositcss; 
			//String fname,sname,tname,foname,finame,siname;
			String chkicon,chkbutton,chktext;
			
			String testfbatch="";
			String kindtest="";
			l2rs2=stat.executeQuery("select * from batch where batchid='"+batchid+"'");
			l2rs2.beforeFirst();
			
			while(l2rs2.next()){
				testfbatch=l2rs2.getString("testid");
				System.out.println(testfbatch);
				l2rs1=stat3.executeQuery("select * from tests where testid='" + testfbatch +"'");
				l2rs1.first();
				kindtest=l2rs1.getString("testkind");
				System.out.println(kindtest);
				
			if(kindtest.equals("l2paycheck")){
			
			result2=result2+"<tr><td>"+testfbatch+"</td>";
			l2rs3=stat2.executeQuery("select * from ibnl2paymentcheck where testid='" + testfbatch +"'");
			l2rs3.beforeFirst();
			
			while(l2rs3.next()){
			
				//fimgcss="img[alt='Deposit With Cards']";
				//fdepositcss="#cardButton";
				//simgcss="img[alt='Open Skrill (Moneybookers) account.']";
				//sdepositcss="#moneybookersButton";
				//timgcss="img[alt='Open UKASH account']";
				//tdepositcss="#ukashButton";
				//foimgcss="img[alt='Open Neteller account']";
				//fodepositcss="#netellerButton";
				//fiimgcss="img[alt='Deposit With Paypal']";
				//siimgcss="img[alt='Deposit by Paysafe']";
				//fidepositcss="#bankingButton";
				//sidepositcss="//table[6]/tbody/tr/td[3]/span/a/img";
			
				//fname="VISA";
				//sname="Skrill";
				//tname="Ukash";
				//foname="Neteller";
				//finame="Paypal";
				//siname="Paysafe";
			
			//1st payment checking

				chkicon=l2rs3.getString("icon");
				chkicon=chkicon.replaceAll("¬","'");
				chkbutton=l2rs3.getString("button");
				chkbutton=chkbutton.replaceAll("¬","'");
				chktext=l2rs3.getString("texttocheck");
				chktext=chktext.replaceAll("¬","'");
			
				result=result+"<p>-----------------------------<p>";
				result=result+"<p>"+chktext+" Payment Method Checking<p>";
				result=result+"<p>-----------------------------<p>";
				
				System.out.println(chkbutton);
				try{
				
					if(!chkbutton.contains("//")){
						
						
						try{
							
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(chkbutton)));
							if(driver.findElement(By.cssSelector(chkicon)).isDisplayed()){
							System.out.println("looking icon");
					
								try{
						
									
									System.out.println("looking button");
									Thread.sleep(1000);
									int j=0;
									
									
									while(j<=5){
										
										driver.findElement(By.cssSelector(chkbutton)).click();
										
										try{
										
											if(!driver.findElement(By.cssSelector("[qa='dbutton']")).isDisplayed()){
											
												try{
																								
													driver.findElement(By.cssSelector(chkbutton)).click();
											
												}catch(Exception e21){
												
													System.out.println("Deposit button not reachable");
												}
										
											}else{
												break;
											}
									
									
										}catch(Exception e40){
											System.out.println("deposit button not in ... try("+j+")");
											driver.navigate().refresh();
											j++;
											Thread.sleep(1000);
										}
									}
									
							
							System.out.println("In payment page");
								


							
							String source=driver.getPageSource().toLowerCase();
							chktext=chktext.toLowerCase();
							if(source.contains(chktext)){
							
								System.out.println("Payment Method ==" + chktext + "== Present");
								System.out.println("-----------------------------------");
							
								if(driver.getPageSource().contains(logname)){
								
									System.out.println("User Name ==" + logname + "== Present");
									System.out.println("Payment Name ==" + chktext + "== Present");
									System.out.println("-----------------------------------");
									success=paymenterrorcheck(chktext,success);
									//System.out.println("Success after payment check===>"+success);
									//result2=result2+"<td>PASS</td></tr>";
									screenshot = "target/screenshots/" + chktext + timesta + ".png";
									
									takesc(screenshot);
									
																		
									if(success==0){
									result=result+"<p>"+chktext+" Payment OK</p>";
									result=result+"<p>Screenshot for this payment <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									result2=result2+"<td>PASS<td></tr>";
									}else{
									result=result+"<p>"+chktext+" Payment FAILED</p>";
									result=result+"<p>payfail Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									}
									
								
								}else{
								
									System.out.println("User Name ==" + logname + "== Not Present");
									System.out.println("-----------------------------------");
								//	result2=result2+"<td>FAILED</td></tr>";
									overall="FAILED";
									success=1;
									result=result+"<p> User Name Not displayed in "+chktext+" payment method</p>";
									result2=result2+"<td>FAILED<td></tr>";
									takesc(screenshot);
									result=result+"<p>pchklogname Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";}
								
							
							}else{
							
								System.out.println("Payment Method ==" + chktext + "== Error");
								System.out.println("-----------------------------------");
							//	result2=result2+"<td>FAILED</td></tr>";
								overall="FAILED";
								success=1;
								result=result+"<p>Payment Name Not displayed in "+chktext+" payment method</p>";
								result2=result2+"<td>FAILED<td></tr>";
								takesc(screenshot);
								result=result+"<p>pcheckpname Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							}
								
						
						
						}catch(Exception e1){
					
							System.out.println(chktext+" Deposit button error");
							System.out.println("-----------------------------------");
						//	result2=result2+"<td>FAILED</td></tr>";
							overall="FAILED";
							success=1;
							result=result+"<p> Depossit button failed in "+chktext+" payment method</p>";
							result2=result2+"<td>FAILED<td></tr>";
							takesc(screenshot);
							result=result+"<p>pchkdbutt Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
						}
				
					}else{
					//	result2=result2+"<td>FAILED</td></tr>";
						overall="FAILED";
						success=1;
						System.out.println("Icon not displayed");
						System.out.println("-----------------------------------");
						result=result+"<p>ICON Not displayed for "+chktext+" payment method</p>";
						result2=result2+"<td>FAILED<td></tr>";
						takesc(screenshot);
						result=result+"<p>pchkicon Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
					}
						}catch(Exception e2){
							overall="FAILED";
							result=result+"<p> ICON CHECKING FAILED <p>";
							result2=result2+"<td>FAILED<td></tr>";
							success=1;
							takesc(screenshot);
							result=result+"<p>pchkicon2 Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							
						}
					}else{
						
						try{
						//chkbutton=chkbutton.toUpperCase();
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(chkbutton)));
							if(driver.findElement(By.cssSelector(chkicon)).isDisplayed()){
							
							try{
							
								driver.findElement(By.xpath(chkbutton)).click();
								Thread.sleep(1000);
							
								String source=driver.getPageSource().toLowerCase();
								chktext=chktext.toLowerCase();								
								if(source.contains(chktext)){
								
									System.out.println("Payment Method ==" + chktext + "== Present");
									System.out.println("-----------------------------------");
								
									
									if(driver.getPageSource().contains(logname)){
									
										System.out.println("User Name ==" + logname + "== Present");
										System.out.println("Payment Name ==" + chktext + "== Present");
										System.out.println("-----------------------------------");
										success=paymenterrorcheck(chktext,success);
										//result2=result2+"<td>PASS</td></tr>";
										screenshot = "target/screenshots/" + chktext + timesta + ".png";
										
										result2=result2+"<td>PASS<td></tr>";
										takesc(screenshot);
										result=result+"<p>Screenshot for this payment <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";

										result=result+"<p>"+chktext+" Payment OK</p>";
									
									}else{
									
										System.out.println("User Name ==" + logname + "== Not Present");
										System.out.println("-----------------------------------");
										//result2=result2+"<td>FAILED</td></tr>";
										overall="FAILED";
										success=1;
										result=result+"<p> User Name Not displayed in "+chktext+" payment method</p>";
										result2=result2+"<td>FAILED<td></tr>";
										takesc(screenshot);
										result=result+"<p>logname Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
									}
								
								}else{
								
									System.out.println("Payment Method ==" + chktext + "== Error");
									System.out.println("-----------------------------------");
								//	result2=result2+"<td>FAILED</td></tr>";
									overall="FAILED";
									success=1;
									result=result+"<p> Payment Name Not displayed in "+chktext+" payment method</p>";
									result2=result2+"<td>FAILED<td></tr>";
									takesc(screenshot);
									result=result+"<p>payname Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
								}
									
							
							
							}catch(Exception e1){
						
								System.out.println(chktext+" Deposit button error");
								System.out.println("-----------------------------------");
							//	result2=result2+"<td>FAILED</td></tr>";
								overall="FAILED";
								success=1;
								result=result+"<p> Deposit Button Failed in "+chktext+" payment method</p>";
								result2=result2+"<td>FAILED<td></tr>";
								takesc(screenshot);	
								result=result+"<p>depbutt Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							}
					
						}else{
						//	result2=result2+"<td>FAILED</td></tr>";
							overall="FAILED";
							success=1;
							System.out.println("Payment ICON not displayed");
							System.out.println("-----------------------------------");
							result=result+"<p> ICON Not displayed for "+chktext+" payment method</p>";
							result2=result2+"<td>FAILED<td></tr>";
							takesc(screenshot);
							result=result+"<p>payicon Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
							
						}}catch(Exception e2){
							overall="FAILED";
							result=result+"<p> ICON CHECKING FAILED <p>";
							result2=result2+"<td>FAILED<td></tr>";
						}
												
					}
			
				}catch(Exception e1){
				
					System.out.println("Something went wrong in "+chktext+" payment method");
					System.out.println("-----------------------------------");
					//result2=result2+"<td>FAILED</td></tr>";
					overall="FAILED";
					success=1;
					result=result+"<p> Something went wrong in "+chktext+" payment method</p>";
					if(success==1){
						
						//System.out.println("-----------------------------------");
						//System.out.println("L2 Step2 Failed");
						//System.out.println("-----------------------------------");
						
						//result=result+"<p>L2 Step2 FAILED";
						takesc(screenshot);	
						result=result+"<p>paymeth Error Screenshot <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
						overall="FAILED";
						}
				}
		
			//if(batchid.contains("labels")){
			
				int p=0;
				try{
				while(driver.findElement(By.cssSelector("[qa='dbutton']")).isDisplayed()){
				try{
				
				driver.findElement(By.cssSelector("[qa='paymentback']")).click();
				System.out.println("looking for correct change card url");
				
				p++;
				System.out.println(p);
				if(p>=6){
					
					driver.navigate().back();
					break;
				}
				}catch(Exception e23){
					System.out.println("Still not");
					System.out.println(driver.getCurrentUrl());
				}}
				}catch(Exception e){
					System.out.println("ok");
				}
			
			/*}else
				
				try{
					driver.findElement(By.cssSelector("[id='paymentLink1']")).click();
				
				
				}catch(Exception e){
					
				}*/
				
				
				//driver.navigate().back();
			
			}
			
			
			try{
				
				if(driver.findElement(By.cssSelector("[id='currentPayments']")).isDisplayed()){
			
					System.out.println("Aqui te queria yo ver");
				
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id='paymentLink1']")));
					}catch(Exception e3){
				
					}
					try{
				
						driver.findElement(By.cssSelector("[id='paymentLink1']")).click();
			
					}catch(Exception e1){
			
			
					}
				}
			}catch(Exception e4){
				
			}
			}}
			
			//System.out.println(success);
		}
			if(success==0){
				
				System.out.println("-----------------------------------");
				System.out.println("IBN L2 Step 2 Completed");
				System.out.println("-----------------------------------");
				//result2=result2+"<td>PASS</td></tr>";
				result=result+"<p>L2 Step2 Successful<p>";
				
			}else{
				
				System.out.println("-----------------------------------");
				System.out.println("IBN L2 Step 2 Failed");
				System.out.println("-----------------------------------");
				result2=result2+"<td>FAILED</td></tr>";
				System.out.println("-----------------------------------");
				System.out.println("L2 Step2 Failed");
				System.out.println("-----------------------------------");
				
				result=result+"<p>L2 Step2 FAILED<p>";
				
				//result2=result2+"<td>FAILED</td></tr>";
			}
			
			finished=finished+1;

		}
	
	public void fieldvalidation (String xpath, String invchars, String testid) throws Exception{
		
		
		//System.out.println(xpath+"    "+invchars+"    "+testid);
		
		started=started+1;
		boolean succesful=true;
		result=result+"<p><h3>" + testid + " Field Validation</h3></p><p></p>";
		result2=result2+"<tr><td>"+ testid+"</td>";
		
		//invchars=invchars.trim();
		String[] charstouse = new String[invchars.length()];
		String character="";
		charstouse=invchars.split("¬");
		//System.out.println(xpath+"    "+invchars+"    "+testid);
		for(int x=0;x<charstouse.length;x++){
			
			//System.out.println("Begin");
			character="aaa";
			character=character+(char)Integer.parseInt(charstouse[x]);
			//System.out.println(character);
			driver.findElement(By.cssSelector(xpath)).clear();
			//driver.findElement(By.cssSelector(xpath)).sendKeys("aaa");
			//System.out.println(character);
			driver.findElement(By.cssSelector(xpath)).sendKeys(character);
			//System.out.println(character);
			if(x<=0){driver.findElement(By.cssSelector(xpath)).sendKeys(Keys.ENTER);System.out.println(character);}
				
			
			
			String regerr="//div[@class='regerrors']";
			if(!driver.findElement(By.xpath(regerr)).isDisplayed()){
				
				
				succesful=false;
				result=result+"<p>Character ==>" + (char)Integer.parseInt(charstouse[x]) +"<== has failed validation on TEST " + testid +" Char Code=" + charstouse[x]+"</p>";
				//System.out.println((char)Integer.parseInt(charstouse[x])+" Failed to verify");
				
			}
			
			if (x==charstouse.length-1){driver.findElement(By.cssSelector(xpath)).clear();
			driver.findElement(By.cssSelector(xpath)).sendKeys("aaa");}
			
		}
		
		if(succesful){
			
			result=result+"<p>Field validation OK</p><p>------------</p>";
			result2=result2+"<td>PASS</td></tr>";
			//overall="PASS";
			
		}else{
			result2=result2+"<td>FAILED</td></tr>";
			overall="FAILED";
		}
		finished=finished+1;
	}
	
	@Test
	public void l1test(String testid) throws Exception{
		
		
		started=started+1;
		System.out.println("-----------------------------------");
		System.out.println("IBN L1 Registration Test");
		System.out.println("-----------------------------------");
		
		
		String screenshot = "target/screenshots/screenshot" + timesta + ".png";
		String fname,lname,email,day,month,year,next,eighteen,accept,login,password,repassword,fun,realbutton,screen;
		int count=0;
				
		int success=0;
		int find=0;
		
		result=result+"<p><h3>" + testid + " IBN L1 Registration Test</h3></p><p></p>";
		result=result+"<p>---------------------------------------------------</p><p></p>";
			//try{
			
				
				//Class.forName("com.mysql.jdbc.Driver");
			
			
				//con=DriverManager.getConnection("jdbc:mysql://"+servername+"/"+db, username, pass); 
				//}catch(ClassNotFoundException e){
					//System.out.println("Class Not Found: "+e.getMessage());
						
				//}catch(SQLException e){
					//System.out.println(e.getMessage());
	//			}finally{	
		
		stat3= con.createStatement();
		stat4=con.createStatement();
		stat=con.createStatement();
		
		//System.out.println(ls.getString("testkind"));
		l1rs= stat3.executeQuery("select * from l1test where testid='"+testid+"'");
		l1rs.first();
		l1rs2= stat4.executeQuery("select tofind from linktofind where testid='"+testid+"'");
		
		
		
		if(l1rs2 !=null){
			
			l1rs2.beforeFirst();
			l1rs2.last();
			count=l1rs2.getRow(); }
		
		String[] link= new String[count];
		
		//System.out.println(count);
		
		if(count>=1){
		
		
			int row=0;
			
			//System.out.println(row);
			l1rs2.beforeFirst();
			
			while(l1rs2.next()){
				
				link[row]=l1rs2.getString("tofind");				
				link[row]=link[row].replaceAll("¬","'");
				//System.out.println(link[row]);
				row=row+1;
			}
			
		}else{
			
			link[0]=l1rs.getString("link");
			link[0]=link[0].replaceAll("¬","'");
		}
		
		
		
		fname=l1rs.getString("fname");
		fname=fname.replaceAll("¬","'");
		lname=l1rs.getString("lname");
		lname=lname.replaceAll("¬","'");
		//System.out.println(lname);
		email=l1rs.getString("email");
		email=email.replaceAll("¬","'");
		//System.out.println(email);
		day=l1rs.getString("day");
		day=day.replaceAll("¬","'");
		//System.out.println(day);
		month=l1rs.getString("month");
		month=month.replaceAll("¬","'");
		//System.out.println(month);
		year=l1rs.getString("year");
		year=year.replaceAll("¬","'");
		//System.out.println(year);
		next=l1rs.getString("next");
		next=next.replaceAll("¬","'");
		//System.out.println(next);
		eighteen=l1rs.getString("eighteen");
		eighteen=eighteen.replaceAll("¬","'");
		//System.out.println(eighteen);
		accept=l1rs.getString("accept");
		accept=accept.replaceAll("¬","'");
		//System.out.println(accept);
		login=l1rs.getString("login");
		login=login.replaceAll("¬","'");
		//System.out.println(login);
		password=l1rs.getString("password");
		password=password.replaceAll("¬","'");
		//System.out.println(password);
		repassword=l1rs.getString("repassword");
		repassword=repassword.replaceAll("¬","'");
		//System.out.println(repassword);
		fun=l1rs.getString("fun");
		fun=fun.replaceAll("¬","'");
		//System.out.println(fun);
		realbutton=l1rs.getString("realbutton");
		realbutton=realbutton.replaceAll("¬","'");
		//System.out.println(realbutton);
		screen="input[id='nicknameform_input']";
		//screen=screen.toUpperCase();
		String enterbutton="input[id='nicknameform_bt']";
		//enterbutton=enterbutton.toUpperCase();
		//System.out.println(link + "\n"+fname+ "\n"+lname+ "\n"+email+ "\n"+day+ "\n"+month+ "\n"+year+ "\n"+next+ "\n"+eighteen+ "\n"+accept+ "\n"+login+ "\n"+password+ "\n"+fun+ "\n"+realbutton);

		
		System.out.println("Looking for Registration Link");
		System.out.println("-----------------------------------");
		
		
		int z=0;
		//System.out.println(z);
		do{
			
			if(!regcss.equals("")){link[z]=regcss;}
			System.out.println(link[z]);
			if(find==0){
			//System.out.println(z+"======"+count);
			try {
			
				
				
				driver.findElement(By.cssSelector(link[z]));
				if(driver.findElement(By.cssSelector(link[z])).isDisplayed()){
				success=0;}
				//System.out.println(link[z]);
				//System.out.println(z);
				
				
				
				
			} catch (Exception e1){
	    		
				success=1;
				//System.out.println("This not");
				//Control different spelling for Contact Us Link
				if(z==count-1){
				System.out.println("Register Link not found");
				System.out.println("-----------------------------------");
				result2=result2+"<tr><td>"+testid+"</td>";
				result2=result2+"<td>FAILED</td></tr>";
				result=result+"<p>Registration Link FAILED<p>";
				takesc(screenshot);
				result=result+"<p> Click on the screenshot to see it larger <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
				overall="FAILED";
	    		}
				//result=(result + "<p><FONT COLOR="+(char)34+"red"+(char)34+">"+ss.getString("tofind")+" Not Finded</FONT><p>");} 
	       		//If no Contact Us 
	    	
			} finally{
	    	
				if (success==0){
	    		
	    		//Random rand = new Random();
	    		
	    		
					System.out.println("Registration Link Successfully Finded");
					
					
				
				try{
	    		
	    			
				//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    		driver.findElement(By.cssSelector(link[z])).click();
	    		//String linkurl = clicklink.getAttribute("href");
	    		//linkurl=linkurl.replace("http://","https://4646:4646@");
	    		//driver.get(linkurl);
	    		//System.out.println(linkurl);
	    		
	    		
	    		
	    		
	    		//System.out.println("Register Clicked");
	    		
	    		
	    		//Thread.sleep(500);
	    		//sendkeys();
	    			    		
	    		}catch(Exception e){
	    			System.out.println(e);
	    			success=1;
	    			
	    		}
				if(driver.getCurrentUrl().toString().contains("registration")){
					find=1;	
				if (success==0){
	    		//List<WebElement> emailerror = driver.findElements(By.xpath("//div[@id='registration_colA']/div[@id='regerrors'][1]"));
	    		
	    		//String genmail="Daniel@hh.com";
	    		
					regcss=link[z];
					WebDriverWait wait = new WebDriverWait(driver, 30);
	    		
	    		//System.out.println("Sigue");
	    		System.out.println(driver.getCurrentUrl().toString());
	    		System.out.println("-----------------------------------");
	    		String txtxpath;
	    		
	    		
	    		stat2.clearBatch();
	    		l1rs2=stat2.executeQuery(" select testid from batch where batchid='"+ batchid +"'");
	    		
	    		if (l1rs2 !=null){
	    			
	    			l1rs2.beforeFirst();
	    			
	    			while(l1rs2.next()){
	    				
	    			
	    				l1rs3= stat.executeQuery("select xpath,kind,value,testid from validation where testid='"+ l1rs2.getString("testid") +"' and position='l1s1'");
	    		
	    		
	    				if(l1rs3 !=null){
	    			
	    			
	    					l1rs3.beforeFirst();
	    			
	    					while(l1rs3.next()){
	    				
	    						if(l1rs3.getString("kind").equals("text")){
	    					
	    							txtxpath=l1rs3.getString("xpath");
	    							txtxpath=txtxpath.replaceAll("¬","'");
	    							//	System.out.println(l1rs3.getString("xpath"));
	    							fieldvalidation(txtxpath,l1rs3.getString("value"),l1rs3.getString("testid"));
	    						}
	    					}
	    			
	    		
	    		
	    				}
	    				
	    			}
	    		}
	    		
	    			
	    		
	    		/*
	    		try{
	    		//IWait<IWebDriver> wait2 = new OpenQA.Selenium.Support.UI.WebDriverWait(driver, TimeSpan.FromSeconds(30.00));

	    		//wait2.Until(driver1 => ((IJavaScriptExecutor)driver).ExecuteScript("return document.readyState").Equals("complete"));
	    		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(email)));
	    		}catch(Exception e){
	    			success=1;
	    		}*/
	    		try{
	    		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(next)));
	    		
	    		}catch(Exception e10){
	    			
	    		}
	    		String genmail="QAautomation"+timesta+"@gtech.com";
	    		try{
	    		driver.findElement(By.cssSelector(email)).clear(); 
	    		driver.findElement(By.cssSelector(email)).sendKeys(genmail);
	    		}catch(Exception e){
	    		
	    			success=1;		
	    			
	    		}
	    		//System.out.println("email");
	    		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    		
	    		//while(driver.findElement(By.xpath("//div[@id='registration_colA']/div[@id='regerrors'][1]")).isDisplayed()){ //Check if the e-mail is already registered
	    		
	    		    			
	    			////genmail="Daniel"+rand.nextInt(100000)+"@gg.com";
	    			//genmail="Daniel"+timesta+"@gg.com";
	    			//driver.findElement(By.xpath(email)).clear(); 
		    		//driver.findElement(By.xpath(email)).sendKeys(genmail);
		    		//driver.findElement(By.xpath(lname)).clear(); 
		    		//driver.findElement(By.xpath(lname)).sendKeys("Prado");
	    			//emailerror = driver.findElements(By.xpath("//div[@id='registration_colA']/div[@id='regerrors'][1]"));
	    			//System.out.println("Email already registered");
	    		//}
	    		try{
	    		driver.findElement(By.cssSelector(fname)).clear(); 
	    		driver.findElement(By.cssSelector(fname)).sendKeys("Daniel");
	    		}catch(Exception e){
	    			success=1;
	    		}
	    	    //driver.findElement(By.cssSelector("input[name=\'newPlayer.firstName\']")).clear();
	    	    //driver.findElement(By.cssSelector("input[name=\'newPlayer.firstName\']")).sendKeys("Daniel");
	    		//System.out.println("FName");
	    		try{
	    		driver.findElement(By.cssSelector(lname)).clear(); 
	    		driver.findElement(By.cssSelector(lname)).sendKeys("Prado");
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		//System.out.println("LName");
	    		
	    		
	    		
	    		//driver.findElement(By.xpath(day))
	    		try{
	    		Select daydrop = new Select(driver.findElement(By.cssSelector(day)));
	    		//daydrop.deselectAll();
	    		//daydrop.selectByVisibleText("18");
	    		daydrop.selectByIndex(18);
	    		//System.out.println("Day");
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		try{
	    		Select monthdrop = new Select(driver.findElement(By.cssSelector(month)));
	    		//daydrop.deselectAll();
	    		//monthdrop.selectByVisibleText("Jun");
	    		monthdrop.selectByIndex(6);
	    		//System.out.println("Month");
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		try{
	    		Select yeardrop = new Select(driver.findElement(By.cssSelector(year)));
	    		//daydrop.deselectAll();
	    		//yeardrop.selectByVisibleText("1977");
	    		yeardrop.selectByIndex(10);
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		
	    		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    		try{
	    		driver.findElement(By.cssSelector(next)).click();
	    		}catch(Exception e){
	    			success=1;
	    		}

	    		if(success==0){
	    		System.out.println("L1 Step1 Completed");
	    		System.out.println("-----------------------------------");
	    		}else{
	    			
	    			System.out.println("Something failed in L1 Step1");
		    		System.out.println("-----------------------------------");
		    		result2=result2+"<tr><td>"+testid+"</td>";
    				result2=result2+"<td>FAILED</td></tr>";
    				result=result+"<p>L1 Step 1 FAILED<p>";
    				overall="FAILED";
    				
	    		}
	    		
	    		/*try{
	    			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(realbutton)));
	    		}catch(Exception e){
	    			success=1;
	    		}*/
	    		
	    		int j=0;
	    		
	    		while(j<4){
	    			
	    			try{
	    			if(driver.findElement(By.cssSelector(realbutton)).isDisplayed()){
	    				
	    				break;
	    				
	    			}else{
	    				
	    				Thread.sleep(1000);
	    				j++;
	    				
	    			}
	    			}catch(Exception e1){
	    				
	    			}
	    		}
	    		
	    		String genlogin="mrt"+timesta;
	    		//genlogin="okbingo7";
	    		Thread.sleep(1000);
	    		try{
	    		driver.findElement(By.cssSelector(login)).clear(); 
	    		driver.findElement(By.cssSelector(login)).sendKeys(genlogin);
	    		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		
	    		try{
	    		driver.findElement(By.cssSelector(password)).clear(); 
	    		//driver.findElement(by.cssSelector(password)).sendKeys("111111");
	    		
	    		
	    		//while(driver.findElement(By.xpath("//div[@id='registration_colA']/div[@id='regerrors'][1]")).isDisplayed()){ //Check if the isername is already in use
		    		
	    			
	    		
	    			//genlogin="mrt_test"+rand.nextInt(9999);
	    		//	genlogin="mrt"+timesta;
	    			//genlogin="okbingo7";
	    			//driver.findElement(By.xpath(login)).clear(); 
		    		//driver.findElement(By.xpath(login)).sendKeys(genlogin);
		    		//driver.findElement(By.xpath(password)).clear(); 
		    		//driver.findElement(By.xpath(lname)).sendKeys("111111");
	    			//emailerror = driver.findElements(By.xpath("//div[@id='registration_colA']/div[@id='regerrors'][1]"));
	    			//System.out.println("Username already registered");
	    			    		
	    		//}
	    		
	    		driver.findElement(By.cssSelector(password)).clear(); 
	    		driver.findElement(By.cssSelector(password)).sendKeys("111111");
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		
	    		if(!language.equals("english")){
	    		
	    			try{ //In case that the site have a Retype Password
	    			
	    				driver.findElement(By.cssSelector(repassword)).clear(); 
	    				driver.findElement(By.cssSelector(repassword)).sendKeys("111111");
	    			}catch(Exception e){
	    				//System.out.println(e);
	    			}
	    		
	    		}
	    		
	    		try{
	    		driver.findElement(By.cssSelector(eighteen)).click();
	    		driver.findElement(By.cssSelector(accept)).click();
	    		}catch(Exception e){
	    			success=1;
	    		}
	    		
	    		String l2test="";
	    		String l2present="NO";
	    		stat2.clearBatch();
    			l1rs2=stat2.executeQuery("select testid from batch where batchid='"+ batchid +"'");
    		
    			if (l1rs2 !=null){
    			
    			l1rs2.beforeFirst();
    			
    			while(l1rs2.next()){
    				
    			
    				String l2testid=l1rs2.getString("testid");
    				//System.out.println(l2testid);
    				
    				stat.clearBatch();
    				l1rs3= stat.executeQuery("select testid,testkind from tests where testid='"+ l2testid +"'");
    				l1rs3.first();
    				//System.out.println(l2testid);
    				    		
    				if(l1rs3.getString("testkind").contains("l2")){
    			
    					//System.out.println(l1rs3.getString("testid"));
    					l2test=l2testid;
    					l2present="YES";
    				}
    			
    		
    		
    				}
    				
    			}
    		
    		
	    		
	    		if(l2present.equals("YES")||l2present.equals("checkonly")){
	    			driver.findElement(By.cssSelector(realbutton)).click();
	    			if(success==0){
	    				System.out.println("L1 Step2 Completed");
	    				System.out.println("-----------------------------------");
	    				Thread.sleep(1000);
	    			}else{
	    				
	    				System.out.println("Something failed in L1 Step2");
			    		System.out.println("-----------------------------------");
			    		//result2=result2+"<tr><td>"+testid+"</td>";
	    				//result2=result2+"<td>FAILED</td></tr>";
	    				//result=result+"<p>L1 Step 2 FAILED<p>";
	    				overall="FAILED";
		    				
	    			}
	    			
	    			
	    			try{
	    				
	    				driver.switchTo().alert().accept();
	    				
	    			}catch(NoAlertPresentException e){
	    				
	    			}
	    			
	    			try{
		    			
		    			int p=0;
	    				//driver.switchTo().alert().dismiss();
	    				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(enterbutton)));
	    				try{
	    					
	    					while(!driver.findElement(By.cssSelector(screen)).isDisplayed()){
	    						
	    						p=1;
	    					}
	    				}catch(Exception e){
	    					
	    				}
	    				String screenname=genlogin.replace("mrt", "");
	    			
	    				while(driver.findElement(By.cssSelector(screen)).isDisplayed()){
	    					driver.findElement(By.cssSelector(screen)).clear(); 
	    					driver.findElement(By.cssSelector(screen)).sendKeys(screenname); //Handle Screen name
	    					driver.findElement(By.cssSelector(enterbutton)).click();
	    					Thread.sleep(3000);
	    				}
	    			}catch (Exception e){
	    			
	    				//System.out.println("No screen name required");
	    				//System.out.println("-----------------------------------");
	    				
	    			
	    			}
	    			
	    			
	    			
    				//result=result+"<p> Click on the screenshot to see it larger <a href=../"+screenshot+"><img SRC=../"+screenshot+" width=100 height=100></a><p>";
    				result2=result2+"<tr><td>"+testid+"</td>";
    				if(success==0){
    				result2=result2+"<td>PASS</td></tr>";
    				System.out.println("USER="+genlogin+"----"+"E-Mail="+genmail+"-------"+"Level=1-------Succesfully Registered");
	    			System.out.println("-----------------------------------");
	    			result=result+"<p>USER="+genlogin+"----"+"E-Mail="+genmail+"-------"+"Level=1<p>-------Succesfully Registered";
	    			ibnl2(genlogin,genmail,l2test);
    				}else{
    					result2=result2+"<td>FAILED</td></tr>";
    					result=result+"<p>L1 Step 2 FAILED<p>";
    					overall="FAILED";
    				}
	    			
	    		}else{
	    			
	    			if(success==0){
	    			System.out.println("L1 Step2 Completed");
		    		System.out.println("-----------------------------------");
	    			driver.findElement(By.cssSelector(fun)).click();
	    			}else{
	    				
	    				System.out.println("Something Failed in L1 Step2");
			    		System.out.println("-----------------------------------");
	    				overall="FAILED";
	    				result2=result2+"<td>FAILED</td></tr>";
    					result=result+"<p>L1 Step 2 FAILED<p>";
	    				
	    			}
				
	    		
	    		
	    		
	    			Thread.sleep(1000);
	    		
	    		
	    			
	    		
	    		//try{
	    			
	    			//driver.findElement(By.xpath(enterbutton)).click(); //handle if a message appears vefore screen name
	    		//}catch (Exception e){
	    			
	    			//System.out.println(e);
	    		//}
	    		
	    		
	    			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    			int screenpresent=0;
	    		
	    		
	    			try{
	    				
	    				driver.switchTo().alert().accept();
	    				
	    			}catch(NoAlertPresentException e){
	    				
	    			}
	    			
	    			try{
	    				    				
	    				int p=0;
	    				
	    				try{
	    					
	    					while(!driver.findElement(By.cssSelector(screen)).isDisplayed()){
	    						
	    						p=1;
	    					}
	    				}catch(Exception e){
	    					
	    				}
	    				//driver.switchTo().alert().dismiss();
	    				String screenname=genlogin.replace("mrt", "");
	    				
	    				driver.findElement(By.cssSelector(screen)).clear(); 
	    				driver.findElement(By.cssSelector(screen)).sendKeys(screenname); //Handle Screen name
	    				driver.findElement(By.cssSelector(enterbutton)).click();
	    				screenpresent=1;
	    			}catch (Exception e){
	    			
	    				//System.out.println("No screen name required");
	    				//System.out.println("-----------------------------------");
	    				screenpresent=0;
	    			
	    			}
	    		
	    		
	    		//String currentURL=driver.getCurrentUrl();
    			//driver.wait(500);
	    			if(driver.getPageSource().contains(genlogin)){
    				//System.out.println("User " + genlogin + " with email "+ genmail + " succesfully registered as level 1 user");
	    		
    				stat3.executeUpdate("insert into testuser(username,email,level) values('" + genlogin + "','"+genmail+"','1')");
	    		
    				System.out.println("-----------------------------------");
    				System.out.println("User " + genlogin + " with email "+ genmail + " succesfully registered as level 1 user");
    				System.out.println("-----------------------------------");
    				
    				screenshot = "target/screenshots/screenshot" + timesta + ".png";
    				
    				while(screenpresent==1){
    				
    						
    					if(driver.findElement(By.xpath(screen)).isDisplayed()){ 
    						screenpresent=1;}else{    						
    						screenpresent=0;
    					}
    					
    				}
    				
    				takesc(screenshot);
    				
    				result=result+"<p>USER="+genlogin+"----"+"E-Mail="+genmail+"-------"+"Level=1<p>-------Succesfully Registered";
    				result=result+"<p> Click on the screenshot to see it larger <a href=../../"+screenshot+"><img SRC=../../"+screenshot+" width=100 height=100></a><p>";
    				result2=result2+"<tr><td>"+testid+"</td>";
    				result2=result2+"<td>PASS</td></tr>";
    				//overall="PASS";
    				//System.out.println(result + "------"+ result2);
    			}else{
    				
    				result=result+"<p>Something Fails in L1 registration<p>";
    				result2=result2+"<tr><td>"+testid+"</td>";
    				result2=result2+"<td>FAILED</td></tr>";
    				result=result+"<p>L1 Registration FAILED<p>";
    				overall="FAILED";
    			}}
	    		
	    		
	    		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    		//driver.findElement(By.xpath(month)).selectByVisibleText("jun");
	    		
	    		//driver.findElement(By.xpath(year)).selectByVisibleText("1977");
	    		
	    		
	    		
	    		

	    		
	    	}
				break;	}}
		//driver.close();
		//driver.quit();
				}}z=z+1;
	    }while(z!=count);
//	}
		finished=finished+1;
}
	
	
	public void single(String testid) throws Exception{
		
		started=started+1;
		result="";
		
		stat3= con.createStatement();
		stat4= con.createStatement();
		
		//System.out.println(testid);
		//System.out.println(ls.getString("testkind"));
		String cus=""; 
		int success=0;
		
			try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			
			
			con=DriverManager.getConnection("jdbc:mysql://"+servername+"/"+db, username, pass); 
			}catch(ClassNotFoundException e){
				System.out.println("Class Not Found: "+e.getMessage());
						
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}	

		//System.out.println(ls.getString("testkind"));
		ss= stat3.executeQuery("select * from linktofind where testid='"+testid+"'");
		ss.last();
		//System.out.println(ss.getRow());
		
	    // Looking for a Contact Us Link
	    
	    //System.out.println("result="+result);
		result=result+"<p><p><strong><h2>"+ss.getString("testid")+" TEST</h2></strong><p><p>";
		result=result+separator;
		ss.beforeFirst();
		int find=0;
		int nolink=0;
		while(ss.next()){
		
		//System.out.println(ss.getString("tofind"));
			try {
			success=0;
			cus=ss.getString("tofind");
			driver.findElement(By.linkText(cus));
	    } catch (Exception e1){
	    		success=1;					//Control different spelling for Contact Us Link
	    		
	    		if(ss.isLast() & nolink<=0){
	    		result=(result + "<p><FONT COLOR="+(char)34+"red"+(char)34+">"+ss.getString("tofind")+" Not Finded</FONT><p>");} 
	       		//If no Contact Us 
	    	
	    } finally{
	    	
	    	if (success==0 & find==0){
	    		//If a Contact Us finded
	    		nolink=1;
	    		find=1;
	    		result=(result + "<p><strong><h3>"+ss.getString("tofind")+"</strong><FONT COLOR="+(char)34+"green"+(char)34+">"+" Present</FONT></h3><p>");
	    		driver.findElement(By.linkText(cus)).click(); //Click on it
	    		System.out.println("Link finded");
	    		try {
					Thread.sleep(3000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	    		
	    		//String source="";
	    		//URL site = new URL(driver.getCurrentUrl());
		    	//BufferedReader in = new BufferedReader(
		    	  //         new InputStreamReader(
		    	    //        site.openStream()));

		    	//String inputLine;

		    	//while ((inputLine = in.readLine()) != null)
		    	  //  source= source + inputLine;

		    //	in.close();

		    	
		    	//System.out.println("articlecontainer");
	    		String source="";
	    		ls= stat4.executeQuery("select * from tofindin where testid='"+testid+"'");
	    		ls.beforeFirst();
	    		String rxpath="";
	    		String xpath="";
	    		boolean success2=false;
	    		
	    		while(ls.next()){
	    			
	    			//System.out.println("vamos");
	    			try {
	    				success2=true;
	    				rxpath=ls.getString("xpath");
	    				rxpath=rxpath.replaceAll("¬","'");
	    				System.out.println(rxpath);
	    				driver.findElement(By.xpath(rxpath));
	    		    } catch (Exception e1){
	    		    		success2=false;			//Control different spelling for Contact Us Link
    			    		    	
	    		    }
	    		    
	    		    	if (success2){
	    		    	
	    		    		xpath=rxpath;}}
	    		
	    		    		try{
	    			    		
	    		//WebElement container= driver.findElement(By.className("articlecontainer"));
	    		
	    		//List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'articlecontainer') or contains(@class,'content') or contains(@id,'content')]"));
	    			System.out.println(xpath);
	    		    List<WebElement> elements = driver.findElements(By.xpath(xpath));
	    		   
	    		   if(elements.size()>0){
	    			   
	    			   //System.out.print(elements.size()+"\n");
	    			   int i=0;
	    			   
	    			  
	    			   for (WebElement container: elements){ 
	    				   if(! container.getText().equals("") & ! container.getText().equals(" ")){
	    					   source = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",container);
	    					   //System.out.println(source);
	    				   }}}
	    			   
	    		   }catch(Exception e){ System.out.println(e);
	    		   }finally{;}
	    		   
	    		   
	    			   //System.out.println(source);  
	    					
	    			   
	    		 
	    		//source = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",container);}catch(Exception e){ System.out.println(e);}finally{
		    	//System.out.println(source);
	    		
	    		
	    //	try{
	    		
	    		//File file=new File("texto.txt");
		    	//String header="<html>\n<head>\n<title>Report</title>\n</head>\n<body>\n<h1>\nReports</h1>\n<p>\n-----------------------------------------------------------------------------------------</p>\n";
		    	//String footer="<p>\n------------------------------------------------------------------------------------------</p>\n\n<strong>Report End</strong></p>\n</body>\n</html>\n";
		    	//FileWriter write = new FileWriter(file,true);
		    	//write.write(header);
		    	//write.write(ls.getString("tofind"));
		    	//write.write(footer);
		    	//write.close();
		    	
		   // }catch(Exception e){
		    	
		    //	 JOptionPane.showMessageDialog(null,
		    	//  		    "File Error",
		    	  //		    "Error",
		    	  	//	    JOptionPane.ERROR_MESSAGE );
		   // }
	    		
	    		xpath=xpath.replaceAll("'","�");
	    		ns= stat4.executeQuery("select * from tofindin where testid='"+testid+"' and xpath='" +xpath+"'");
	    		while(ns.next()){
	    		
	    			String text;
	    			String[] split;
	    			String[] nfsplit;
	    			text=ns.getString("tofind");
	    			total=text.length();
	    			failed=0;
	    			
	    			System.out.println("Total------------------" + total + "-----------------");
	    			
	    			text=text.replaceAll("\\s+", " ");
	    			split=text.split("��");
	    			//System.out.println(text);	    			 
	    			//System.out.println(split.length);
	    			//System.out.println(split[0]);
	    			
	    			int len=split.length;
	    			int nflen=0;
	    			int textpos = 0;
	    			int nfpos=0;
	    			
	    			String found="";
	    			String nfound="";
	    			//String page=driver.findElement(By.tagName("body")).getText().toLowerCase();
	    			//String page=driver.getPageSource();
	    			while (textpos!=len){
	    			
	    				if(split[textpos]!=""){
	    					
	    					split[textpos]=split[textpos].replaceAll("�","'");
	    					//split[textpos]=split[textpos].replaceAll('(', ' ');
	    					//split[textpos]=split[textpos].replaceAll(")", "");
	    					//System.out.println(split[textpos]);
	    					//split[textpos]=split[textpos].replaceAll("\r", " ")+" ";
	    					//split[textpos]=split[textpos].replaceAll("\""," ")+" ";
	    	    			
	    					
	    					if(source.contains(split[textpos])){
	    							
	    						found=found+split[textpos]+" ";
	    						
	    						
	    					}else{
	    						
	    						//source=source.replaceAll("\\W"," ");
	    						//System.out.println(source);
	    						split[textpos]=split[textpos].replaceAll("\\W", " ");
	    						nfsplit=split[textpos].split(" ");
	    							    						
	    						nflen=nfsplit.length;
	    						
	    						 						
	    						if (nflen>1){
	    						
	    							
	    							nfpos=0;
	    						while (nfpos!=nflen){
	    							
	    							if(nfsplit[nfpos]!=" "){
	    							
	    								
	    								//System.out.println(nfsplit[nfpos]+"\n--------\n");
	    								//System.out.println(nfsplit[nfpos].length());
						
	    								
	    								//nfsplit[nfpos]=nfsplit[nfpos].replaceAll(" ","");
	    								if(source.contains(nfsplit[nfpos])){
	    		    						found=found+nfsplit[nfpos]+" ";
	    		    						//System.out.println("Matches");
	    								}else{
	    									nfound=nfound+nfsplit[nfpos]+" ";
	    									failed=failed+nfsplit[nfpos].length();
	    									//System.out.println("Failed------------------" + failed + "-----------------");
	    									System.out.println(nfsplit[nfpos]+" not found\n");
	    									}}
	    							
	    							nfpos=nfpos+1;
	    							//System.out.println(found);
	    							//System.out.println(nfound);
	    						}
	    			
	    					}
	    				}			
	    					textpos=textpos+1;
	    				}}
	    			
	    			
	    			float percent= (100-((failed*100.0f)/total));
	    			System.out.println(total + "-------"+ failed + "---------"+ percent);
	    			//if (found!=""){ result=result+"<p><FONT COLOR="+(char)34+"blue"+(char)34+">"+found+"<FONT COLOR="+(char)34+"green"+(char)34+">"+percent+"% present</FONT><p>";}
	    			if (found!=""){ result=result+"<p><FONT COLOR="+(char)34+"green"+(char)34+">"+percent+"% present</FONT><p>";}
	    			if (nfound!=""){ result=result+"<p><h1>Missing Stuff</h1><p><p><h3><FONT COLOR="+(char)34+"brown"+(char)34+">"+nfound+"<FONT COLOR="+(char)34+"red"+(char)34+">"+(100-percent)+"% absent</FONT><p><p>Visit <a href="+(char)34+driver.getCurrentUrl()+(char)34+" target="+(char)34+"_blank" + (char)34 + ">"+driver.getCurrentUrl()+"</a> To check manually<FONT COLOR="+(char)34+"black"+(char)34+">";}
	    			
	    		//System.out.println(result);	
	    		} 	result=result+separator;
	    		
	    		try{
	    		
	    		File file=new File("report1.htm");
	    		System.out.println(result);
	    		//String header="<html>\n<head>\n<title>Report</title>\n</head>\n<body>\n<h1>\nReports</h1>\n<p>\n-----------------------------------------------------------------------------------------</p>\n";
		    	
		    	//WebElement page=driver.getPageSource();
		    	FileWriter write = new FileWriter(file,true);
		    	//write.write(header);
		    	write.write("<p>"+result+"<p>");
		    	//write.write(driver.getPageSource().toString());
		    	//write.write(driver.findElement(By.tagName("body")).getText().toLowerCase());
		    	//write.write(footer);
		    	
		    //	URL site = new URL(driver.getCurrentUrl());
		    //	BufferedReader in = new BufferedReader(
		    //	         new InputStreamReader(
		    //	        site.openStream()));

		   // String inputLine;

		   // 	while ((inputLine = in.readLine()) != null)
		  //  	    write.write(inputLine);

		  // 	in.close();
		    	
		    	File file2=new File("repor.txt");
		    	
		    	
		    	//WebElement page=driver.getPageSource();
		    	FileWriter write2 = new FileWriter(file2,true);
		    	
		    	//WebElement container= driver.findElement(By.cssSelector("div[class='articlecontainer']"));
		    	//String contsource = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",container);
		    	//write2.write(contsource);
		    
		    	write.close();
		    	write2.close();
		    	
		    	
		    	
		    }catch(Exception e){
		    	
		    	 JOptionPane.showMessageDialog(null,
		    	  		    e,
		    	  		    "Error",
		    	  		    JOptionPane.ERROR_MESSAGE );
		    }}}}
	    		
	    		
		finished=finished+1;
		}
		  
	    		
	    		//System.out.println(result);
	    		
	    	
	    
	    //JUnitCore.main();
	    //result=result+separator;
	    //test();
		
		
	
	
	@Before
	public void setUp(String[] option) throws Exception {
	//public void setUp() throws Exception {
		//String[] option={"nothing","hola"};
		//System.out.println(option.length);
		//for(int i=0;i<option.length;i++){ System.out.println(i+"  "+option[i]);}
		
		File folder=new File("target/reports");
		File folder2=new File("target/screenshots");
		
		if(!folder.exists()){folder.mkdirs();}
		if(!folder2.exists()){folder2.mkdirs();}
		
		if(option[0].equals("getcode")){
		
		driver = new FirefoxDriver();
	    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(option[1]);
		//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		File file2=new File("repor.txt");
		file2.delete();
    	
    	
    	//WebElement page=driver.getPageSource();
    	FileWriter write2 = new FileWriter(file2,true);
    	String contsource = "";
    	try{
    	    	
    	//WebElement container= driver.findElement(By.cssSelector("div[class='articlecontainer']"));
    	String option1="";
    	if(option[2].equals("")){option1="//div[contains(@class,'articlecontainer') or contains(@class,'content') or contains(@id,'content')]";
    		
    	}else{
    		
    		option1=option[2];
    	}
    	
    	List<WebElement> elements = driver.findElements(By.xpath(option1));
		   
    		if(elements.size()>0){
			  
			   for (WebElement container: elements){ 
				   if(! container.getText().equals("") & ! container.getText().equals(" ")){
					   contsource = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",container);
					   //System.out.println(source);
				   }}}
			   
		   }catch(Exception e){ System.out.println(e);
		   }finally{
    	    	
		   		write2.write(contsource);
    	    	//driver.close();	
    	    	write2.close();}
    	
    	Desktop.getDesktop().open(file2);
    		
    	//driver.close();
    	//driver.quit();
    	System.exit(0);
		
		
	}
		//System.out.println("antes readdatabase");
		readdatabase();
	//baseUrl= JOptionPane.showInputDialog(null,"Please write URL to test");
	//driver = new FirefoxDriver();
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //driver.get(baseUrl);
	//driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	test();  
    //cus(result);
  }
  
	
	
  
   
   public void test() throws Exception {
	  
	  driver.quit();
	  	  
  }
	  	  
	  
 



}


package main.java;

import java.awt.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyDownAction;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Keyboard;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TakesScreenshot;



import javax.swing.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import java.net.*;

public class tests {
  		
  static String separator="<p>\n------------------------------------------------------------------------------------------</p>\n\n";
  public static String result="";
  public static String overall="PASSED";
  public static String result2="";
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  
  
  static Connection con = null;
	static String servername="db4free.net";
	static String username="hellpine";
	static String db="firsttry";
	static String pass="111111";
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
	
	
	public static Statement stat=null;
	public static Statement stat2=null;
	public static Statement stat3=null;
	public static Statement stat4=null;
	public int total=0;
	public int failed=0;
	
	
		
	public void readdatabase() throws Exception {
		
		String tkind;
		String tid;
		timesta=timesta%1000000000;
		File file = new File("reports/"+timesta+".html");
		File file2=new File("reports/result.html");
		file.delete();
		file2.delete();
		//System.out.println(new Timestamp(date.getTime()));
		
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
		
		
		rs= stat.executeQuery("select * from gotest"); //execute sql query
						
		rs.first();
		batchid=String.valueOf(System.getProperty("batch"));
		if(batchid.equals("null")){
		batchid=(rs.getString("batchid"));} //read from recordset
		System.out.println(batchid);
		//System.out.println(rs.getString("batchid"));
		//System.out.println(batchid);
		//rs.close();
		stat.clearBatch();
		rs= stat.executeQuery("select * from batch where batchid='"+batchid+"'");
		//System.out.println(rs.getString("testid"));
		rs.first();
		String url=rs.getString("url");
		
		//System.out.println(url);
		//if(url.contains("http://")){
		
			//	url= url.replace("http://", "https://4646:4646@");
				
		//}else{
			
			//	url= url.replace("https://", "https://4646:4646@");
		//}
	
		//System.out.println(url);
		
		baseUrl=(url);
		 //FirefoxBinary binary = new FirefoxBinary();  
		 //File firefoxProfileFolder = new 
		 //File("profile");
		 //FirefoxProfile profile = new FirefoxProfile(firefoxProfileFolder);
		 //profile.setAcceptUntrustedCertificates(true);
		 //profile.addExtension("autoauth-2.1-fx+fn.xpi");
		 //driver = new FirefoxDriver(profile);
		 driver = new FirefoxDriver();
		 driver.manage().deleteAllCookies();
		
		//FirefoxProfile ffprofile = new FirefoxProfile("c:\");
		//ffprofile.setPreference("network.automatic-ntlm-auth.trusted-uris", "stminver-demo.com");
		//ffprofile.setPreference("network.http.phishy-userpass-length", 255);
		//ffprofile.setAcceptUntrustedCertificates(true);
		//ffprofile.setAssumeUntrustedCertificateIssuer(false);
		//driver = new FirefoxDriver(ffprofile);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    //driver.get(baseUrl);
	    driver.navigate().to(baseUrl);
	    try{ //Try to bypass company privacy policy
	    	driver.findElement(By.linkText("Click here to accept this statement and access the Internet.")).click();
	    }catch (Exception e){
	    	
	    }
	    
	    String source=driver.getPageSource();
	    
	    if (source.contains("ontact")){ language="english";}
	    if (source.contains("ontakt")){ language="norwegian";}
	    if (source.contains("ö")){ language="swedish";}
	    
	    System.out.println("Site Language=="+language);
	    		
	    
	    
						
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
		
		FileWriter write = new FileWriter(file,true);
		FileWriter write2 = new FileWriter(file2,true);
		String header="<p><FONT COLOR="+(char)34+"black"+(char)34+">\n------------------------------------------------------------------------------------------</p>\n\n<strong>BATCH ID=" + batchid + "<p><p>URL= " + baseUrl + "<p></FONT></strong></p>";
		write.write(header);
		write2.write(header);
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
    	write2.write("<p> OVERALL STATUS= "+ overall +" <p>");
    	write2.write("<p><p><p><p><table border="+(char)34+"1"+(char)34+"><tr><th>TEST</th><th>STATUS</th></tr>");
    	//write.write((<p><p><p><p><<table border="1"><tr><th>TEST</th><th>STATUS</th></tr>);
		write.write(result);
    	write2.write(result2);
    	write2.write("</tr></table>");
    	//String currentDir = System.getProperty("user.dir");
    	write2.write("<p></p><p></p><p></p><p></p> Please follow this <a href="+(char)34+ timesta + ".html"+(char)34+"> LINK </a> for a full report<p>");
    	
    	write.close();
		write2.close();
		ls.close();
		rs.close();
		con.close();
		//Desktop.getDesktop().open(file);
		//Desktop.getDesktop().open(file2);
		System.out.println("All Tests Finished, please refer to " + file + " to see the report");
		
		

		driver.close();
		driver.quit();
			
		}
		
		
		
	//}
  
	
	public void ibnl2(String logname,String email,String l2test) throws Exception{
		
		String phonecss,streetcss,housecss,postcodecss,citycss,answercss,nextbuttoncss,paymentcss;
		String phone,street,house,postcode,city,answer;
		System.out.println(l2test);
		String testtoget="";
		String testid="";
		
		result=result+"<p><h3>" + l2test + " IBN L2 TEST</h3></p><p></p>";
		
		String what="";
		boolean success=true;
		//System.out.println(l2test);
		stat3= con.createStatement();
		stat4=con.createStatement();
		stat=con.createStatement();
		result2=result2+"<tr><td>"+ l2test+"</td>";
		
		l2rs1= stat3.executeQuery("select * from tests where testid='"+l2test+"'");
		l2rs1.first();
		
		String testk=l2rs1.getString("testkind");
		System.out.println(testk);
				
		if(testk.equals("ibnl2chk")){
			
			stat3.clearBatch();
			l2rs1= stat3.executeQuery("select * from ibnl2paymentcheck where testid='"+l2test+"'");
			l2rs1.first();
			testtoget=l2rs1.getString("testtoget");
			testid=l2rs1.getString("testid");
			l2rs2=stat.executeQuery("select * from ibnl2straight where testid='" + testtoget +"'");
			l2rs2.first();
			what="checkonly";
			System.out.println(testid);
		}
			
		if(testk.equals("ibnl2str")){
			
			testid=l2rs1.getString("testid");
			l2rs2=stat.executeQuery("select * from ibnl2straight where testid='" + testid  +"'");
			l2rs2.first();
			what="YES";
			System.out.println(testid);
			
		}
		
		//Get all the data from database
		
		//phonecss="input[name='newPlayer.address.homePhone']";
		System.out.println("Adquiring IBN L2 Commom data");
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
		paymentcss=l2rs2.getString("payment");
		paymentcss=paymentcss.replaceAll("¬","'");
		System.out.println("IBN L2 Commom data adquired");
		
		phone="11111111111";
		street="QA street";			//Default Values
		house="QA Number";
		postcode="m333aj";
		city="Manchester";
		answer="Pino";
		
		Thread.sleep(1000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try{
			
			driver.findElement(By.cssSelector(phonecss)).clear();
			driver.findElement(By.cssSelector(phonecss)).sendKeys(phone);
			
		}catch(NoSuchElementException e1){
			
			System.out.println("phone field not found");
			overall="FAILED";
			success=false;
			result=result+"<p> Phone Field Failed</p>";
			
		}
		
		try{
			
			driver.findElement(By.cssSelector(streetcss)).clear();
			driver.findElement(By.cssSelector(streetcss)).sendKeys(street);
			
		}catch(NoSuchElementException e1){
			
			System.out.println("street field not found");
			overall="FAILED";
			success=false;
			result=result+"<p> Street Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(housecss)).clear();
			driver.findElement(By.cssSelector(housecss)).sendKeys(house);
			
		}catch(NoSuchElementException e1){
			
			System.out.println("House field not found");
			overall="FAILED";
			success=false;
			result=result+"<p> House Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(postcodecss)).clear();
			driver.findElement(By.cssSelector(postcodecss)).sendKeys(postcode);
			
		}catch(NoSuchElementException e1){
			
			System.out.println("postcode field not found");
			overall="FAILED";
			success=false;
			result=result+"<p> Post Code Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(citycss)).clear();
			driver.findElement(By.cssSelector(citycss)).sendKeys(city);
			
		}catch(NoSuchElementException e1){
			
			System.out.println("city field not found");
			overall="FAILED";
			success=false;
			result=result+"<p> City Field Failed</p>";
		}
		
		try{
			
			driver.findElement(By.cssSelector(answercss)).clear();
			driver.findElement(By.cssSelector(answercss)).sendKeys(answer);
			System.out.println("answer");
		}catch(NoSuchElementException e1){
			
			System.out.println("answer field not found");
			overall="FAILED";
			success=false;
			result=result+"<p> Answer Field Failed</p>";
		}
		
		Thread.sleep(1000);
		
		try{
			
			driver.findElement(By.cssSelector(nextbuttoncss)).click();
			System.out.println("Boton");			
		}catch(NoSuchElementException e1){
			
			System.out.println("Next Button not found");
			overall="FAILED";
			success=false;
			result=result+"<p> Next Button Failed</p>";
		}
		
		//if(overall.equals("FAILED")){result2=result2+"<td>FAILED</td></tr>";
		//overall="FAILED";}
		
		Thread.sleep(1000);
		
		if (what.equals("YES")){
		
		try{
			
			driver.findElement(By.cssSelector(paymentcss)).click();
						
		}catch(NoSuchElementException e1){
			
			System.out.println("Payment Button not found");
			overall="FAILED";
			success=false;
		}
		
		Thread.sleep(1000);
		
		if(driver.getPageSource().contains(logname)){
			
			System.out.println("User ==>"+ logname + "<== with email ==>" + email +"<== succesfully registered as L2 'No payment at the moment'");
			result2=result2+"<td>PASS</td></tr>";
			String screenshot = "screenshots/screenshot" + timesta + ".png";
			
			try {
                
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(screenshot));
                result=result+"<p>Screenshot for this payment <a href=../"+screenshot+"><img SRC=../"+screenshot+" width=100 height=100></a><p>";
                
            } catch (IOException e1) {
                System.out.println("Screenshot Failed");
            }
			
		}else{
			
			System.out.println("Something wrong in code");
		}}
		
		if(what.equals("checkonly")){
			
			
			//Start payment methods present and functional
			
			//String fimgcss, fdepositcss, simgcss,sdepositcss,timgcss,tdepositcss,foimgcss,fodepositcss,fiimgcss,fidepositcss,siimgcss,sidepositcss; 
			//String fname,sname,tname,foname,finame,siname;
			String chkicon,chkbutton,chktext;
			
			l2rs3=stat2.executeQuery("select * from ibnl2paymentcheck where testid='" + testid +"'");
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
			
				try{
				
					if(!chkbutton.contains("//")){
					if(driver.findElement(By.cssSelector(chkicon)).isDisplayed()){
					
						try{
						
							driver.findElement(By.cssSelector(chkbutton)).click();
							Thread.sleep(1000);
						
							String source=driver.getPageSource().toLowerCase();
							chktext=chktext.toLowerCase();
							if(source.contains(chktext)){
							
								System.out.println("Payment Method ==" + chktext + "== Present");
							
								if(driver.getPageSource().contains(logname)){
								
									System.out.println("User Name ==" + logname + "== Present");
									System.out.println("Payment Name ==" + chktext + "== Present");
									//result2=result2+"<td>PASS</td></tr>";
									String screenshot = "screenshots/" + chktext + timesta + ".png";
									
									try {
						                
										File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
						                FileUtils.copyFile(scrFile, new File(screenshot));
						                
						                result=result+"<p>Screenshot for this payment <a href=../"+screenshot+"><img SRC=../"+screenshot+" width=100 height=100></a><p>";
						                
						            } catch (IOException e1) {
						                System.out.println("Screenshot Failed");
						            }
									
									result=result+"<p>"+chktext+" Payment OK</p>";
									
								
								}else{
								
									System.out.println("User Name ==" + logname + "== Not Present");
								//	result2=result2+"<td>FAILED</td></tr>";
									overall="FAILED";
									success=false;
									result=result+"<p> User Name Not displayed in "+chktext+" payment method</p>";
								}
							
							}else{
							
								System.out.println("Payment Method ==" + chktext + "== Error");
							//	result2=result2+"<td>FAILED</td></tr>";
								overall="FAILED";
								success=false;
								result=result+"<p>Payment Name Not displayed in "+chktext+" payment method</p>";
							}
								
						
						
						}catch(NoSuchElementException e1){
					
							System.out.println(chktext+" Deposit button error");
						//	result2=result2+"<td>FAILED</td></tr>";
							overall="FAILED";
							success=false;
							result=result+"<p> Depposti button failed in "+chktext+" payment method</p>";
						}
				
					}else{
					//	result2=result2+"<td>FAILED</td></tr>";
						overall="FAILED";
						success=false;
						System.out.println("Icon not displayed");
						result=result+"<p>ICON Not displayed for "+chktext+" payment method</p>";
					}
					
					}else{
						
						if(driver.findElement(By.cssSelector(chkicon)).isDisplayed()){
							
							try{
							
								driver.findElement(By.xpath(chkbutton)).click();
								Thread.sleep(1000);
							
								String source=driver.getPageSource().toLowerCase();
								chktext=chktext.toLowerCase();								
								if(source.contains(chktext)){
								
									System.out.println("Payment Method ==" + chktext + "== Present");
								
									
									if(driver.getPageSource().contains(logname)){
									
										System.out.println("User Name ==" + logname + "== Present");
										System.out.println("Payment Name ==" + chktext + "== Present");
										//result2=result2+"<td>PASS</td></tr>";
										String screenshot = "screenshots/" + chktext + timesta + ".png";
										
										try {
							                
											File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
							                FileUtils.copyFile(scrFile, new File(screenshot));
							                
							                result=result+"<p>Screenshot for this payment <a href=../"+screenshot+"><img SRC=../"+screenshot+" width=100 height=100></a><p>";
							                
							            } catch (IOException e1) {
							                System.out.println("Screenshot Failed");
							            }
										
										result=result+"<p>"+chktext+" Payment OK</p>";
									
									}else{
									
										System.out.println("User Name ==" + logname + "== Not Present");
										//result2=result2+"<td>FAILED</td></tr>";
										overall="FAILED";
										success=false;
										result=result+"<p> User Name Not displayed in "+chktext+" payment method</p>";
									}
								
								}else{
								
									System.out.println("Payment Method ==" + chktext + "== Error");
								//	result2=result2+"<td>FAILED</td></tr>";
									overall="FAILED";
									success=false;
									result=result+"<p> Payment Name Not displayed in "+chktext+" payment method</p>";
								}
									
							
							
							}catch(NoSuchElementException e1){
						
								System.out.println(chktext+" Deposit button error");
							//	result2=result2+"<td>FAILED</td></tr>";
								overall="FAILED";
								success=false;
								result=result+"<p> Deposit Button Failed in "+chktext+" payment method</p>";
							}
					
						}else{
						//	result2=result2+"<td>FAILED</td></tr>";
							overall="FAILED";
							success=false;
							System.out.println("Payment ICON not displayed");
							result=result+"<p> ICON Not displayed for "+chktext+" payment method</p>";
						}
					}
			
				}catch(NoSuchElementException e1){
				
					System.out.println("Something went wrong");
					//result2=result2+"<td>FAILED</td></tr>";
					overall="FAILED";
					success=false;
					result=result+"<p> Somethin went wrong in "+chktext+" payment method</p>";
				}
		
			driver.navigate().back();
			
			}
			
			if(success=true){
				
				result2=result2+"<td>PASS</td></tr>";
				
			}else{
				
				result2=result2+"<td>FAILED</td></tr>";
			}
			}
		

		}
	
	public void fieldvalidation (String xpath, String invchars, String testid) throws Exception{
		
		
		//System.out.println(xpath+"    "+invchars+"    "+testid);
		
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
				
			
			
			
			if(!driver.findElement(By.xpath("//div[@class='regerrors']")).isDisplayed()){
				
				
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
	}
	
	public void l1test(String testid) throws Exception{
		
		String fname,lname,email,day,month,year,next,eighteen,accept,login,password,repassword,fun,realbutton,screen;
		int count=0;
				
		boolean success=true;
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
		screen="//div[@id='nicknameDialog']/form[@id='nicknameform']/p[@id='nicknameform_txt']/input[@id='nicknameform_input']";
		String enterbutton="/html/body/div[@id='nicknameDialog']/form[@id='nicknameform']/p[@id='nicknameform_txt']/input[@id='nicknameform_bt']";
		//System.out.println(link + "\n"+fname+ "\n"+lname+ "\n"+email+ "\n"+day+ "\n"+month+ "\n"+year+ "\n"+next+ "\n"+eighteen+ "\n"+accept+ "\n"+login+ "\n"+password+ "\n"+fun+ "\n"+realbutton);

		int z=0;
		System.out.println(z);
		do{
			
			if(find==0){
			System.out.println(z+"======"+count);
			try {
			
				
				success=true;
				System.out.println(link[z]);
				System.out.println(z);
				driver.findElement(By.cssSelector(link[z]));
				
				
				
			} catch (NoSuchElementException e1){
	    		
				success=false;
				System.out.println("This not");
				//Control different spelling for Contact Us Link
				if(z==count-1){
				System.out.println("Register Link not found");
				result2=result2+"<tr><td>"+testid+"</td>";
				result2=result2+"<td>FAILED</td></tr>";
				overall="FAILED";
	    		}
				//result=(result + "<p><FONT COLOR="+(char)34+"red"+(char)34+">"+ss.getString("tofind")+" Not Finded</FONT><p>");} 
	       		//If no Contact Us 
	    	
			} finally{
	    	
				if (success){
	    		
	    		//Random rand = new Random();
	    		
	    		
				System.out.println("Register finded");
				
				try{
	    		
	    			
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    		driver.findElement(By.cssSelector(link[z])).click();
	    		//String linkurl = clicklink.getAttribute("href");
	    		//linkurl=linkurl.replace("http://","https://4646:4646@");
	    		//driver.get(linkurl);
	    		//System.out.println(linkurl);
	    		
	    		
	    		
	    		
	    		System.out.println("Register Clicked");
	    		
	    		
	    		//Thread.sleep(500);
	    		//sendkeys();
	    			    		
	    		}catch(Exception e){
	    			System.out.println(e);
	    			success=false;
	    		}
				if(driver.getCurrentUrl().toString().contains("registration")){
					find=1;	
				if (success){
	    		//List<WebElement> emailerror = driver.findElements(By.xpath("//div[@id='registration_colA']/div[@id='regerrors'][1]"));
	    		
	    		//String genmail="Daniel@hh.com";
	    		
	    		
	    		
	    		System.out.println("Sigue");
	    		System.out.println(driver.getCurrentUrl().toString());
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
	    		
	    			
	    		
	    		
	    		
	    		
	    		String genmail="QAautomation"+timesta+"@gtech.com";
	    		driver.findElement(By.cssSelector(email)).clear(); 
	    		driver.findElement(By.cssSelector(email)).sendKeys(genmail);
	    		System.out.println("email");
	    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
	    		
	    		driver.findElement(By.cssSelector(fname)).clear(); 
	    		driver.findElement(By.cssSelector(fname)).sendKeys("Daniel");
	    	    //driver.findElement(By.cssSelector("input[name=\'newPlayer.firstName\']")).clear();
	    	    //driver.findElement(By.cssSelector("input[name=\'newPlayer.firstName\']")).sendKeys("Daniel");
	    		System.out.println("FName");
	    		driver.findElement(By.cssSelector(lname)).clear(); 
	    		driver.findElement(By.cssSelector(lname)).sendKeys("Prado");
	    		System.out.println("LName");
	    		
	    		
	    		
	    		//driver.findElement(By.xpath(day))
	    		Select daydrop = new Select(driver.findElement(By.cssSelector(day)));
  		
	    		//daydrop.deselectAll();
	    		//daydrop.selectByVisibleText("18");
	    		daydrop.selectByIndex(18);
	    		System.out.println("Day");
	    		Select monthdrop = new Select(driver.findElement(By.cssSelector(month)));
	    		//daydrop.deselectAll();
	    		//monthdrop.selectByVisibleText("Jun");
	    		monthdrop.selectByIndex(6);
	    		System.out.println("Month");
	    		Select yeardrop = new Select(driver.findElement(By.cssSelector(year)));
	    		//daydrop.deselectAll();
	    		//yeardrop.selectByVisibleText("1977");
	    		yeardrop.selectByIndex(10);
	    		
	    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    		driver.findElement(By.cssSelector(next)).click();
	    		
	    		Thread.sleep(1000);
	    		String genlogin="mrt"+timesta;
	    		//genlogin="okbingo7";
	    		driver.findElement(By.cssSelector(login)).clear(); 
	    		driver.findElement(By.cssSelector(login)).sendKeys(genlogin);
	    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    		
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
	    		
	    		try{ //In case that the site have a Retype Password
	    			
	    			driver.findElement(By.cssSelector(repassword)).clear(); 
		    		driver.findElement(By.cssSelector(repassword)).sendKeys("111111");
	    		}catch(Exception e){
	    			//System.out.println(e);
	    		}
	    		
	    		driver.findElement(By.cssSelector(eighteen)).click();
	    		driver.findElement(By.cssSelector(accept)).click();
	    		
	    		String l2test="";
	    		String l2present="NO";
	    		stat2.clearBatch();
    			l1rs2=stat2.executeQuery("select testid from batch where batchid='"+ batchid +"'");
    		
    			if (l1rs2 !=null){
    			
    			l1rs2.beforeFirst();
    			
    			while(l1rs2.next()){
    				
    			
    				String l2testid=l1rs2.getString("testid");
    				System.out.println(l2testid);
    				
    				stat.clearBatch();
    				l1rs3= stat.executeQuery("select testid,testkind from tests where testid='"+ l2testid +"'");
    				l1rs3.first();
    				System.out.println(l2testid);
    				    		
    				if(l1rs3.getString("testkind").contains("l2")){
    			
    					System.out.println(l1rs3.getString("testid"));
    					l2test=l2testid;
    					l2present="YES";
    				}
    			
    		
    		
    				}
    				
    			}
    		
    		
	    		
	    		if(l2present.equals("YES")||l2present.equals("checkonly")){
	    			driver.findElement(By.cssSelector(realbutton)).click();
	    			Thread.sleep(1000);
	    			
	    			try{
		    			
		    			
	    				//driver.switchTo().alert().dismiss();
	    				String screenname=genlogin.replace("mrt", "");
	    			
	    				driver.findElement(By.xpath(screen)).clear(); 
	    				driver.findElement(By.xpath(screen)).sendKeys(screenname); //Handle Screen name
	    				driver.findElement(By.xpath(enterbutton)).click();
	    				
	    			}catch (NoSuchElementException e){
	    			
	    				System.out.println("No screen name required");
	    				
	    			
	    			}
	    			
	    			result=result+"<p>USER="+genlogin+"----"+"E-Mail="+genmail+"-------"+"Level=1<p>-------Succesfully Registered";
    				//result=result+"<p> Click on the screenshot to see it larger <a href=../"+screenshot+"><img SRC=../"+screenshot+" width=100 height=100></a><p>";
    				result2=result2+"<tr><td>"+testid+"</td>";
    				result2=result2+"<td>PASS</td></tr>";
	    			ibnl2(genlogin,genmail,l2test);
	    			
	    		}else{
	    			driver.findElement(By.cssSelector(fun)).click();
				
	    		
	    		
	    		
	    			Thread.sleep(1000);
	    		
	    		
	    			
	    		
	    		//try{
	    			
	    			//driver.findElement(By.xpath(enterbutton)).click(); //handle if a message appears vefore screen name
	    		//}catch (Exception e){
	    			
	    			//System.out.println(e);
	    		//}
	    		
	    		
	    			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    			int screenpresent=0;
	    		
	    		
	    		
	    			try{
	    			
	    			
	    				//driver.switchTo().alert().dismiss();
	    				String screenname=genlogin.replace("mrt", "");
	    			
	    				driver.findElement(By.xpath(screen)).clear(); 
	    				driver.findElement(By.xpath(screen)).sendKeys(screenname); //Handle Screen name
	    				driver.findElement(By.xpath(enterbutton)).click();
	    				screenpresent=1;
	    			}catch (NoSuchElementException e){
	    			
	    				System.out.println("No screen name required");
	    				screenpresent=0;
	    			
	    			}
	    		
	    		
	    		//String currentURL=driver.getCurrentUrl();
    			//driver.wait(500);
	    			if(driver.getPageSource().contains(genlogin)){
    				//System.out.println("User " + genlogin + " with email "+ genmail + " succesfully registered as level 1 user");
	    		
    				stat3.executeUpdate("insert into testuser(username,email,level) values('" + genlogin + "','"+genmail+"','1')");
	    		
    				System.out.println("User " + genlogin + " with email "+ genmail + " succesfully registered as level 1 user");
    				
    				String screenshot = "screenshots/screenshot" + timesta + ".png";
    				
    				while(screenpresent==1){
    				
    						
    					if(driver.findElement(By.xpath(screen)).isDisplayed()){ 
    						screenpresent=1;}else{    						
    						screenpresent=0;
    					}
    					
    				}
    				
    				try {
    	                
    					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	                FileUtils.copyFile(scrFile, new File(screenshot));
    	            } catch (IOException e1) {
    	                System.out.println("Screenshot Failed");
    	            }
    				
    				result=result+"<p>USER="+genlogin+"----"+"E-Mail="+genmail+"-------"+"Level=1<p>-------Succesfully Registered";
    				result=result+"<p> Click on the screenshot to see it larger <a href=../"+screenshot+"><img SRC=../"+screenshot+" width=100 height=100></a><p>";
    				result2=result2+"<tr><td>"+testid+"</td>";
    				result2=result2+"<td>PASS</td></tr>";
    				//overall="PASS";
    				//System.out.println(result + "------"+ result2);
    			}else{
    				
    				result=result+"<p>Something Fails in L1 registration<p>";
    				result2=result2+"<tr><td>"+testid+"</td>";
    				result2=result2+"<td>FAILED</td></tr>";
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
	
}
	
	
	public void single(String testid) throws Exception{
		
		result="";
		
		stat3= con.createStatement();
		stat4= con.createStatement();
		
		//System.out.println(testid);
		//System.out.println(ls.getString("testkind"));
		String cus=""; 
		boolean success = true;
		
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
			success=true;
			cus=ss.getString("tofind");
			driver.findElement(By.linkText(cus));
	    } catch (NoSuchElementException e1){
	    		success=false;					//Control different spelling for Contact Us Link
	    		
	    		if(ss.isLast() & nolink<=0){
	    		result=(result + "<p><FONT COLOR="+(char)34+"red"+(char)34+">"+ss.getString("tofind")+" Not Finded</FONT><p>");} 
	       		//If no Contact Us 
	    	
	    } finally{
	    	
	    	if (success & find==0){
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
	    		    } catch (NoSuchElementException e1){
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
		
		if(option[0].equals("getcode")){
		
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(option[1]);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
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
    	    	driver.close();	
    	    	write2.close();}
    	
    	Desktop.getDesktop().open(file2);
    		
    	driver.close();
    	driver.quit();
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
  
	
	
  @Test
   
   public void test() throws Exception {
	  
  }
	  	  
	  
  }

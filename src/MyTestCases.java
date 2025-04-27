import static org.testng.Assert.assertEquals;

import java.awt.Checkbox;
import java.awt.RenderingHints.Key;
import java.awt.Window;
import java.awt.event.WindowStateListener;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTestCases {
	
	WebDriver driver = new ChromeDriver();
	String website = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	
	@BeforeTest
	
	public void  mysetup() {
		
		driver.manage().window().maximize();
		driver.get(website);
	}
	
	@Test(priority=1,description = "Radio button",invocationCount = 1)//invocationCount تكرار
	
	public void  RadioButtonExample() {
		
		List<WebElement> Allradiobuttons = driver.findElements(By.className("radioButton"));
		
		//click on first radio button
		//Allradiobuttons.get(0).click();
		
		
		
		int randomradiobutton = rand.nextInt(Allradiobuttons.size());
		Allradiobuttons.get(randomradiobutton).click();
		//to know if the radio button is really selected
		boolean excpetedresult = true;
		boolean actualresult = Allradiobuttons.get(randomradiobutton).isSelected();
		
	}
	
   @Test(priority=2,description = "Dropdown dynamic")

	public void DynamicDropdownExample() throws InterruptedException {
	   
	   
	   
	   
	   //generate random string in a static way because i dont beed my test to include any other data
	   String[] countriescode = {"US","OM","BR"};
	   
	   //random index based on the lenght of the above array
	   int randomindex = rand.nextInt(countriescode.length);
	   
	   // web element to the input field of country
	   WebElement randomcountries = driver.findElement(By.id("autocomplete"));
	   
	   //send an random item from my array to the webelemrnt (inputfield)
	   randomcountries.sendKeys(countriescode[randomindex]);
	   Thread.sleep(1000);
	   
	   //it will press arrow down + enter to select first item from list
	   randomcountries.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
	   
	   //return first value of the element and i make it string by adding caSTING
	   //i need to capture or take the country name that selenium already selected
	  String datainsidemyinput = (String) js.executeScript("return arguments[0].value",randomcountries);
	  
	  //the country name for example jordan contains capital letters and small 
	  //so what i did i make all letters small 
	  String updatedata = datainsidemyinput.toLowerCase();

      
	  Boolean actualvalue = updatedata.contains(countriescode[randomindex].toLowerCase());

	   Boolean Excpectedresult = true;
	  Assert.assertEquals(actualvalue, Excpectedresult);
  }
	
	@Test(priority=3,description = "Static Dropdown Example")
public void StaticDropdownExample () {
		
		WebElement selectstaticdroplist = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(selectstaticdroplist);
		
	//	sel.selectByIndex(0);
	//	sel.selectByValue("option1");
		sel.selectByVisibleText("Selenium");
		
		
		
		
	}
	
	@Test(priority=4,description = "check box example")
	
public void checkbpxexample() {
		//  //tagname [@attributename='value of the attributte']
		List<WebElement> checkboxes  = driver.findElements(By.xpath("//input [@type='checkbox']"));
	int randomcheckbox=	rand.nextInt(checkboxes.size());
	checkboxes.get(randomcheckbox).click();
	
	//to select them all
	//for  (int i=0; i<checkboxes.size();i++) {
	//	checkboxes.get(i).click();
	//boolean Actualresult = checkboxes.get(i).isselected();
	// boolean excpetedresult = true;
	//Assert.assertEquals(Actualresult,excpetedresult);
		
	}
	@Test(priority=5,description = "Switch Window Example",enabled=false)
	public void switchwindowexample() throws InterruptedException {
	
		WebElement openwinbutton = driver.findElement(By.id("openwindow"));
		openwinbutton.click();
		Thread.sleep(2000);
		List<String> windowhandle = new ArrayList<String>(driver.getWindowHandles());
		//switch to other window
		driver.switchTo().window(windowhandle.get(1));
		//in the second window
		WebElement contactbutton = driver.findElement(By.id(""));
		contactbutton.click();
		//switch to first window
		driver.switchTo().window(windowhandle.get(0));
		
	
	}
	
		@Test(priority=6,description = "Switch to tab example")
		public void swetichtabexample() {
			WebElement opentab = driver.findElement(By.id("opentab"));
			opentab.click();
			
			List<String> windowhandle = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(windowhandle.get(1));

			
		}
		
		
		
	
	@Test(priority=7,description = "Alert and confirm")
	
	public void Alertandconfirm(){	
		WebElement entername = driver.findElement(By.id("name"));
		entername.sendKeys("Haitham");
		
		WebElement alert = driver.findElement(By.id("alertbtn"));
		alert.click();
		
		
	//	WebElement confirm = driver.findElement(By.id("confirmbtn"));
	//	confirm.click();
		
		driver.switchTo().alert().accept();
		//driver..switchTo().alert().dismiss();
		
		//WebElement confirm = driver.findElement(By.id("confirmbtn"));
		//	confirm.click();
		//driver.switchTo().alert().accept();
	}
	
	@Test(priority=8,description = "play with data of coloumn")
	
	public void webtableexample() {
		//get the id of all the table 
		WebElement thetable= driver.findElement(By.id("product"));
		//inside the table get all the rows by tag name 
		List <WebElement> thedatainsidetable = thetable.findElements(By.tagName("tr"));
		//we start from 1 to execlude the th header of table 
		for (int i=1;i<thedatainsidetable.size();i++){
		int totalidinrow = thedatainsidetable.get(i).findElements(By.tagName("td")).size();
		System.out.println(
				
				thedatainsidetable.get(i).findElements(By.tagName("td")).get(totalidinrow-1).getText());
				
					
		}
		}
	
	@Test(priority=9,description = "show and hide button")
	public void elementdisplay() {
		//hard assert will stop all the execution
		//soft assert will continoue even if one is failed
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll, 1500");
		SoftAssert myassert = new SoftAssert();
		
		WebElement hidebutton = driver.findElement(By.id("hide-textbox"));
		WebElement showbutton = driver.findElement(By.id("show-textbox"));
		
		hidebutton.click();
		 WebElement textinseting = driver.findElement(By.id("displayed-text"));
		 //soft assert
		// myassert.assertEquals(textinseting.isDisplayed(), false);
		 Assert.assertEquals(textinseting.isDisplayed(), false);
		 
		 showbutton.click();
		 Assert.assertEquals(textinseting.isDisplayed(), true);
		 //for soft assert run
		// myassert.assertAll();
		
	}
	
	@Test(priority=10)	
	
	public void checkthetitle() {
		
		String excpected = "Automation Practice - CodenBox AutomationLab";
		
		String actualtitle = driver.getTitle();
		Assert.assertEquals(actualtitle,excpected);
	}
	
	
	
	}
	
	
	
	
	



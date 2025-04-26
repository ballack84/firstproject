import java.awt.Checkbox;
import java.awt.RenderingHints.Key;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	
	WebDriver driver = new ChromeDriver();
	String website = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	
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
		
	}
	
   @Test(priority=2,description = "Dropdown dynamic")

	public void DynamicDropdownExample() throws InterruptedException {
	   
	   String[] countriescode = {"US","OM","BR"};
	   int randomindex = rand.nextInt(countriescode.length);
	   WebElement randomcountries = driver.findElement(By.id("autocomplete"));
	   randomcountries.sendKeys(countriescode[randomindex]);
	   Thread.sleep(1000);
	   randomcountries.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
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
		
	}
	
	
	
	
	



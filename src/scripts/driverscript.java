package scripts;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class driverscript {
	
	public static ChromeDriver driver;
	
	public int p = 6;

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/utilities/chromedriver.exe" );
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		driver = new ChromeDriver(options);		
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		
		System.out.println();
		
		Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+"/src/TestData/Tests.xlsx");
		int count = xr.getRowCount("Sheet1");
		//System.out.println(count);
		for(int i=2;i<=count;i++)
		{
			String run = xr.getCellData("Sheet1", "Run", i).trim();
			//System.out.println(run);
			if(run.equalsIgnoreCase("on"))
			{
				String Tcname = xr.getCellData("Sheet1", "TestCases", i).trim();
				//System.out.println(Tcname);
				switch(Tcname)
				{
				case "vTigerCrm_Verify_Title_01":
					String expTitle = "vtiger CRM - Commercial Open Source CRM";
					String actTitle = driver.getTitle();
					if(expTitle.equals(actTitle))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_Logo_02":
					//Select sel = new Select(driver.findElement(By.className("")));
					if(driver.findElement(By.xpath("//img[@src='include/images/vtiger-crm.gif']")).isDisplayed())
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_KeyModules_03":
					if(driver.findElement(By.xpath("(//body//font)[1]")).getText().equals("Key Modules"))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_vtigerCustomerPortal_Link_04":
					if(driver.findElements(By.linkText("vtiger Customer Portal")).size()==1)
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_UsernameFiled_Editable_05":
					driver.findElement(By.name("user_name")).sendKeys("Hello");
					if(driver.findElement(By.name("user_name")).getAttribute("value").equals("Hello"))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_passwordField_Encrypted_06":					
					if(driver.findElement(By.name("user_password")).getAttribute("type").equals("password"))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_LoginWithInvalidCedentials_07":	
					driver.findElement(By.name("user_name")).sendKeys("asas");
					driver.findElement(By.name("user_password")).sendKeys("123");
					driver.findElement(By.name("Login")).click();					
					if(driver.findElements(By.xpath("//*[contains(text(),'You must specify a valid username and password.')]")).size()==1)
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Verify_LoginWithValidCedentials_08":
					driver.findElement(By.name("user_name")).clear();
					driver.findElement(By.name("user_name")).sendKeys("admin");
					driver.findElement(By.name("user_password")).clear();
					driver.findElement(By.name("user_password")).sendKeys("admin");
					driver.findElement(By.name("Login")).click();					
					if(driver.findElements(By.linkText("Logout")).size()==1)
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_MouseMoveActivities_09":					
					Actions act = new Actions(driver);
					
					act.moveToElement(driver.findElement(By.id("showSubMenu"))).perform();
					act.click(driver.findElement(By.linkText("New Invoice"))).perform();
					if(driver.findElements(By.xpath("//th[text()='Invoice Information']")).size()==1)
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Mouse_DragnDrop_Activities_10":
					driver.findElement(By.linkText("My Account")).click();
					driver.findElement(By.name("Customise")).click();					
					Actions act1 = new Actions(driver);
					act1.dragAndDrop(driver.findElement(By.id("cl1")), driver.findElement(By.id("cl7"))).perform();
					if(driver.findElement(By.id("cl7")).getText().equals("Home"))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Window_handles_11":
					driver.findElement(By.linkText("New Contact")).click();
					driver.findElement(By.name("btn1")).click();
					Thread.sleep(3000);
					Set<String> set = driver.getWindowHandles();
					Iterator<String> iter = set.iterator();
					String parentwindow = iter.next();
					System.out.println(parentwindow);
					String childwindow = iter.next();
					System.out.println(childwindow);
					
					driver.switchTo().window(childwindow);
					driver.findElement(By.linkText("vtiger")).click();
					
					driver.switchTo().window(parentwindow);
					
					if(driver.findElement(By.name("account_name")).getAttribute("value").equals("vtiger"))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Tab_handles_12":
					
					driver.findElement(By.linkText("Logout")).click();
					driver.findElement(By.linkText("vtiger Customer Portal")).click();
					Thread.sleep(5000);
					Set<String> set1 = driver.getWindowHandles();
					Iterator<String> iter1 = set1.iterator();
					String tab1 = iter1.next();					
					String tab2 = iter1.next();
					
					
					driver.switchTo().window(tab2);
					driver.findElement(By.id("login")).click();
					driver.findElement(By.name("username")).sendKeys("Test");
					driver.close();
					
					driver.switchTo().window(tab1);
					driver.findElement(By.name("user_password")).sendKeys("admin");
					driver.findElement(By.name("Login")).click();	
					
					if(driver.findElement(By.linkText("Logout")).isDisplayed())
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				case "vTigerCrm_Alert_handles_13":
					driver.findElement(By.linkText("New Lead")).click();
					driver.findElement(By.name("button")).click();
					Thread.sleep(2000);
					String lnammsg = driver.switchTo().alert().getText();
					driver.switchTo().alert().accept();
					driver.findElement(By.name("lastname")).sendKeys("Modi");
					driver.findElement(By.name("button")).click();
					Thread.sleep(2000);
					String compmsg = driver.switchTo().alert().getText();
					driver.switchTo().alert().accept();
					driver.findElement(By.name("company")).sendKeys("BJP");
					driver.findElement(By.name("button")).click();					
					
					if(driver.findElement(By.xpath("//td[text()='Last Name:']/following::td[1]")).getText().equals("Modi") && driver.findElement(By.xpath("//td[text()='Company:']/following::td[1]")).getText().equals("BJP") && lnammsg.equals("Last Name cannot be empty") && compmsg.equals("Company cannot be empty"))
					{
						System.out.println(Tcname+"  = PASSED");
						xr.setCellData("Sheet1", "Status", i, "PASSED");
					}
					else
					{
						System.out.println(Tcname+"  = FAILED");
						xr.setCellData("Sheet1", "Status", i, "FAILED");
					}
					break;
				}
			}
		}
		
		

	}

}

package com.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDDTest {

	static WebDriver driver;

	@Test(priority = 1)
	public void validateRegisterPage() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	
	
	@Test(priority = 2, dataProvider = "TestData")
	public void validateInforamation(String firstname, String lastname, String email, String address, String city,
			String state, String country, String username, String password, String confirmPassword) throws Exception {
		
//		System.out.println(firstname+" "+lastname+" "+email+" "+address+" "+city+" "+state+" "+country+" "+username+" "+password+" "+confirmPassword);
		
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);

		driver.findElement(By.name("userName")).sendKeys(email);
		driver.findElement(By.name("address1")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);

		WebElement wb = driver.findElement(By.name("country"));
		Select sel = new Select(wb);
		sel.selectByVisibleText(country);
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='register.php']")).click();
		Thread.sleep(1000);
		
	}
	

	@DataProvider(name="TestData")
	public Object[][] testData(){
		ExcelReader excelReader = new ExcelReader("F:\\Testing\\mavendemo\\DataDrivenDevelopement\\TestData.xlsx"); 
		int row = excelReader.getRowCount(0);
		int column = excelReader.getColumnCOunt(0);
		
		Object[][] data=new Object[row][column];
	   	
		for(int i=0;i<=row-1;i++) {
			for(int j=0;j<column;j++) {
				data[i][j] = excelReader.getSpecificCellValue(0, i, j);
			}
		}
		return data;
	}

}

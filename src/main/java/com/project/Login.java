package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	
	
	private static XSSFWorkbook Read_Data;
	private static XSSFSheet Index_Sheet;
	private static XSSFCell cellData;
	private static XSSFCell cellPassword;
	private static XSSFCell cellUser;
	
	public static void main( String[] args ) throws InterruptedException, FileNotFoundException, IOException {
    	//read data from file excel
		FileInputStream fis= new FileInputStream(new File("C:\\Users\\Huu Dung Pro\\eclipse-workspace\\project\\user.xlsx"));
	    Read_Data = new XSSFWorkbook(fis);
	    Index_Sheet = Read_Data.getSheetAt(0);
	    cellUser = Index_Sheet.getRow(3).getCell(0);
	    cellPassword = Index_Sheet.getRow(3).getCell(1);
	    cellData = Index_Sheet.getRow(4).getCell(2);
	  
	    // put values in variables 
	    String dataUser = cellUser.getStringCellValue();
	    String dataPassword = cellPassword.getStringCellValue();
	    String data = cellData.getStringCellValue();
	    
	    // show data 
	    System.out.println(dataUser+" "+dataPassword);
	    System.out.println(data);
	    
	    // khai  bao chromedriver để chạy automation
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Huu Dung Pro\\eclipse-workspace\\project\\chromedriver.exe"); 
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//Vào trang rủng rỉnh site để automation và cho khoảng nghỉ.
	    driver.get("https://rungrinh.vn/");
	    Thread.sleep(2000);
	    
	    
	    //dang nhap automation
	    driver.findElement(By.linkText("Đăng nhập")).click();
	    Thread.sleep(2000);
	    WebElement account = driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div/input[1]"));
	    Thread.sleep(2000);
	    
	    //nhap username
	    account.sendKeys(dataUser);
	    Thread.sleep(2000);
	    account = driver.findElement(By.xpath(("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div/input[2]"))); 
	  
	    //nhap password
	    account.sendKeys(dataPassword);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div/button[1]")).click();
	    Thread.sleep(5000);
	    
	    //nhan nut đăng nhập
	    driver.findElement(By.xpath("/html/body/div[1]/div/header/div/nav/div[3]/div[2]")).click();
	    Thread.sleep(4000);
	    
	    //sau đó đăng xuất
	    driver.findElement(By.linkText("Đăng xuất")).click();
	    Thread.sleep(4000);
 
		
 
		// dăng kí 
		driver.findElement(By.xpath("/html/body/div[1]/div/header/div/nav/div[3]/div[2]/a[2]")).click();
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div[2]/button[1]")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    
	    // nhap username dăng kí
	    account= driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div[2]/input[1]"));
	    cellUser = Index_Sheet.getRow(5).getCell(0);
	    account.sendKeys(cellUser.getStringCellValue()); 
	    account = driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div[2]/input[2]")); 
	    
	    //nhap password
	    cellPassword = Index_Sheet.getRow(5).getCell(1);
	    account.sendKeys(cellPassword.getStringCellValue());
	    System.out.println(cellPassword.getStringCellValue());
	    driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/div/div[2]/div/div[2]/button")).click();
	    Thread.sleep(3000);
	    
	    //click o đăng kí
	    driver.findElement(By.xpath("/html/body/div[1]/div/div[9]/div/div/div/span")).click();
	    Thread.sleep(4000);
	    
	    //search theo từ theo data có sẵn 
	    account = driver.findElement(By.cssSelector("input[class='search-box__input']"));
		account.sendKeys(data);
		driver.findElement(By.cssSelector("input[class='search-box__input']")).sendKeys(Keys.ENTER);;
		 
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/div[1]/div/header/div/nav/div[1]/a/img")).click();
		Thread.sleep(3000);
		    
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/section/div[1]/ul/li[6]/span")).click();
		Thread.sleep(4000); 
		
		
		//đếm xem được có bao nhiêu phần tử trong trang rủnginh.vn
		List<WebElement> count = driver.findElements(By.cssSelector("div[class='site-card']"));
		int xpath = count.size();
		System.out.println(xpath);
		Thread.sleep(4000); 
		
		// đóng kết nối và đóng automation
		driver.close();
	    driver.quit();

	}

}

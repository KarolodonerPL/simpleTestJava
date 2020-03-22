package simpleTest;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase {
	
	@Test
	public void test() {
		
		//Define driver
    	WebDriver driver = new ChromeDriver();
    	
    	// 1. Open web Page
    	driver.get("http://www.allegro.pl");
    			
    	// 2. Maximize window
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	
    	// 3. Check if page contain PopUp, and if yes, close it
    	if(driver.findElements(By.id("dialog-title")).size() != 0)
    	{
    		driver.findElement(By.xpath("//button[@data-role=\"accept-consent\"]")).click();
    	}
    	
    	// 4. Search for iPhone 11
    	driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("Iphone 11");
    	driver.findElement(By.xpath("//button[@data-role=\"search-button\"]")).click();
    	
    	// 5. Choose black color
    	driver.findElement(By.xpath("//*[contains(@class,'_3e3a8_2MeYj') and contains(.,'czarny')]")).click();
    	
    	// 6. We count the number of displayed phones on the first page of results and the number presented in the Console
    	for (int i = 1; i < 1000; i++) 
    	{
    		if(driver.findElements(By.xpath("//*[@id='opbox-listing--base']//section/article["+i+"]/div/div")).size() == 0)
        	{
    			int result = i - 1;
    			System.out.println(result);
    			break;
        	}
    	}
    	
    	// 7. We are looking for the highest price on the list and display in the console
    	new Select(driver.findElement(By.xpath("//*[@class='_1h7wt _k70df _7qjq4 _27496_3VqWr']"))).selectByVisibleText("cena: od najwy¿szej");
    	for (int i = 1; i < 10; i++)
    	{
    		String expectedSorting=" cena: od najwy¿szej ";
    	    String actualSorting = driver.findElement(By.xpath("//*[@class='_1h7wt _k70df _7qjq4 _27496_3VqWr']")).getText();
    	    
    		if(expectedSorting.equals(actualSorting))
    		{
    			break;
    	    }
    	    else
    	    {
    	    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	    }
    	}
    	String maxPrice = driver.findElement(By.xpath("//*[@id='opbox-listing--base']/div/section[2]/section/article[1]/div/div/div[2]/div[2]/div/div/span")).getText();
    	System.out.println(maxPrice);
    	
    	// 8. We add 23% to the highest price
    	String asd = maxPrice.replaceAll(" ", "");
    	String asd2[] = asd.split(",");
    	double dbl = Double.parseDouble(asd2[0]);
    	double finalResult = dbl * 1.23;
    	System.out.println("Ostateczna cena z 23% vatem to: " + finalResult); 	
    	
		// 9. Close window
		  driver.close();  			
	}

}

package MUM_Pearson;
import java.util.Calendar;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.Test;

import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;

public class DatePicker {

    @Test

    public void testDAtePicker() throws Exception{

    	SetProperpertiesFile.setAllProperpertiesFile();
        //DAte and Time to be set in textbox
    	String geckodriverPath=SetProperpertiesFile.appConfig.getPropertyValue("GeckoDriver");
    	System.setProperty("webdriver.gecko.driver", geckodriverPath);
        String dateTime ="08/08/2017 2:00 PM";

        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        
        driver.get("http://demo.automationtesting.in/WebTable.html");
        List<WebElement> gridList = driver.findElements(By.xpath("//*[@class='ng-isolate-scope']"));
        for(WebElement ele : gridList)
        {
        	System.out.println(ele.getText());
        }
      
    }

}
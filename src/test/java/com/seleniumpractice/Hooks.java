package com.seleniumpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver;
    @Before
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.currys.co.uk");
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);//polymorphism
        driver.manage().window().maximize();




    }

    //@After
    public void tearDown()
    {

        driver.quit();
    }
}

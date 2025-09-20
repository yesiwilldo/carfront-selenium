package cn.xiaoli.testSuites;

import cn.xiaoli.pageObjects.CarMinePage;
import cn.xiaoli.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class OrderTest {
    HomePage homePage;
    WebDriver driver;
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/mine");
        homePage = PageFactory.initElements(driver, HomePage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //进入首页点击第一个公司进入公司详情页
        homePage.getHome_link().click();
        Actions actions = new Actions(driver);
        actions.moveToElement(homePage.getCompany()).click().perform();
//        homePage.getCompany().click();
    }
    @Test
    public void order(){

    }
}

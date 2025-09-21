package cn.xiaoli.testSuites;

import cn.xiaoli.pageObjects.CarMinePage;
import cn.xiaoli.pageObjects.DetailCompanyPage;
import cn.xiaoli.pageObjects.HomePage;
import cn.xiaoli.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
//权限与访问控制
public class OrderPermissionTest {
    HomePage homePage;
    DetailCompanyPage detailCompanyPage;
    CarMinePage carMinePage;
    OrderPage orderPage;
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/mine");
        homePage = PageFactory.initElements(driver, HomePage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        detailCompanyPage = PageFactory.initElements(driver, DetailCompanyPage.class);
        carMinePage=PageFactory.initElements(driver,CarMinePage.class);
        orderPage=PageFactory.initElements(driver,OrderPage.class);
//        homePage.getCompany().click();
    }
    //下单操作
    public void tryorder(){
        homePage = PageFactory.initElements(driver, HomePage.class);
        wait.until(ExpectedConditions.visibilityOf(homePage.getHome_link()));
        //进入首页点击第一个公司进入公司详情页
        homePage.getHome_link().click();
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.getCompany()));
        actions.moveToElement(homePage.getCompany()).click().perform();
        //选择车辆点击下单
        wait.until(ExpectedConditions.visibilityOf(detailCompanyPage.getCar_btn()));
        actions.moveToElement(detailCompanyPage.getCar_btn()).click().perform();
    }
    //未登录用户尝试下单
    @Test
    public void TC_A1_UnloggedUserTryToOrder(){
        homePage = PageFactory.initElements(driver, HomePage.class);
        tryorder();
        wait.until(ExpectedConditions.visibilityOf(detailCompanyPage.getDialog__message()));
        String dialog__message_text = driver.findElement(By.className("van-dialog__message")).getText();
        Assert.assertEquals(dialog__message_text,"请进行用户账号登录再下订单操作！");
    }
    //普通用户尝试下单
    @Test
    public void TC_A2_NormalUserTryToOrder() throws InterruptedException {
        carMinePage.login("also","123456");
        homePage = PageFactory.initElements(driver, HomePage.class);
        tryorder();
        Assert.assertEquals(orderPage.getText_opage().getText(),"订单确认");
    }
    //非普通用户（如管理员）尝试下单
    @Test
    public void TC_A3_NonNormalUserTryToOrder() throws InterruptedException {
        carMinePage.login("zhang","12345");
        homePage = PageFactory.initElements(driver, HomePage.class);
        tryorder();
        //弹窗内容断言
        wait.until(ExpectedConditions.visibilityOf(detailCompanyPage.getDialog__message()));
        String dialog__message_text = driver.findElement(By.className("van-dialog__message")).getText();
        Assert.assertEquals(dialog__message_text,"请进行用户账号登录再下订单操作！");
    }


    @AfterMethod
    public void quit_page(){
        if (driver!=null){
            driver.quit();
        }
    }
}

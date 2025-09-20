package cn.xiaoli.testSuites;

import cn.xiaoli.pageObjects.CarMinePage;
import cn.xiaoli.pageObjects.DetailCompanyPage;
import cn.xiaoli.pageObjects.HomePage;
import cn.xiaoli.pageObjects.OrderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrderPaymentTest {
    CarMinePage carMinePage;
    OrderPermissionTest orderPermissionTest;
    OrderPage orderPage;
    HomePage homePage;
    DetailCompanyPage detailCompanyPage;
    WebDriverWait wait;
    WebDriver driver;
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/mine");
        //初始化java类
        detailCompanyPage = PageFactory.initElements(driver, DetailCompanyPage.class);
        carMinePage=PageFactory.initElements(driver,CarMinePage.class);
        orderPage=PageFactory.initElements(driver,OrderPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //下单操作
    public void inorder(){
//        System.out.println("111");
        wait.until(ExpectedConditions.visibilityOf(homePage.getHome_link()));
        //进入首页点击第一个公司进入公司详情页
        homePage.getHome_link().click();
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.getCompany()));
        actions.moveToElement(homePage.getCompany()).click().perform();
        //选择车辆点击下单
        wait.until(ExpectedConditions.visibilityOf(detailCompanyPage.getCar_btn()));
        actions.moveToElement(detailCompanyPage.getCar_btn()).click().perform();
        Assert.assertEquals(orderPage.getText_opage().getText(),"订单确认");
    }
    //余额充足用户（账户余额 > 订单总额）下单
    @Test
    public void TC_B1_SufficientBalanceOrderSuccess() throws InterruptedException {
        carMinePage.login("hhh","123456");
        inorder();
        orderPage.OrderByDefault(driver);
        wait.until(ExpectedConditions.visibilityOf(orderPage.getToast_text()));
        Assert.assertEquals(orderPage.getToast_text().getText(),"出单成功，已支付费用，请用户确认租车");
    }
    @Test
    //余额为零用户下单
    public void TC_B2_ZeroBalanceOrderFail() throws InterruptedException {
        carMinePage.login("butterfly","123456");
        inorder();
        orderPage.OrderByDefault(driver);
        wait.until(ExpectedConditions.visibilityOf(orderPage.getToast_text()));
        Assert.assertEquals(orderPage.getToast_text().getText(),"用户账户金额不够，请充值！");
    }
    @Test
    //余额不足但非零用户（0 < 账户余额 < 订单总额(2276元)）下单
    public void TC_B3_InsufficientBalanceOrderFail() throws InterruptedException {
        carMinePage.login("ccc","123456");
        inorder();
        orderPage.OrderByDefault(driver);
        wait.until(ExpectedConditions.visibilityOf(orderPage.getToast_text()));
        Assert.assertEquals(orderPage.getToast_text().getText(),"用户账户金额不够，请充值！");
    }
    @AfterMethod
    public void quit_page(){
        if (driver!=null){
            driver.quit();
        }
    }
}

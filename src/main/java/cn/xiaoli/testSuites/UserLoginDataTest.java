package cn.xiaoli.testSuites;

import cn.xiaoli.pageObjects.CarMinePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserLoginDataTest {
    WebDriver driver;
    CarMinePage carMinePage;
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/mine");
        carMinePage = PageFactory.initElements(driver, CarMinePage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @DataProvider
    public Object[][] getLoginData(){
        Object[][] data={
                {"also","123456","登陆成功!"},
                {"also","","用户名或密码错误!"},
                {"also","11","用户名或密码错误!"},
                {"","123","用户名或密码错误!"},
                {"123","123","用户名或密码错误!"},
        };
        return data;
    }

    @Test(dataProvider = "getLoginData")
    public void testLogin(String username,String password,String msg) throws InterruptedException {
        carMinePage.login(username,password);
        Assert.assertEquals(carMinePage.getToast__text(driver),msg);
    }

    @AfterMethod
    public void quit_page(){
        if (driver!=null){
            driver.quit();
        }
    }
//    //登陆成功（正确用户名+正确密码）
//    @Test
//    public void testLogin0() throws InterruptedException {
//        carMinePage.login("also","123456");
//        Assert.assertEquals(carMinePage.getToast__text(driver),"登陆成功!");
//    }
//    //登陆失败（正确用户名+密码为空）
//    @Test
//    public void testLogin1() throws InterruptedException {
//        carMinePage.login("also","");
//        Assert.assertEquals(carMinePage.getToast__text(driver),"用户名或密码错误!");
//    }
//    //登陆失败（正确用户名+密码错误）
//    @Test
//    public void testLogin2() throws InterruptedException {
//        carMinePage.login("also","11");
//        Assert.assertEquals(carMinePage.getToast__text(driver),"用户名或密码错误!");
//    }
//    //登陆失败（用户名为空+错误密码）
//    @Test
//    public void testLogin3() throws InterruptedException {
//        carMinePage.login("","123");
//        Assert.assertEquals(carMinePage.getToast__text(driver),"用户名或密码错误!");
//    }
//    //登陆失败（错误用户名+错误密码）
//    @Test
//    public void testLogin4() throws InterruptedException {
//        carMinePage.login("123","123");
//        Assert.assertEquals(carMinePage.getToast__text(driver),"用户名或密码错误!");
//    }




//    //    @Test
//    public void testLoginBaidu() throws InterruptedException {
////        CarMinePage cm = PageFactory.initElements(driver, CarMinePage.class);
////        cm.login("also","123456");
////        System.out.println(driver);
//        BaiduPage baiduPage = PageFactory.initElements(driver, BaiduPage.class);
////        Thread.sleep(2000);
//        baiduPage.login("11","22");
//    }
}

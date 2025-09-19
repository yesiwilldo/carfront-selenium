package cn.xiaoli.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

public class BaiduPage {
    @FindBy(id = "TANGRAM__PSP_11__userName")
    WebElement login_userName;
    @FindBy(id = "TANGRAM__PSP_11__password")
    WebElement login_password;

    public void login(String username,String password) throws InterruptedException {
        Thread.sleep(3000);
        login_userName.sendKeys(username);
        login_password.sendKeys(password);
    }
//    test中文件如下：
//    @BeforeClass
//    public void setup( ) throws InterruptedException {
//        System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
//        driver=new EdgeDriver();
//        driver.manage().window().maximize();
//        driver.get("http://localhost:8080/mine");
////        driver.get("https://www.baidu.com/index.php?tn=75144485_4_dg&ch=1");
////        WebElement loginbtn = driver.findElement(By.id("s-top-loginbtn"));
////        loginbtn.click();
//        Thread.sleep(2000);
//        carMinePage = PageFactory.initElements(driver, CarMinePage.class);
//    }

}

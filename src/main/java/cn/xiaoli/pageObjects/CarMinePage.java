package cn.xiaoli.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarMinePage {
    WebDriver driver;
//    public  CarMinePage(WebDriver driver){
//        this.driver=driver;
//    }
    //元素定位
    //登录按钮
    @FindBy(xpath = "//a[text()='未登录']")
    WebElement login_link;

    //输入用户名
    @FindBy(xpath = "//input[@name=\"用户名\"]")
    WebElement input_username;

    //输入密码
    @FindBy(xpath = "//input[@name=\"密码\"]")
    WebElement input_password;

    //登录按钮
    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement login_inputBtn;

    //登陆后弹窗
    String toast__text;


    // 业务逻辑和操作方法
    // 登录方法
    public void login(String username,String password) throws InterruptedException {
        login_link.click();
        Thread.sleep(3000);
        input_username.sendKeys(username);
        input_password.sendKeys(password);
        login_inputBtn.click();
    }

    //登录后弹窗，返回弹窗字符串信息
    public String getToast__text(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        toast__text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("van-toast__text"))).getText();
        System.out.println(toast__text);
        return toast__text;
    }

}

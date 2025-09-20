package cn.xiaoli.pageObjects;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
public class OrderPage {
    WebDriverWait wait;
    //订单确认界面文字
    @FindBy(className = "nav-m")
    WebElement text_opage;
    //租车数量
    @FindBy(className = "van-stepper__input")
    WebElement carnum;
    //选择时间
    @FindBy(className = "bottom-time")
    WebElement time;
    //确定日期按钮
    @FindBy(xpath = "//span[@class=\"van-button__text\" and text()=\"确认\"]")
    WebElement time_btn;
    //付款出单按钮
    @FindBy(xpath = "//span[@class=\"van-button__text\" and text()=\"点击付款出单\"]")
    WebElement pay_btn;
    //点击付款出单后的提示
    @FindBy(className = "van-toast__text")
    WebElement toast_text;

    public void OrderByDefault(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(time));
        actions.moveToElement(time).click();
        actions.perform();
        wait.until(ExpectedConditions.visibilityOf(time_btn));
        actions.moveToElement(time_btn).click();
        actions.perform();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(pay_btn));
        actions.moveToElement(pay_btn).click();
        actions.perform();
    }
}

package cn.xiaoli.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailCompanyPage {
    //分享按钮
    @FindBy(xpath = "//span[text()=\"分享\"]")
    WebElement share;
    //评论内容输入
    @FindBy(xpath = "//input[@type=\"text\"]")
    WebElement comment_input;
    //发送评论按钮
    @FindBy(xpath = "//span[@class=\"van-button__text\" and text()=\"发送\"]")
    WebElement comment_btn;
    //租车按钮
    @FindBy(xpath = "//div[text()='大众 桑塔纳3']/../button[@type=\"button\"]")
    WebElement car_btn;

}

package cn.xiaoli.pageObjects;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Data
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
    @FindBy(xpath = "//div[text()='别克 GL8 ']/button[@type=\"button\"]")
    WebElement car_btn;
    //非普通用户点击订单后的警告弹窗
    @FindBy(className = "van-dialog__message")
    WebElement dialog__message;

}

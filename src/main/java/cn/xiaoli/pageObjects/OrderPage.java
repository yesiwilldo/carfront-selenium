package cn.xiaoli.pageObjects;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Data
public class OrderPage {
    //订单确认界面文字
    @FindBy(className = "nav-m")
    WebElement text_opage;
}

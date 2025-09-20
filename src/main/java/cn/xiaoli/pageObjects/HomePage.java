package cn.xiaoli.pageObjects;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Data
public class HomePage {
    //首页导航栏
    @FindBy(xpath="//div[text()='首页']")
    WebElement home_link;
    //查看更多
    @FindBy(className = "more")
    WebElement more;
    //第一个公司
    @FindBy(css = ".lists > .card:nth-child(2)")
    WebElement company;
}

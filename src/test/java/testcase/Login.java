package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import javax.xml.xpath.XPath;

public class Login {


    @Test
    public void testLogin() throws InterruptedException {
        System.setProperty("webdriver.edge.driver","src/drivers/msedgedriver.exe");
        WebDriver driver=new EdgeDriver();
        driver.get("http://localhost:8080/mine");
        Thread.sleep(2000);
        //登陆页面
        WebElement login = driver.findElement(By.xpath("//a[text()='未登录']"));
        login.click();
        //登录操作
        driver.findElement(By.xpath("//input[@name=\"用户名\"]")).sendKeys("also");
        driver.findElement(By.xpath("//input[@name=\"密码\"]")).sendKeys("123456");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        //查看账单明细

    }
}

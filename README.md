# carfront-selenium
租赁系统前台测试脚本
技术：Java + Selenium + TestNG，Page Object模式、数据驱动、Allure
测试范围：
img\模块.png
登录功能模块
下订单功能模块：
img\下单测试点.png
运行
testng.xml
批量执行测试用例
mvn clean test
生成allure报告
mvn io.qameta.allure:allure-maven:serve
img\all-tests.png
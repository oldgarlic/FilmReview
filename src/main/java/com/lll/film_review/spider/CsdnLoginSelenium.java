package com.lll.film_review.spider;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Set;

/**
 * 模拟登录
 */
public class CsdnLoginSelenium implements PageProcessor {
    private Set<Cookie> cookies;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000);


    @Override
    public void process(Page page) {
        System.out.println("hahah");
    }

    @Override
    public Site getSite() {
        // TODO Auto-generated method stub
        for(Cookie cookie:cookies) {
            site.addCookie(cookie.getName().toString(), cookie.getValue().toString());
        }
        return site;
    }

    public void Login() {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://accounts.douban.com/passport/login?source=main");
        //登入方式的转变
        //driver.findElement(By.className("login-part")).findElement(By.tagName("a")).click();

        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"account\"]/div[2]/div[2]/div/div[1]/ul[1]/li[2]"));
        element1.click();

        // 防止页面未能及时加载出来而设置一段时间延迟
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //页面调动，短信登录和密码登入不一样
        driver.findElement(By.id("username")).sendKeys("15268853403");
        driver.findElement(By.id("password")).sendKeys("lza123456");

        driver.findElement(By.xpath("//*[@id=\"account\"]/div[2]/div[2]/div/div[2]/div[1]/div[4]/a")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cookies = driver.manage().getCookies();

        driver.close();
    }

    public static void main(String[] args) {
        CsdnLoginSelenium csdn = new CsdnLoginSelenium();
        csdn.Login();
        Spider.create(new CsdnLoginSelenium())  //创建爬虫
                .addUrl("https://www.douban.com/people/76521782/") // 添加目标页面
                .thread(5)  //并发数量
                .run();  //运行

    }
}

package com.lll.film_review.spider;

import com.lll.film_review.pojo.UserInfo;
import com.lll.film_review.utils.FilmReviewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyProcessor implements PageProcessor {
    @Autowired
    private MyPipeline myPipeline;

    public static int time;

    //可以设置请求配置
    private Site site = Site.me()
            .setRetrySleepTime(2000)
            .setRetryTimes(3)
            .setSleepTime(2000);


    //爬虫主要的处理流程
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //爬取信息：用户姓名  星星数量 评语 评论时间 赞同数 反对数 正文链接

        //爬取评论时间
        List<String> commentTime = html.css(".main-hd .main-meta", "text").all();
        //爬取赞同数量
        List<String> agreeStar = html.xpath("//*div[@class='main-bd']/div[3]/a[1]/span/text()").all();
        //爬取反对数量
        List<String> againstStar = html.xpath("//*div[@class='main-bd']/div[3]/a[2]/span/text()").all();
        //爬取正文链接
        List<String> textLink = html.xpath("//*div[@class='main review-item']/div/h2").links().all();
        //爬取用户姓名
        List<String> name = html.css(".main-hd .name", "text").all();
        //爬取星星数
        List<String> startNumber = html.xpath("//div[@class='main review-item']/header/span[1]/@title").all();
        //爬取评论小标题
        List<String> comment = html.css(".main-bd h2 a", "text").all();

        List<UserInfo> userList = new ArrayList<>();
        for (int i = 0; i < name.size(); i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(name.get(i));
            //评价星星数str→int
            userInfo.setStarNumber(FilmReviewUtils.starNumberStrToInt(startNumber.get(i)));
            //String→date
            userInfo.setCommentTime(FilmReviewUtils.strToDate(commentTime.get(i)));
            //String→int  获取的赞同户数量字符串前端有空格
            userInfo.setAgreeStar(FilmReviewUtils.starStrToInt(agreeStar.get(i)));
            //string→int
            userInfo.setAgainstStar(FilmReviewUtils.starStrToInt(againstStar.get(i)));
            userInfo.setTextLink(textLink.get(i));
            userInfo.setComment(comment.get(i));
            userList.add(userInfo);
//            page.putField(userInfo.getUserName(), userInfo);
        }
        page.putField("userInfo", userList);
        //抓取下一页的超链接
        if (--time == 0) return;
        List<String> nextPage = html.css(".paginator span.next").links().all();
        page.addTargetRequests(nextPage);
    }

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * 开启爬虫的入口
     */
//    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100000)
    public void run() {
        FilmReviewUtils.setTime(20);
        System.out.println("爬取哪吒豆瓣影评开始啦--------------");
        long start = System.currentTimeMillis() / 1000;
        Spider.create(new MyProcessor())  //创建爬虫
                .addUrl("https://movie.douban.com/subject/26794435/reviews") // 添加目标页面
                .thread(5)  //并发数量
                .addPipeline(myPipeline) //添加自定义目标
                .run();  //运行
        long end = System.currentTimeMillis() / 1000;
        System.out.println("该次爬取共耗费时间：" + (end - start) + "秒");
    }
}

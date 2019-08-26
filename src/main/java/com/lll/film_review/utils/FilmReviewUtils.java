package com.lll.film_review.utils;

import com.lll.film_review.spider.MyProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilmReviewUtils {
    /**
     * 空字符串处理
     * @param str
     * @return
     */
    public static int starStrToInt(String str) {
        if (str != null && !"".equals(str.trim())) {
            //则字符串不为空或空格
            return Integer.parseInt(str.trim());
        }else {
            return 0;
        }
    }

    /**
     * 字符串日期处理
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        //需要转换的字符串必须和解析的格式相对应
        SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = smp.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 文字评价转数量
     * @param startNumber
     * @return
     */
    public static int starNumberStrToInt(String startNumber) {
        switch (startNumber) {
            case "很差":
                return 1;
            case "较差":
                return 2;
            case "还行":
                return 3;
            case "推荐":
                return 4;
            case "力荐":
                return 5;
            default:
                return 0;
        }
    }

    /**
     * 爬取次数的设定
     * @param time
     */
    public static void setTime(int time) {
        MyProcessor.time = time;
    }
}

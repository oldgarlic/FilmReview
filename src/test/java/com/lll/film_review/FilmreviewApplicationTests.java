package com.lll.film_review;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmreviewApplicationTests {

    @Test
    public void contextLoads() {
    }
    
    @Test
    public void test1(){
        String str = "    ";
        if(str.length()==0){
            System.out.println(str.length());
        }else {
            String trim = str.trim();
            System.out.println(trim);
            trim = null;
            System.out.println(Integer.parseInt(trim));
        }
    }

    @Test
    public void test2(){
        String str1 = "";
        String str2 = "";
        String str3 = "  ";
        String str4 = "      ";
        System.out.println(str1==str2);
        System.out.println(str4.trim().equals(str1));
        System.out.println(str1.equals(str3));
        System.out.println(str4.length());
        System.out.println(str4.trim().length());
    }

    public static boolean judgeNullString(String str){
        if (str != null && !"".equals(str.trim())) {
            //则字符串不为空或空格
            return false;
        }
        return true;
    }
}

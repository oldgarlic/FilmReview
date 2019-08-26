package com.lll.film_review.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 监听事件：监听SPring容器初始化完成
 */

@Component
public class StartEndListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()==null){
            System.out.println("Spring 容器加载完成------------");
        }
    }
}

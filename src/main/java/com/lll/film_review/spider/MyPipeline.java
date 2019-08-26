package com.lll.film_review.spider;


import com.lll.film_review.pojo.UserInfo;
import com.lll.film_review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * 实现Pineline，重写process方法，修改数据的输出方向
 */
@Component
public class MyPipeline implements Pipeline {


    @Autowired
    private UserService userService ;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<UserInfo> userInfo = (List<UserInfo>)resultItems.get("userInfo");

        if (userInfo != null) {
            //进行判断数据库是否已经存在该数据
            for (int i = 0; i < userInfo.size(); i++) {
                if (!this.userService.judgeHaveUserInfo(userInfo.get(i))){
                    this.userService.save(userInfo.get(i));
                }
            }
        }
    }

}

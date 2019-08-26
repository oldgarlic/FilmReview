package com.lll.film_review.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("film_review")
public class UserInfo {
    //用户姓名  星星数量 评语 评论时间 赞同数 反对数 正文链接
    private int id;
    private String userName;
    private int starNumber;
    private String comment;
    private Date commentTime;
    private int agreeStar;
    private int againstStar;
    private String textLink;

}

package com.lll.film_review.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lll.film_review.mapper.UserMapper;
import com.lll.film_review.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存UserInfo
     * @param userInfo
     */
    public void save(UserInfo userInfo){
        userMapper.insert(userInfo);
    }

    /**
     * 根据用户名和点赞数量来判断数据是否已存在
     * 有数据，返回true，反之返回false
     * @param userInfo
     * @return
     */
    @Transactional
    public boolean judgeHaveUserInfo(UserInfo userInfo){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName",userInfo.getUserName())
                .eq("agreeStar",userInfo.getAgreeStar());
        List<UserInfo> result = userMapper.selectList(queryWrapper);
        if(result.size()==0){
            return false;
        }else {
            return true;

        }
    }
}

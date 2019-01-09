package com.info_system.service;

import com.info_system.dao.FollowDao;
import com.info_system.entity.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    private FollowDao followDao;

    /**
     * 我关注的人
     * @param userId
     */
    public List<Follow> listFollow(int userId) {
        System.out.println("listFollow in FollowService");
        return followDao.listFollow(userId);

    }

    /**
     * 关注我的人
     * @param userId
     */
    public List<Follow> listFans(int userId) {
        System.out.println("listFans in FollowService");
        return followDao.listFans(userId);
    }

    /**
     * 我未关注的人
     * @param username
     * @param userId
     */
    public List<Follow> listUnFollow(String username, int userId) {
        System.out.println("listUnFollow in FollowService");
        username = '%'+username+'%';
        System.out.println(username);
        return followDao.listUnFollow(username,userId);
    }


    /**
     * 删除关注
     * @param followId
     */
    public void deleteFollow(int followId) {
        System.out.println("deleteFollow in FollowService");
        followDao.deleteFollow(followId);

    }

    /**
     * 添加关注
     * @param follow
     */
    public void addFollow(Follow follow) {
        System.out.println("addFollow in FollowService");
        followDao.addFollow(follow);
    }
}

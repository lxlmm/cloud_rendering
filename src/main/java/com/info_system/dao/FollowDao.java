package com.info_system.dao;

import com.info_system.entity.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowDao {

    /**
     * 我关注的人
     * @param userId
     */
    List<Follow> listFollow(int userId);

    /**
     * 关注我的人
     * @param userId
     */
    List<Follow> listFans(int userId);

    /**
     * 我未关注的人
     */
    List<Follow> listUnFollow(@Param("username") String username, @Param("userId") int userId);

    /**
     * 删除关注
     * @param followId
     */
    void deleteFollow(int followId);


    /**
     * 添加关注
     * @param follow
     */
    void addFollow(Follow follow);
}

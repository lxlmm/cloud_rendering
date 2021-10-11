package com.info_system.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private int deleteFlag;
    private int adminFlag;
    private int fansNum;
    private String userPic;
    private int followNum;
    private int blogNum;

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }




    public User(Integer id, int deleteFlag) {
        this.id = id;
        this.deleteFlag = deleteFlag;
    }


    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }




    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }





    public int getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(int adminFlag) {
        this.adminFlag = adminFlag;
    }

    public User() {
        super();
    }

    public User(String name, String sex, String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", adminFlag=" + adminFlag +
                ", fansNum=" + fansNum +
                ", userPic='" + userPic + '\'' +
                ", followNum=" + followNum +
                ", blogNum=" + blogNum +
                '}';
    }
}

package com.info_system.entity;


import java.util.Date;
import java.util.List;

public class Blog implements Comparable<Blog> {
    private int blogId;
    private String blogTitle;
    private String blogContent;
    private User user;
    private Date blogTime;
    private int deleteFlag;
    private String beforeBlogPic;
    private String afterBlogPic;
    private String blogPreviewPic;
    private String username;
    private int likeCount;
    private int commentCount;
    private boolean hasLike;//判断当前用户是否有点赞该博文
    private boolean hasComment;//判断当前用户是否有评论该博文

    public Blog() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public Blog(int blogId, int deleteFlag) {
        this.blogId = blogId;
        this.deleteFlag = deleteFlag;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<User> getInformUser() {
        return informUser;
    }

    public void setInformUser(List<User> informUser) {
        this.informUser = informUser;
    }

    private List<User> informUser;


    public boolean isHasLike() {
        return hasLike;
    }

    public void setHasLike(boolean hasLike) {
        this.hasLike = hasLike;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }


    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }


    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }


    public Date getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(Date blogTime) {
        this.blogTime = blogTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", user=" + user +
                ", blogTime=" + blogTime +
                ", deleteFlag=" + deleteFlag +
                ", beforeblogPic='" + beforeBlogPic + '\'' +
                ", afterblogPic='" + afterBlogPic + '\'' +
                ", blogPreviewPic='" + blogPreviewPic + '\'' +
                ", username='" + username + '\'' +
                ", hasLike=" + hasLike +
                ", hasComment=" + hasComment +
                ", informUser=" + informUser +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                '}';
    }

    //排序方法
    public int compareTo(Blog o) {
        return likeCount < o.getLikeCount() ? 1 : (likeCount > o.getLikeCount() ? -1 : 0);
    }

    public String getBlogPreviewPic() {
        return blogPreviewPic;
    }

    public void setBlogPreviewPic(String blogPreviewPic) {
        this.blogPreviewPic = blogPreviewPic;
    }

    public String getBeforeBlogPic() {
        return beforeBlogPic;
    }

    public void setBeforeBlogPic(String beforeBlogPic) {
        this.beforeBlogPic = beforeBlogPic;
    }

    public String getAfterBlogPic() {
        return afterBlogPic;
    }

    public void setAfterBlogPic(String afterBlogPic) {
        this.afterBlogPic = afterBlogPic;
    }
}

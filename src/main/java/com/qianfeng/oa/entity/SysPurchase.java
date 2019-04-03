package com.qianfeng.oa.entity;

import java.util.Date;

public class SysPurchase {
    private Long id;

    private String title;

    private String content;

    private Long userId;

    private String userName;

    private Long money;

    private Date createtime;

    private Byte flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }
}
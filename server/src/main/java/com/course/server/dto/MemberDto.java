package com.course.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MemberDto {

    /**
     * id
     */
    private String id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像url
     */
    private String photo;

    /**
     * 注册时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;

    /**
     * 图片验证码
     */
    private String imageCode;

    /**
     * 图片验证码token
     */
    private String imageCodeToken;

    /**
     * 短信验证码
     */
    private String smsCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImageCodeToken() {
        return imageCodeToken;
    }

    public void setImageCodeToken(String imageCodeToken) {
        this.imageCodeToken = imageCodeToken;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", registerTime=").append(registerTime);
        sb.append(", imageCode='").append(imageCode).append('\'');
        sb.append(", imageCodeToken='").append(imageCodeToken).append('\'');
        sb.append(", smsCode='").append(smsCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

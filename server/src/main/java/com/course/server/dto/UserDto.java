package com.course.server.dto;


public class UserDto {

    /**
     * id
     */
    private String id;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String imageCode;

    /**
     * 图片验证码token
     */
    private String imageCodeToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", imageCode='").append(imageCode).append('\'');
        sb.append(", imageCodeToken='").append(imageCodeToken).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
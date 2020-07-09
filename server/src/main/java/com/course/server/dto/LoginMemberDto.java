package com.course.server.dto;

public class LoginMemberDto {

    private String id;

    private String mobile;

    private String name;

    private String token;

    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginMemberDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

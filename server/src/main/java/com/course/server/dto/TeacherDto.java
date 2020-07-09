package com.course.server.dto;


public class TeacherDto {

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String image;

    /**
     * 职位
     */
    private String position;

    /**
     * 座右铭
     */
    private String motto;

    /**
     * 简介
     */
    private String intro;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", nickname=").append(nickname);
        sb.append(", image=").append(image);
        sb.append(", position=").append(position);
        sb.append(", motto=").append(motto);
        sb.append(", intro=").append(intro);
        sb.append("]");
        return sb.toString();
    }

}
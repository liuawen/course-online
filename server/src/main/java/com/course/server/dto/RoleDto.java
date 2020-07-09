package com.course.server.dto;


import java.util.List;

public class RoleDto {

    /**
     * id
     */
    private String id;

    /**
     * 角色
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    private List<String> resourceIds;

    private List<String> userIds;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoleDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", resourceIds=").append(resourceIds);
        sb.append(", userIds=").append(userIds);
        sb.append('}');
        return sb.toString();
    }

}
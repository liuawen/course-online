package com.course.server.dto;


import java.util.List;

public class ResourceDto {

    /**
     * id
     */
    private String id;

    /**
     * 名称|菜单或按钮
     */
    private String name;

    /**
     * 页面|路由
     */
    private String page;

    /**
     * 请求|接口
     */
    private String request;

    /**
     * 父id
     */
    private String parent;

    private List<ResourceDto> children;

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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<ResourceDto> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResourceDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", page='").append(page).append('\'');
        sb.append(", request='").append(request).append('\'');
        sb.append(", parent='").append(parent).append('\'');
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }

}
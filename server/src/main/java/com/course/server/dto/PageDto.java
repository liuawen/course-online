package com.course.server.dto;

import java.util.List;

public class PageDto<T> {

    /**
     * 当前页码
     */
    protected int page;

    /**
     * 每页条数
     */
    protected int size;

    /**
     * 总条数
     */
    protected long total;

    protected List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);

        sb.append('}');
        return sb.toString();
    }
}

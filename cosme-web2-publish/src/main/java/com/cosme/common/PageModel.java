//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cosme.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PageModel<T> implements Serializable {
    private static final PageModel EMPTY = new PageModel();
    private List<T> data = Collections.emptyList();
    private int totalCount;
    private int page;
    private Integer pageSize;

    public static <T> PageModel<T> build(List<T> data, int totalCount) {
        return build(data, totalCount, 0, (Integer)null);
    }

    public static <T> PageModel<T> build(List<T> data, int totalCount, int page, Integer pageSize) {
        return new PageModel(data, totalCount, page, pageSize);
    }

    public static <T> PageModel<T> emptyModel() {
        return EMPTY;
    }

    public static <T> PageModel<T> emptyModel(int page, Integer pageSize) {
        return build(Collections.emptyList(), 0, page, pageSize);
    }

    public boolean hasData() {
        return this.data.size() > 0;
    }

    public List<T> getData() {
        return this.data;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getPage() {
        return this.page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof PageModel)) {
            return false;
        } else {
            PageModel other = (PageModel)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label43: {
                    List this$data = this.getData();
                    List other$data = other.getData();
                    if(this$data == null) {
                        if(other$data == null) {
                            break label43;
                        }
                    } else if(this$data.equals(other$data)) {
                        break label43;
                    }

                    return false;
                }

                if(this.getTotalCount() != other.getTotalCount()) {
                    return false;
                } else if(this.getPage() != other.getPage()) {
                    return false;
                } else {
                    Integer this$pageSize = this.getPageSize();
                    Integer other$pageSize = other.getPageSize();
                    if(this$pageSize == null) {
                        if(other$pageSize != null) {
                            return false;
                        }
                    } else if(!this$pageSize.equals(other$pageSize)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageModel;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        List $data = this.getData();
        int result1 = result * 59 + ($data == null?0:$data.hashCode());
        result1 = result1 * 59 + this.getTotalCount();
        result1 = result1 * 59 + this.getPage();
        Integer $pageSize = this.getPageSize();
        result1 = result1 * 59 + ($pageSize == null?0:$pageSize.hashCode());
        return result1;
    }

    @Override
    public String toString() {
        return "PageModel(data=" + this.getData() + ", totalCount=" + this.getTotalCount() + ", page=" + this.getPage() + ", pageSize=" + this.getPageSize() + ")";
    }

    private PageModel() {
    }

    private PageModel(List<T> data, int totalCount, int page, Integer pageSize) {
        this.data = data;
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
    }
}

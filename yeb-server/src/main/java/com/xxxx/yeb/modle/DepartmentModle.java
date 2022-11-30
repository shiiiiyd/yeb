package com.xxxx.yeb.modle;

import java.util.List;

public class DepartmentModle {
    private Integer id;
    private String name;
    private Integer parentId;

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public List<DepartmentModle> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentModle> children) {
        this.children = children;
    }

    private Boolean isParent;
    private List<DepartmentModle> children;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "DepartmentModle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", isParent=" + isParent +
                ", children=" + children +
                '}';
    }
}

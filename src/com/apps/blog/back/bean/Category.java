package com.apps.blog.back.bean;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private int delFlag;
	private Date createTime;
    private Date updateTime;
	
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
    public int getDelFlag() {
        return delFlag;
    }
    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
	
	
}

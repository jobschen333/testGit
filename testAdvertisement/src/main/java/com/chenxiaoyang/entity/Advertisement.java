package com.chenxiaoyang.entity;

import java.util.Date;

/**
 *
 * 首页广告
 *  @author chenxiaoyang
 */
public class Advertisement {

    private int id; //主键id

    private String title; //标题名称

    private String surfacePlotAdress; //图片地址

    private String webUrl; //网页链接

    private String wapUrl; //wap链接

    private Date createTime; //创建时间

    private String surfacePlotName; //封面图名称

    /**
     * 主键id
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * 主键id
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 图片地址
     * @return the sufacePlotAdress
     */
    public String getSurfacePlotAdress() {
        return surfacePlotAdress;
    }

    /**
     * 图片地址
     * @param surfacePlotAdress the sufacePlotAdress to set
     */
    public void setSurfacePlotAdress(String surfacePlotAdress) {
        this.surfacePlotAdress = surfacePlotAdress;
    }

    /**
     * 网页链接
     * @return the webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 网页链接
     * @param webUrl the webUrl to set
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * wap链接
     * @return the wapUrl
     */
    public String getWapUrl() {
        return wapUrl;
    }

    /**
     * wap链接
     * @param wapUrl the wapUrl to set
     */
    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    /**
     * 创建时间
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 封面图名称
     * @return the sufacePlotName
     */
    public String getSurfacePlotName() {
        return surfacePlotName;
    }

    /**
     * 封面图名称
     * @param surfacePlotName the sufacePlotName to set
     */
    public void setSurfacePlotName(String surfacePlotName) {
        this.surfacePlotName = surfacePlotName;
    }
}

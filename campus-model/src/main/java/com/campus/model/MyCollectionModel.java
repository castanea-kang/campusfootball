package com.campus.model;

import com.campus.dao.pojo.TblMycollection;

/**
 * TO DO :
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-09-05 16:11
 */
public class MyCollectionModel extends TblMycollection {
    private String title;
    private String Content;
    private String url;
    private String imagesUrl;

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets new Content.
     *
     * @param Content New value of Content.
     */
    public void setContent(String Content) {
        this.Content = Content;
    }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets url.
     *
     * @return Value of url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets Content.
     *
     * @return Value of Content.
     */
    public String getContent() {
        return Content;
    }

    /**
     * Sets new url.
     *
     * @param url New value of url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets new imagesUrl.
     *
     * @param imagesUrl New value of imagesUrl.
     */
    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    /**
     * Gets imagesUrl.
     *
     * @return Value of imagesUrl.
     */
    public String getImagesUrl() {
        return imagesUrl;
    }
}

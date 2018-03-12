package com.example.smita.mycity;


public class Content {

    private String contentName, contentAddress, contentRating,contentImgUrl, contentStatus;

    public Content(String contentName, String contentAddress, String contentRating, String contentImgUrl, String contentStatus) {
        this.contentName = contentName;
        this.contentAddress = contentAddress;
        this.contentRating = contentRating;
        this.contentImgUrl = contentImgUrl;
        this.contentStatus = contentStatus;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentAddress() {
        return contentAddress;
    }

    public void setContentAddress(String contentAddress) {
        this.contentAddress = contentAddress;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getContentImgUrl() {
        return contentImgUrl;
    }

    public void setContentImgUrl(String contentImgUrl) {
        this.contentImgUrl = contentImgUrl;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }
}

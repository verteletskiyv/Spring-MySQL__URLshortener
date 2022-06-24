package com.study.urlshortener_spring.models;

import javax.persistence.*;

@Entity
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url_full, url_short;

    public Links() {
    }

    public Links(String url_full, String url_short) {
        this.url_full = url_full;
        this.url_short = url_short;
    }

    public long getId() {
        return id;
    }

    public String getUrl_full() {
        return url_full;
    }

    public void setUrl_full(String url_full) {
        this.url_full = url_full;
    }

    public String getUrl_short() {
        return url_short;
    }

    public void setUrl_short(String url_short) {
        this.url_short = url_short;
    }
}

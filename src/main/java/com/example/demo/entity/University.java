package com.example.demo.entity;

public class University {
    private String name;
    private String[] web_pages;
    private String domain;

    public University(String name, String[] web_pages, String domain) {
        this.name = name;
        this.web_pages = web_pages;
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeb_pages(String[] web_pages) {
        this.web_pages = web_pages;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public String[] getWeb_pages() {
        return web_pages;
    }

    public String getDomain() {
        return domain;
    }
}

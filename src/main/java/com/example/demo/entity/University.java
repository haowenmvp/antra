package com.example.demo.entity;

import java.util.List;

public class University {
    private String name;
    private List<String> web_pages;
    private List<String> domain;

    public University (){
    }

    public University(String name, List<String> web_pages, List<String> domain) {
        this.name = name;
        this.web_pages = web_pages;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public List<String> getWeb_pages() {
        return web_pages;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setWeb_pages(List<String> web_pages) {
        this.web_pages = web_pages;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }




}

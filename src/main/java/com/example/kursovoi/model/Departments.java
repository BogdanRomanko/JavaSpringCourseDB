package com.example.kursovoi.model;

public class Departments {
    private Long id;
    private String title;
    private Long head_of_dep_id;

    public Departments(Long id, String title, Long head_of_dep_id) {
        this.id = id;
        this.title = title;
        this.head_of_dep_id = head_of_dep_id;
    }

    public Departments(String title, Long head_of_dep_id) {
        this.title = title;
        this.head_of_dep_id = head_of_dep_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getHead_of_dep_id() {
        return head_of_dep_id;
    }

    public void setHead_of_dep_id(Long head_of_dep_id) {
        this.head_of_dep_id = head_of_dep_id;
    }
}

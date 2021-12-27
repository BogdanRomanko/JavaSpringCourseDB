package com.example.kursovoi.model;

public class Groups {
    private Long id;
    private String title;
    private Long dep_id;
    private Long praestor_id;
    private Long sum_year;
    private String direction_on_study;
    private Long curator_id;
    private Long size;

    public Groups(Long id, String title, Long dep_id, Long praestor_id, Long sum_year, String direction_on_study, Long curator_id, Long size) {
        this.id = id;
        this.title = title;
        this.dep_id = dep_id;
        this.praestor_id = praestor_id;
        this.sum_year = sum_year;
        this.direction_on_study = direction_on_study;
        this.curator_id = curator_id;
        this.size = size;
    }

    public Groups(String title, Long dep_id, Long praestor_id, Long sum_year, String direction_on_study, Long curator_id, Long size) {
        this.title = title;
        this.dep_id = dep_id;
        this.praestor_id = praestor_id;
        this.sum_year = sum_year;
        this.direction_on_study = direction_on_study;
        this.curator_id = curator_id;
        this.size = size;
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

    public Long getDep_id() {
        return dep_id;
    }

    public void setDep_id(Long dep_id) {
        this.dep_id = dep_id;
    }

    public Long getPraestor_id() {
        return praestor_id;
    }

    public void setPraestor_id(Long praestor_id) {
        this.praestor_id = praestor_id;
    }

    public Long getSum_year() {
        return sum_year;
    }

    public void setSum_year(Long sum_year) {
        this.sum_year = sum_year;
    }

    public String getDirection_on_study() {
        return direction_on_study;
    }

    public void setDirection_on_study(String direction_on_study) {
        this.direction_on_study = direction_on_study;
    }

    public Long getCurator_id() {
        return curator_id;
    }

    public void setCurator_id(Long curator_id) {
        this.curator_id = curator_id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}

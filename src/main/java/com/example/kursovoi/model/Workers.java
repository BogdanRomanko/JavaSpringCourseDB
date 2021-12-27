package com.example.kursovoi.model;

public class Workers {
    private Long id;
    private String name;
    private Long dep_id;
    private String role;

    public Workers(Long id, String name, Long dep_id, String role) {
        this.id = id;
        this.name = name;
        this.dep_id = dep_id;
        this.role = role;
    }

    public Workers(String name, Long dep_id, String role){
        this.name = name;
        this.dep_id = dep_id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDep_id() {
        return dep_id;
    }

    public void setDep_id(Long dep_id) {
        this.dep_id = dep_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

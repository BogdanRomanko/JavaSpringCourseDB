package com.example.kursovoi.model;

public class Students {
    private Long id;
    private String name;
    private Long group_id;
    private Long dep_id;
    private String type_of_training;
    private Long is_budget;
    private Long is_praestor;

    public Students(Long id, String name, Long group_id, Long dep_id, String type_of_training, Long is_budget, Long is_praestor) {
        this.id = id;
        this.name = name;
        this.group_id = group_id;
        this.dep_id = dep_id;
        this.type_of_training = type_of_training;
        this.is_budget = is_budget;
        this.is_praestor = is_praestor;
    }

    public Students(String name, Long group_id, Long dep_id, String type_of_training, Long is_budget, Long is_praestor) {
        this.name = name;
        this.group_id = group_id;
        this.dep_id = dep_id;
        this.type_of_training = type_of_training;
        this.is_budget = is_budget;
        this.is_praestor = is_praestor;
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

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getDep_id() {
        return dep_id;
    }

    public void setDep_id(Long dep_id) {
        this.dep_id = dep_id;
    }

    public String getType_of_training() {
        return type_of_training;
    }

    public void setType_of_training(String type_of_training) {
        this.type_of_training = type_of_training;
    }

    public Long getIs_budget() {
        return is_budget;
    }

    public void setIs_budget(Long is_budget) {
        this.is_budget = is_budget;
    }

    public Long getIs_praestor() {
        return is_praestor;
    }

    public void setIs_praestor(Long is_praestor) {
        this.is_praestor = is_praestor;
    }
}

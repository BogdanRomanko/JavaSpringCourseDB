package com.example.kursovoi.model;

public class Subjects {
    private Long id;
    private String title;
    private Long teacher_id;
    private Long is_exam;

    public Subjects(Long id, String title, Long teacher_id, Long is_exam) {
        this.id = id;
        this.title = title;
        this.teacher_id = teacher_id;
        this.is_exam = is_exam;
    }

    public Subjects(String title, Long teacher_id, Long is_exam) {
        this.title = title;
        this.teacher_id = teacher_id;
        this.is_exam = is_exam;
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

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Long getIs_exam() {
        return is_exam;
    }

    public void setIs_exam(Long is_exam) {
        this.is_exam = is_exam;
    }
}

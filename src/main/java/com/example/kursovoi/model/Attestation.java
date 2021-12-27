package com.example.kursovoi.model;

public class Attestation {
    private Long id;
    private Long subject_id;
    private Long group_id;
    private Long student_id;
    private Long value;

    public Attestation(Long id, Long subject_id, Long group_id, Long student_id, Long value) {
        this.id = id;
        this.subject_id = subject_id;
        this.group_id = group_id;
        this.student_id = student_id;
        this.value = value;
    }

    public Attestation(Long subject_id, Long group_id, Long student_id, Long value) {
        this.subject_id = subject_id;
        this.group_id = group_id;
        this.student_id = student_id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}

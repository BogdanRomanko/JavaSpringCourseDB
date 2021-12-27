package com.example.kursovoi.model;

import java.sql.Date;

public class Results {
    private Long id;
    private Long statement_id;
    private Date date;
    private Long classroom;
    private Long group_id;
    private Long subject_id;
    private Long teacher_id;
    private Long student_id;
    private Long value;

    public Results(Long id, Long statement_id, Date date, Long classroom, Long group_id, Long subject_id, Long teacher_id, Long student_id, Long value) {
        this.id = id;
        this.statement_id = statement_id;
        this.date = date;
        this.classroom = classroom;
        this.group_id = group_id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.value = value;
    }

    public Results(Long statement_id, Date date, Long classroom, Long group_id, Long subject_id, Long teacher_id, Long student_id, Long value) {
        this.statement_id = statement_id;
        this.date = date;
        this.classroom = classroom;
        this.group_id = group_id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatement_id() {
        return statement_id;
    }

    public void setStatement_id(Long statement_id) {
        this.statement_id = statement_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getClassroom() {
        return classroom;
    }

    public void setClassroom(Long classroom) {
        this.classroom = classroom;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
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

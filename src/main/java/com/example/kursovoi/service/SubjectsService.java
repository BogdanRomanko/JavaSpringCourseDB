package com.example.kursovoi.service;

import com.example.kursovoi.SQLConfig;
import com.example.kursovoi.exception.SubjectsException;
import com.example.kursovoi.model.Subjects;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class SubjectsService {

    public ArrayList<Subjects> getAllSubjects() throws SubjectsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM subjects";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Subjects> subjects = new ArrayList<>();

            while (rs.next()) {
                Subjects subject = new Subjects(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4));
                subjects.add(subject);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return subjects;
        } catch (Exception ev) {
            throw new SubjectsException("Не удалость получить все дисциплины");
        }
    }

    public Subjects getSubject(Long id) throws SubjectsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM subjects WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Subjects subject = null;

            while (rs.next()) {
                subject = new Subjects(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4));
            }

            statement.close();
            rs.close();
            config.closeConnection();

            if (subject == null)
                throw new SubjectsException("Такой дисциплины не существует");

            return subject;
        } catch (Exception ev) {
            throw new SubjectsException("Такой дисциплины не существует");
        }
    }

    public void deleteSubject(Long id) throws SubjectsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE subjects FROM subjects WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new SubjectsException("Не удалось удалить дисциплину");
        }
    }

    public void editSubject(Long id, Subjects newSubject) throws SubjectsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE subjects SET title=?, teacher_id=?, is_exam=? WHERE id=?"
            );

            statement.setString(1, newSubject.getTitle());
            statement.setLong(2, newSubject.getTeacher_id());
            statement.setLong(3, newSubject.getIs_exam());
            statement.setLong(4, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new SubjectsException("Не удалось изменить дисциплину");
        }
    }

    public void addSubject(Subjects subject) throws SubjectsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO subjects(title, teacher_id, is_exam) VALUES(?, ?, ?)"
            );

            statement.setString(1, subject.getTitle());
            statement.setLong(2, subject.getTeacher_id());
            statement.setLong(3, subject.getIs_exam());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new SubjectsException("Не удалось добавить дисциплину");
        }
    }

}

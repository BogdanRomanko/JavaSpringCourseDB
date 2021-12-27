package com.example.kursovoi.service;

import com.example.kursovoi.SQLConfig;
import com.example.kursovoi.exception.StudentsException;
import com.example.kursovoi.model.Students;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class StudentsService {

    public ArrayList<Students> getAllStudents() throws StudentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM students";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Students> students = new ArrayList<>();

            while (rs.next()) {
                Students student = new Students(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(5),
                        rs.getLong(6),
                        rs.getLong(7)
                );
                students.add(student);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return students;
        } catch (Exception ev) {
            throw new StudentsException("Не удалось получить всех студентов");
        }
    }

    public Students getStudent(Long id) throws StudentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM students WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Students student = null;

            while (rs.next()) {
                student = new Students(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(5),
                        rs.getLong(6),
                        rs.getLong(7)
                );
            }

            statement.close();
            rs.close();
            config.closeConnection();

            if (student == null)
                throw new StudentsException("Такой студент не найден");

            return student;
        } catch (Exception ev) {
            throw new StudentsException("Такого студента не существует");
        }
    }

    public void deleteStudent(Long id) throws StudentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE students FROM students WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new StudentsException("Не удалось удалить студента");
        }
    }

    public void editStudent(Long id, Students newStudent) throws StudentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET full_name=?, group_id=?, dep_id=?, type_of_training=?, is_budget=?, is_praestor=? WHERE id=?"
            );

            statement.setString(1, newStudent.getName());
            statement.setLong(2, newStudent.getGroup_id());
            statement.setLong(3, newStudent.getDep_id());
            statement.setString(4, newStudent.getType_of_training());
            statement.setLong(5, newStudent.getIs_budget());
            statement.setLong(6, newStudent.getIs_praestor());
            statement.setLong(7, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new StudentsException("Не удалось изменить студента");
        }
    }

    public void addStudent(Students student) throws StudentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students(full_name, group_id, dep_id, type_of_training, is_budget, is_praestor) VALUES(?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, student.getName());
            statement.setLong(2, student.getGroup_id());
            statement.setLong(3, student.getDep_id());
            statement.setString(4, student.getType_of_training());
            statement.setLong(5, student.getIs_budget());
            statement.setLong(6, student.getIs_praestor());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new StudentsException("Не удалось добавить студента");
        }
    }
}

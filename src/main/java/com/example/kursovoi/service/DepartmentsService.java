package com.example.kursovoi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.kursovoi.SQLConfig;
import com.example.kursovoi.exception.DepartmentsException;
import com.example.kursovoi.model.Departments;
import org.springframework.stereotype.Service;

@Service
public class DepartmentsService {

    public ArrayList<Departments> getAllDepartments() throws DepartmentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM departments";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Departments> departments = new ArrayList<>();

            while (rs.next()) {
                Departments department = new Departments(rs.getLong(1), rs.getString(2), rs.getLong(3));
                departments.add(department);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return departments;
        } catch (Exception ev) {
            throw new DepartmentsException("Не удалость получить все кафедры");
        }
    }

    public Departments getDepartment(Long id) throws DepartmentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM departments WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Departments department = null;

            while (rs.next())
                department = new Departments(rs.getLong(1), rs.getString(2), rs.getLong(3));

            statement.close();
            rs.close();
            config.closeConnection();

            if (department == null)
                throw new DepartmentsException("Такой кафедры не существует");

            return department;
        } catch (Exception ev) {
            throw new DepartmentsException("Такой кафедры не существует");
        }
    }

    public void deleteDepartment(Long id) throws DepartmentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE departments FROM departments WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new DepartmentsException("Не удалось удалить кафедру");
        }
    }

    public void editDepartment(Long id, Departments newDepartment) throws DepartmentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE departments SET title=?, head_of_dep_id=? WHERE id=?"
            );

            statement.setString(1, newDepartment.getTitle());
            statement.setLong(2, newDepartment.getHead_of_dep_id());
            statement.setLong(3, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new DepartmentsException("Не удалось изменить кафедру");
        }
    }

    public void addDepartment(Departments department) throws DepartmentsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO departments(title, head_of_dep_id) VALUES(?, ?)"
            );

            statement.setString(1, department.getTitle());
            statement.setLong(2, department.getHead_of_dep_id());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new DepartmentsException("Не удалось добавить кафедру");
        }
    }

}

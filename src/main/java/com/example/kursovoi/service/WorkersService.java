package com.example.kursovoi.service;

import com.example.kursovoi.exception.WorkersException;
import com.example.kursovoi.model.Workers;

import java.sql.*;
import java.util.ArrayList;

import com.example.kursovoi.SQLConfig;
import org.springframework.stereotype.Service;

@Service
public class WorkersService {

    public ArrayList<Workers> getAllWorkers() throws WorkersException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM workers";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Workers> workers = new ArrayList<>();

            while (rs.next()) {
                Workers worker = new Workers(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4));
                workers.add(worker);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return workers;
        } catch (Exception ev) {
            throw new WorkersException("Произошла ошибка");
        }
    }

    public Workers getWorker(Long id) throws WorkersException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM workers WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Workers worker = null;

            while (rs.next()) {
                worker = new Workers(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4));
            }

            statement.close();
            rs.close();
            config.closeConnection();

            if (worker == null)
                throw new WorkersException("Такой работник не найден");

            return worker;
        } catch (Exception ev) {
            throw new WorkersException("Такого работника не существует");
        }
    }

    public void deleteWorker(Long id) throws WorkersException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE workers FROM workers WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new WorkersException("Не удалось удалить работника");
        }
    }

    public void editWorker(Long id, Workers newWorker) throws WorkersException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE workers SET full_name=?, dep_id=?, role=? WHERE id=?"
            );

            statement.setString(1, newWorker.getName());
            statement.setLong(2, newWorker.getDep_id());
            statement.setString(3, newWorker.getRole());
            statement.setLong(4, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new WorkersException("Не удалось изменить сотрудника");
        }
    }

    public void addWorker(Workers worker) throws WorkersException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO workers(full_name, dep_id, role) VALUES(?, ?, ?)"
            );

            statement.setString(1, worker.getName());
            statement.setLong(2, worker.getDep_id());
            statement.setString(3, worker.getRole());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new WorkersException("Не удалось добавить сотрудника");
        }
    }
}

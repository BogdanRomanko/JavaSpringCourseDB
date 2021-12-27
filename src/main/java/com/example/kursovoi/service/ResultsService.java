package com.example.kursovoi.service;

import com.example.kursovoi.SQLConfig;
import com.example.kursovoi.exception.ResultsException;
import com.example.kursovoi.model.Results;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class ResultsService {

    public ArrayList<Results> getAllResults() throws ResultsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM results";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Results> results = new ArrayList<>();

            while (rs.next()) {
                Results result = new Results(rs.getLong(1), rs.getLong(2), rs.getDate(3), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9));
                results.add(result);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return results;
        } catch (Exception ev) {
            throw new ResultsException("Не удалость получить все результаты");
        }
    }

    public Results getResult(Long id) throws ResultsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM results WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Results result = null;

            while (rs.next())
                result = new Results(rs.getLong(1), rs.getLong(2), rs.getDate(3), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9));

            statement.close();
            rs.close();
            config.closeConnection();

            if (result == null)
                throw new ResultsException("Такого результата не существует");

            return result;
        } catch (Exception ev) {
            throw new ResultsException("Такого результата не существует");
        }
    }

    public void deleteResult(Long id) throws ResultsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE results FROM results WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new ResultsException("Не удалось удалить результат");
        }
    }

    public void editResult(Long id, Results newResult) throws ResultsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE results SET statement_id=?, date=?, classroom=?, group_id=?, subject_id=?, teacher_id=?, student_id=?, value=? WHERE id=?"
            );

            statement.setLong(1, newResult.getStatement_id());
            statement.setDate(2, newResult.getDate());
            statement.setLong(3, newResult.getClassroom());
            statement.setLong(4, newResult.getGroup_id());
            statement.setLong(5, newResult.getSubject_id());
            statement.setLong(6, newResult.getTeacher_id());
            statement.setLong(7, newResult.getStudent_id());
            statement.setLong(8, newResult.getValue());
            statement.setLong(9, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new ResultsException("Не удалось изменить результат");
        }
    }

    public void addResult(Results result) throws ResultsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO results(statement_id, date, classroom, group_id, subject_id, teacher_id, student_id, value) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setLong(1, result.getStatement_id());
            statement.setDate(2, result.getDate());
            statement.setLong(3, result.getClassroom());
            statement.setLong(4, result.getGroup_id());
            statement.setLong(5, result.getSubject_id());
            statement.setLong(6, result.getTeacher_id());
            statement.setLong(7, result.getStudent_id());
            statement.setLong(8, result.getValue());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new ResultsException("Не удалось добавить результат");
        }
    }

}

package com.example.kursovoi.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.kursovoi.SQLConfig;
import org.springframework.stereotype.Service;

@Service
public class AdditionalService {

    public ArrayList<Object> getPraestors() throws Exception {
        ArrayList<Object> result = new ArrayList<>();

        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT s.full_name, g.title FROM students s INNER JOIN groups g ON (g.praepostor_id = s.id)";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>();
                row.put("name", rs.getString(1));
                row.put("title", rs.getString(2));
                result.add(row);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return result;
        } catch (Exception ev) {
            throw new Exception(ev.getMessage());
        }
    }

    public ArrayList<Object> getCurators() throws Exception {
        ArrayList<Object> result = new ArrayList<>();

        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT g.title, w.full_name FROM groups g INNER JOIN workers w ON (g.curator_id = w.id)";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>();
                row.put("title", rs.getString(1));
                row.put("full_name", rs.getString(2));
                result.add(row);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return result;
        } catch (Exception ev) {
            throw new Exception(ev.getMessage());
        }
    }

    public ArrayList<Object> getBudgets() throws Exception {
        ArrayList<Object> result = new ArrayList<>();

        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT s.full_name, g.title FROM students s LEFT JOIN groups g ON (s.group_id = g.id) WHERE s.is_budget = 1;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>();
                row.put("full_name", rs.getString(1));
                row.put("title", rs.getString(2));
                result.add(row);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return result;
        } catch (Exception ev) {
            throw new Exception(ev.getMessage());
        }
    }

    public ArrayList<Object> getContracts() throws Exception {
        ArrayList<Object> result = new ArrayList<>();

        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT s.full_name, g.title FROM students s LEFT JOIN groups g ON (s.group_id = g.id) WHERE s.is_budget = 0;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>();
                row.put("full_name", rs.getString(1));
                row.put("title", rs.getString(2));
                result.add(row);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return result;
        } catch (Exception ev) {
            throw new Exception(ev.getMessage());
        }
    }

}

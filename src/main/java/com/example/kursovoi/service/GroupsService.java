package com.example.kursovoi.service;

import com.example.kursovoi.SQLConfig;
import com.example.kursovoi.exception.GroupsException;
import com.example.kursovoi.model.Groups;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class GroupsService {

    public ArrayList<Groups> getAllGroups() throws GroupsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM groups";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Groups> groups = new ArrayList<>();

            while (rs.next()) {
                Groups group = new Groups(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getLong(5),
                        rs.getString(6),
                        rs.getLong(7),
                        rs.getLong(8)
                );
                groups.add(group);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return groups;
        } catch (Exception ev) {
            throw new GroupsException("Не удалось получить все группы");
        }
    }

    public Groups getGroup(Long id) throws GroupsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM groups WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Groups group = null;

            while (rs.next()) {
                group = new Groups(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getLong(5),
                        rs.getString(6),
                        rs.getLong(7),
                        rs.getLong(8)
                );
            }

            statement.close();
            rs.close();
            config.closeConnection();

            if (group == null)
                throw new GroupsException("Такая группа не найден");

            return group;
        } catch (Exception ev) {
            throw new GroupsException("Такой группы не существует");
        }
    }

    public void deleteGroup(Long id) throws GroupsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE groups FROM groups WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new GroupsException("Не удалось удалить группу");
        }
    }

    public void editGroup(Long id, Groups newGroup) throws GroupsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement
            (
        "UPDATE groups SET title=?, dep_id=?, praepostor_id=?, sum_year=?, direction_on_study=?, curator_id=?, size=? WHERE id=?"
            );

            statement.setString(1, newGroup.getTitle());
            statement.setLong(2, newGroup.getDep_id());
            statement.setLong(3, newGroup.getPraestor_id());
            statement.setLong(4, newGroup.getSum_year());
            statement.setString(5, newGroup.getDirection_on_study());
            statement.setLong(6, newGroup.getCurator_id());
            statement.setLong(7, newGroup.getSize());
            statement.setLong(8, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new GroupsException("Не удалось изменить группу");
        }
    }

    public void addGroup(Groups group) throws GroupsException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO groups(title, dep_id, praepostor_id, sum_year, direction_on_study, curator_id, size) VALUES(?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, group.getTitle());
            statement.setLong(2, group.getDep_id());
            statement.setLong(3, group.getPraestor_id());
            statement.setLong(4, group.getSum_year());
            statement.setString(5, group.getDirection_on_study());
            statement.setLong(6, group.getCurator_id());
            statement.setLong(7, group.getSize());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new GroupsException("Не удалось добавить группу");
        }
    }

}

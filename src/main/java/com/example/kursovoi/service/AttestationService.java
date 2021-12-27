package com.example.kursovoi.service;

import com.example.kursovoi.SQLConfig;
import com.example.kursovoi.exception.AttestationException;
import com.example.kursovoi.model.Attestation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Service
public class AttestationService {

    public ArrayList<Attestation> getAllAttestations() throws AttestationException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM attestation";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Attestation> attestations = new ArrayList<>();

            while (rs.next()) {
                Attestation attestation = new Attestation(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getLong(5));
                attestations.add(attestation);
            }

            statement.close();
            rs.close();
            config.closeConnection();

            return attestations;
        } catch (Exception ev) {
            throw new AttestationException("Не удалость получить все аттестации");
        }
    }

    public Attestation getAttestation(Long id) throws AttestationException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();

            String query = "SELECT * FROM attestation WHERE id=" + id;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Attestation attestation = null;

            while (rs.next())
                attestation = new Attestation(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getLong(5));

            statement.close();
            rs.close();
            config.closeConnection();

            if (attestation == null)
                throw new AttestationException("Такой аттестации не существует");

            return attestation;
        } catch (Exception ev) {
            throw new AttestationException("Такой аттестации не существует");
        }
    }

    public void deleteAttestation(Long id) throws AttestationException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE attestation FROM attestation WHERE id=?"
            );

            statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception ev) {
            throw new AttestationException("Не удалось удалить аттестацию");
        }
    }

    public void editAttestation(Long id, Attestation newAttestation) throws AttestationException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE attestation SET subject_id=?, group_id=?, student_id=?, value=? WHERE id=?"
            );

            statement.setLong(1, newAttestation.getSubject_id());
            statement.setLong(2, newAttestation.getGroup_id());
            statement.setLong(3, newAttestation.getStudent_id());
            statement.setLong(4, newAttestation.getValue());
            statement.setLong(5, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new AttestationException("Не удалось изменить аттестацию");
        }
    }

    public void addAttestation(Attestation attestation) throws AttestationException {
        try {
            SQLConfig config = new SQLConfig();
            Connection connection = config.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO attestation(subject_id, group_id, student_id, value) VALUES(?, ?, ?, ?)"
            );

            statement.setLong(1, attestation.getSubject_id());
            statement.setLong(2, attestation.getGroup_id());
            statement.setLong(3, attestation.getStudent_id());
            statement.setLong(4, attestation.getValue());
            statement.executeUpdate();

            connection.commit();

            statement.close();
            connection.setAutoCommit(true);
            config.closeConnection();
        } catch (Exception e) {
            throw new AttestationException("Не удалось добавить аттестацию");
        }
    }

}

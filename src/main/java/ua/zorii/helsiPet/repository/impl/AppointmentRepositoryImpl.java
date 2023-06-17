package ua.zorii.helsiPet.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.enums.RequestStatus;
import ua.zorii.helsiPet.repository.AppointmentRepository;

import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public AppointmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAppointmentToDB(Appointment appointment, RequestStatus requestStatus) {
        String queryToInsertDataIntoTable = "INSERT INTO appointment_forms(vet_username, owner_username, owner_pet_name," +
                "vet_available_date, details, status) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(queryToInsertDataIntoTable, appointment.getVetUsername(), appointment.getOwnerUsername(),
                appointment.getOwnerPetName(), appointment.getVetAvailableDate(), appointment.getDetails(), requestStatus.toString());
    }

    @Override
    public List<Appointment> getAppointmentsByOwnerUsername(String ownerUsername) {
        String queryToGetAppointmentByOwnerUsername = "SELECT * FROM appointment_forms WHERE owner_username = ?";
        return jdbcTemplate.query(queryToGetAppointmentByOwnerUsername, new BeanPropertyRowMapper<>(Appointment.class), ownerUsername);
    }

    @Override
    public List<Appointment> getAppointmentsByVetUsername(String vetUsername) {
        String queryToGetAppointmentByVetUsername = "SELECT * FROM appointment_forms WHERE vet_username = ?";
        return jdbcTemplate.query(queryToGetAppointmentByVetUsername, new BeanPropertyRowMapper<>(Appointment.class), vetUsername);
    }

    @Override
    public void updateAppointmentStatus(Integer id, RequestStatus requestStatus) {
        String updateAppointmentStatus = "UPDATE appointment_forms SET status = ? WHERE id = ?";
        jdbcTemplate.update(updateAppointmentStatus, requestStatus.toString(), id);
    }

    @Override
    public void removeAppointment(Integer id) {
        String queryToDeleteAppointment = "DELETE FROM appointment_forms WHERE id = ?";
        jdbcTemplate.update(queryToDeleteAppointment, id);
    }

    @Override
    public void updateAppointment(Integer appointmentId, Integer receiptId) {
        String queryToAddReceipt = "UPDATE appointment_forms SET receipt_id = ? WHERE id = ?";
        jdbcTemplate.update(queryToAddReceipt, receiptId, appointmentId);
    }

    @Override
    public Appointment getAppointmentById(Integer id) {
        String queryToGetAppointmentByID = "SELECT * FROM appointment_forms WHERE id = ?";
        return jdbcTemplate.queryForObject(queryToGetAppointmentByID, new BeanPropertyRowMapper<>(Appointment.class), id);
    }
}

package ua.zorii.helsiPet.repository;

import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.enums.RequestStatus;

import java.util.List;

public interface AppointmentRepository {
    void saveAppointmentToDB(Appointment appointment, RequestStatus requestStatus);
    List<Appointment> getAppointmentsByOwnerUsername(String ownerUsername);
    List<Appointment> getAppointmentsByVetUsername(String vetUsername);
    void updateAppointmentStatus(Integer id, RequestStatus requestStatus);
    void removeAppointment(Integer id);
    void updateAppointment(Integer appointmentId, Integer receiptId);
    Appointment getAppointmentById(Integer id);

}

package ua.zorii.helsiPet.service.impl;

import org.springframework.stereotype.Service;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.enums.RequestStatus;
import ua.zorii.helsiPet.repository.AppointmentRepository;
import ua.zorii.helsiPet.service.AppointmentService;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void saveAppointmentToDB(Appointment appointment, RequestStatus requestStatus) {
        appointmentRepository.saveAppointmentToDB(appointment, requestStatus);
    }

    @Override
    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.getAppointmentById(id);
    }

    @Override
    public List<Appointment> getAppointmentsByOwnerUsername(String ownerUsername) {
        return appointmentRepository.getAppointmentsByOwnerUsername(ownerUsername);
    }

    @Override
    public List<Appointment> getAppointmentsByVetUsername(String vetUsername) {
        return appointmentRepository.getAppointmentsByVetUsername(vetUsername);
    }

    @Override
    public void updateAppointmentStatus(Integer id, RequestStatus requestStatus) {
        appointmentRepository.updateAppointmentStatus(id, requestStatus);
    }

    @Override
    public void removeAppointment(Integer id) {
        appointmentRepository.removeAppointment(id);
    }

    @Override
    public void updateAppointment(Integer appointmentId, Integer receiptId) {
        appointmentRepository.updateAppointment(appointmentId, receiptId);
    }


}

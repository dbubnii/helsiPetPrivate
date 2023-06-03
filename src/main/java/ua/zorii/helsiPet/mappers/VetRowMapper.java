package ua.zorii.helsiPet.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.Vet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VetRowMapper implements RowMapper<Vet> {
    @Override
    public Vet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Vet.builder()
                .usersUsername(rs.getString("users_username"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .phoneNumber(rs.getString("phoneNumber"))
                .email(rs.getString("email"))
                .id(rs.getInt("id"))
                .dateEmployed(rs.getString("dateEmployed"))
                .area(rs.getString("area"))
                .clinicId(rs.getInt("clinicId"))
                .availableAppointmentDate(rs.getString("availableAppointmentDate"))
                .build();
    }
}

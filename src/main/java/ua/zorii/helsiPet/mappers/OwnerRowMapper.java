package ua.zorii.helsiPet.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.zorii.helsiPet.entity.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRowMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Owner.builder()
                .usersUsername(rs.getString("users_username"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .phoneNumber(rs.getString("phoneNumber"))
                .email(rs.getString("email"))
                .id(rs.getInt("id"))
                .build();
    }
}

package ua.zorii.helsiPet.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.enums.PetType;
import ua.zorii.helsiPet.enums.Sex;
import ua.zorii.helsiPet.enums.Size;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetRowMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Animal.builder()
                .ownerUsername(rs.getString("owner_username"))
                .id(rs.getInt("id"))
                .age(rs.getInt("age"))
                .breed(rs.getString("breed"))
                .sex(Sex.valueOf(rs.getString("sex")))
                .size(Size.valueOf(rs.getString("size")))
                .weight(rs.getFloat("weight"))
                .name(rs.getString("name"))
                .sterilized(rs.getBoolean("sterilized"))
                .type(PetType.valueOf(rs.getString("type")))
                .photo(rs.getString("photo"))
                .details(rs.getString("details"))
                .vetId(rs.getInt("vet_id"))
                .uniqueID(rs.getString("uniqueID"))
                .build();
    }
}

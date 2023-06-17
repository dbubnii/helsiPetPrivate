package ua.zorii.helsiPet.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.zorii.helsiPet.dto.HistoryCombinedDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRowMapper implements RowMapper<HistoryCombinedDTO> {
    @Override
    public HistoryCombinedDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return HistoryCombinedDTO.builder()
                .id(rs.getInt("id"))
                .ownerFirstName(rs.getString("ownerFirstName"))
                .ownerLastName(rs.getString("ownerLastName"))
                .ownerEmail(rs.getString("ownerEmail"))
                .vetFirstName(rs.getString("vetFirstName"))
                .vetLastName(rs.getString("vetLastName"))
                .vetEmail(rs.getString("vetEmail"))
                .petName(rs.getString("name"))
                .petBreed(rs.getString("breed"))
                .petAge(rs.getString("age"))
                .petSex(rs.getString("sex"))
                .petSize(rs.getString("size"))
                .petType(rs.getString("type"))
                .petSterilized(rs.getString("sterilized"))
                .petWeight(rs.getString("weight"))
                .petUniqueId(rs.getString("uniqueID"))
                .animalDetails(rs.getString("animalDetails"))
                .appointmentDetails(rs.getString("formDetails"))
                .vetAvailableDate(rs.getString("vet_available_date"))
                .receiptDrugName(rs.getString("drugName"))
                .receiptDrugCount(rs.getString("drugCount"))
                .receiptDateStart(rs.getString("dateStart"))
                .receiptDateEnd(rs.getString("dateEnd"))
                .receiptClinicName(rs.getString("clinicName"))
                .receiptDetails(rs.getString("receiptDetails"))
                .build();
    }
}

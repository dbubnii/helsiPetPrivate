package ua.zorii.helsiPet.repository.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.zorii.helsiPet.entity.Receipt;
import ua.zorii.helsiPet.repository.ReceiptRepository;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReceiptRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createReceipt(Receipt receipt) {
        String queryToAddReceipt = "INSERT INTO receipt(id, vetFullName, ownerFullName, drugName, drugCount, dateStart, " +
                "dateEnd, clinicName, details) VALUES(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(queryToAddReceipt, receipt.getId(), receipt.getVetFullName(), receipt.getOwnerFullName(),
                receipt.getDrugName(), receipt.getDrugCount(), receipt.getDateStart(), receipt.getDateEnd(), receipt.getClinicName(),
                receipt.getDetails());
    }

    @Override
    public Receipt getReceiptById(Integer id) {
        String queryToGetReceiptById = "SELECT * FROM receipt WHERE id = ?";
        return jdbcTemplate.queryForObject(queryToGetReceiptById, new BeanPropertyRowMapper<>(Receipt.class), id);
    }
}

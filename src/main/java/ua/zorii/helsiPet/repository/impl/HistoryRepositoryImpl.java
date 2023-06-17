package ua.zorii.helsiPet.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.zorii.helsiPet.dto.HistoryCombinedDTO;
import ua.zorii.helsiPet.dto.HistoryDTO;
import ua.zorii.helsiPet.mappers.HistoryRowMapper;
import ua.zorii.helsiPet.repository.HistoryRepository;

import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public HistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveHistory(HistoryDTO historyDTO) {
        String query = """
                INSERT INTO sickness_history(vet_username, owner_username, pet_name, appointment_id) VALUES(?,?,?,?)
                """;
        jdbcTemplate.update(query, historyDTO.getVet().getUsersUsername(), historyDTO.getOwner().getUsersUsername(),
                historyDTO.getAnimal().getName(), historyDTO.getAppointment().getId());
    }

    @Override
    public List<HistoryCombinedDTO> getHistory(String username, String petName) {
        String queryToGetComposedData = """
                SELECT sh.id, o.firstName as ownerFirstName, o.lastName as ownerLastName, o.email as ownerEmail,
                        v.firstName as vetFirstName, v.lastName as vetLastName, v.email as vetEmail,
                        a.name, a.breed, a.sex, a.age, a.size, a.weight, a.type, a.details as animalDetails,
                        a.sterilized, a.uniqueID, af.details as formDetails, af.vet_available_date,
                        r.drugName, r.drugCount, r.dateStart, r.dateEnd, r.clinicName, r.details as receiptDetails
                FROM sickness_history sh
                INNER JOIN animals a ON sh.pet_name = a.name
                INNER JOIN vets v ON v.users_username = sh.vet_username
                INNER JOIN owners o ON o.users_username = sh.owner_username
                INNER JOIN appointment_forms af ON af.id = sh.appointment_id
                INNER JOIN receipt r ON r.id = af.receipt_id
                WHERE sh.owner_username = '%s' AND sh.pet_name = '%s' AND af.status = 'ВИКОНАНО'
                """;
        String formatedQuery = String.format(queryToGetComposedData, username, petName);
        return jdbcTemplate.query(formatedQuery, new HistoryRowMapper());
    }
}

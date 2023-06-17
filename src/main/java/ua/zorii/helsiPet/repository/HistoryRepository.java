package ua.zorii.helsiPet.repository;

import ua.zorii.helsiPet.dto.HistoryCombinedDTO;
import ua.zorii.helsiPet.dto.HistoryDTO;

import java.util.List;

public interface HistoryRepository {
    void saveHistory(HistoryDTO historyDTO);
    List<HistoryCombinedDTO> getHistory(String username, String petName);

}

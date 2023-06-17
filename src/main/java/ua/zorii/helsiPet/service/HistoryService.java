package ua.zorii.helsiPet.service;

import ua.zorii.helsiPet.dto.HistoryCombinedDTO;
import ua.zorii.helsiPet.dto.HistoryDTO;

import java.util.List;

public interface HistoryService {
    void saveHistory(HistoryDTO historyDTO);
    List<HistoryCombinedDTO> getHistory(String username, String petName);
}

package ua.zorii.helsiPet.service.impl;

import org.springframework.stereotype.Service;
import ua.zorii.helsiPet.dto.HistoryCombinedDTO;
import ua.zorii.helsiPet.dto.HistoryDTO;
import ua.zorii.helsiPet.repository.HistoryRepository;
import ua.zorii.helsiPet.service.HistoryService;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    @Override
    public void saveHistory(HistoryDTO historyDTO) {
        historyRepository.saveHistory(historyDTO);
    }

    @Override
    public List<HistoryCombinedDTO> getHistory(String username, String petName) {
        return historyRepository.getHistory(username, petName);
    }
}

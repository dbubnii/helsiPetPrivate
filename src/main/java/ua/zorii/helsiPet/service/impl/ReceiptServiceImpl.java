package ua.zorii.helsiPet.service.impl;

import org.springframework.stereotype.Service;
import ua.zorii.helsiPet.entity.Receipt;
import ua.zorii.helsiPet.repository.ReceiptRepository;
import ua.zorii.helsiPet.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public void createReceipt(Receipt receipt) {
        receiptRepository.createReceipt(receipt);
    }

    @Override
    public Receipt getReceiptById(Integer id) {
        return receiptRepository.getReceiptById(id);
    }
}

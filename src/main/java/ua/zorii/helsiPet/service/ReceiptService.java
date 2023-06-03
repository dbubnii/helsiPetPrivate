package ua.zorii.helsiPet.service;

import ua.zorii.helsiPet.entity.Receipt;

public interface ReceiptService {
    void createReceipt(Receipt receipt);
    Receipt getReceiptById(Integer id);
}

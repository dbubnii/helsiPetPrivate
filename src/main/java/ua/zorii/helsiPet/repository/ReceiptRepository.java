package ua.zorii.helsiPet.repository;

import ua.zorii.helsiPet.entity.Receipt;

public interface ReceiptRepository {
    void createReceipt(Receipt receipt);
    Receipt getReceiptById(Integer id);

}

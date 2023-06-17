package ua.zorii.helsiPet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HistoryCombinedDTO {
    private Integer id;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerEmail;
    private String vetFirstName;
    private String vetLastName;
    private String vetEmail;
    private String petName;
    private String petBreed;
    private String petSex;
    private String petAge;
    private String petSize;
    private String petWeight;
    private String petType;
    private String animalDetails;
    private String petSterilized;
    private String petUniqueId;
    private String appointmentDetails;
    private String vetAvailableDate;
    private String receiptDrugName;
    private String receiptDrugCount;
    private String receiptDateStart;
    private String receiptDateEnd;
    private String receiptClinicName;
    private String receiptDetails;
}

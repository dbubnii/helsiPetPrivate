package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Receipt {
    private Integer id;
    private String vetFullName;
    private String ownerFullName;
    private String drugName;
    private String drugCount;
    private String dateStart;
    private String dateEnd;
    private String clinicName;
    private String details;
}

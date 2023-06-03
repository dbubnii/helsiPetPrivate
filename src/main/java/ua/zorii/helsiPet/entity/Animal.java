package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ua.zorii.helsiPet.enums.PetType;
import ua.zorii.helsiPet.enums.Sex;
import ua.zorii.helsiPet.enums.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Animal {
    @Id
    private Integer id;
    private Integer age;
    private Integer vetId;
    private String name;
    private String breed;
    private String photo;
    private String details;
    private String ownerUsername;
    private Boolean sterilized;
    private Sex sex;
    private Size size;
    private Float weight;
    private PetType type;
    private String uniqueID;
}

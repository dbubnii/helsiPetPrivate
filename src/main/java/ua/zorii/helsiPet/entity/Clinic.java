package ua.zorii.helsiPet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Clinic {
    @Id
    private Integer id;
    private String name;
    private String address;
    private String phone;
}

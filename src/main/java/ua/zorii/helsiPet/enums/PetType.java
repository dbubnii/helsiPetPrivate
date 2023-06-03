package ua.zorii.helsiPet.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum PetType {
    КІТ, СОБАКА, ІНШЕ;

    public static Set<String> toStringSet() {
        PetType[] values = PetType.values();
        return Arrays.stream(values).map(Enum::toString).collect(Collectors.toSet());
    }
}

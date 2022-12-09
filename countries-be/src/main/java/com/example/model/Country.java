package com.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Comparable<Country> {
    private String id;
    private String name;
    private String nativeName;
    private int phoneCode;
    private String continent;
    private String capital;
    private String currency;
    private List<String> languages;

    @Override
    public int compareTo(Country o) {
        int phoneCode = ((Country)o).phoneCode;
        //Ascending order
        return this.phoneCode - phoneCode;
    }
    
}
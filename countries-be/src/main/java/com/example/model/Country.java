package com.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String id;
    private String name;
    private String nativeName;
    private int phoneCode;
    private String continent;
    private String capital;
    private String currency;
    private List<String> languages;
    
}
package com.gbsolorzano.Taller2_Diferido.Models.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Password {
    public Password() {
    }
    private String username;
    private String password;
}

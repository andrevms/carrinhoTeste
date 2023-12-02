package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VendaResponseDto {
    private double valor;
    private double frete;
}

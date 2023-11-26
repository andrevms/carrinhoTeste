package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoControllerTest {



    @Test
    void checkout() {
        List<String> mockList = (List<String>) Mockito.mock(List.class);
        mockList.add("MySrt");
        mockList.add("teste");
        mockList.add("tes");
        System.out.println(mockList.get(0));
        System.out.println(mockList.contains("teste"));

    }
}
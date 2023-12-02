package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.controller;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaRequestDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaResponseDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service.CarrinhoService;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service.ItemService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarrinhoControllerTest {

    @InjectMocks
    CarrinhoController sutController;
    @Mock
    CarrinhoService mockService;
    @Mock
    ItemService mockItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckout() {
        List<String> mockList = (List<String>) Mockito.mock(List.class);
        //List<String> mockList = new ArrayList<>();
        //mockList.add("MySrt");

        double valor = 1;
        double frete = 2;

        VendaResponseDto mockVendaDto = new VendaResponseDto(valor,frete);

        when(mockService.checkout(any(List.class))).thenReturn(mockVendaDto);

        VendaRequestDto vendas = new VendaRequestDto();
        vendas.setListId(mockList);

        List<Item> list = new ArrayList<>();

        //CarrinhoController carrinho = sutController.checkout(vendas);
        ResponseEntity<VendaResponseDto> responseEntity = sutController.checkout(vendas);

        //VendaResponseDto carrinho = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

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
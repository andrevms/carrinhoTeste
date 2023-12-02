package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.controller;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaRequestDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaResponseDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/carrinho/")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("checkout")
    public ResponseEntity<VendaResponseDto> checkout(@RequestBody VendaRequestDto listItems){
        try{
            VendaResponseDto vendas = carrinhoService.checkout(listItems.getListId());
            return ResponseEntity.ok(vendas);
        }catch (Exception e ) {

        }
        return ResponseEntity.badRequest().build();
    }


}

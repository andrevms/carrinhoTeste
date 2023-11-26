package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.controller;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/carrinho/")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("checkout")
    public ResponseEntity<VendaDto> checkout(@RequestBody List<Item> listItems){

        try{
            VendaDto vendas = carrinhoService.checkout(listItems);
            return ResponseEntity.ok(carrinhoService.checkout(listItems));
        }catch (Exception e ) {

        }
        return ResponseEntity.badRequest().build();
    }


}

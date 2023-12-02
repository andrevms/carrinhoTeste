package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaResponseDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.TypeItem;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarrinhoService {


    ItemService itemService;
    CarrinhoService(ItemService itemService){
        this.itemService = itemService;
    }

    public VendaResponseDto checkout(List<String> listItens) {
        List<Item> listaDeItens = new ArrayList<>();
        for(String s : listItens){
            listaDeItens.add(itemService.getItemById(s).get());
        }
        BigDecimal price = calculateValue(listaDeItens);
        BigDecimal frete = calculateFrete(listaDeItens);
        return new VendaResponseDto(price.doubleValue(),frete.doubleValue());
    }

    public BigDecimal calculateFrete(List<Item> listItems){
        BigDecimal price = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.FLOOR);
        BigDecimal discont = BigDecimal.valueOf(0.95).setScale(2, RoundingMode.FLOOR);
        BigDecimal weight = calculateWeight(listItems);

        if(listItems.size() > 5){
            price = price.add(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.FLOOR));
        }

        if(weight.doubleValue() <= 2.0) {
            //Frete gratis
        }else if ( weight.doubleValue() <= 10.0){
            price = price.add(weight.multiply(BigDecimal.valueOf(2.00)));
        }else if ( weight.doubleValue() <= 50.0) {
            price = price.add(weight.multiply(BigDecimal.valueOf(4.00)));
        }else {
            price = price.add(weight.multiply(BigDecimal.valueOf(7.00)));
        }

        if(listItems.size() >= 3) {
            if( hasDiscontoFrete(listItems) ) {
                price = price.multiply(discont);
            }
        }

        return price.setScale(2, RoundingMode.FLOOR);
    }

    public BigDecimal calculateWeight(List<Item> listItems) {
        BigDecimal weight = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.FLOOR);
        try{
            for (Item item: listItems
                 ) {
                weight = weight.add(item.getWeight());
            }
        }catch(Exception e){

        }
        return weight;
    }

    public BigDecimal calculateValue(List<Item> listItems){
        BigDecimal price = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.FLOOR);
        try{
            for (Item item: listItems
            ) {
                price = price.add(item.getPrice());
            }
        }catch(Exception e){

        }

        price = hasDiscontoValue(price);

        return price;
    }

    public BigDecimal hasDiscontoValue(BigDecimal valueCarrinho) {
        BigDecimal discontMoreThan1000 = BigDecimal.valueOf(0.80).setScale(2, RoundingMode.FLOOR);
        BigDecimal discontMoreThan500 = BigDecimal.valueOf(0.90).setScale(2, RoundingMode.FLOOR);

        if( valueCarrinho.doubleValue() > 1000.0) {
            return BigDecimal.valueOf(valueCarrinho.doubleValue() * discontMoreThan1000.doubleValue()).setScale(2, RoundingMode.FLOOR);
        }else if (valueCarrinho.doubleValue() > 500.0) {
            return BigDecimal.valueOf(valueCarrinho.doubleValue() * discontMoreThan500.doubleValue()).setScale(2, RoundingMode.FLOOR);
        }

        return valueCarrinho;
    }
    public boolean hasDiscontoFrete(List<Item> listItem) {
        Map<TypeItem, Integer> mapCount = new HashMap<>();

        for (Item item: listItem
             ) {
            mapCount.compute(item.getType(), (key, count) -> (count == null) ? 1 : count + 1);
                    if(mapCount.get(item.getType()) > 2 ){
                        return true;
                    }
        }
        return false;
    }
}

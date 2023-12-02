package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.TypeItem;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarrinhoService {

    @Autowired
    ItemService itemService;

    public VendaDto checkout(List<String> listItens) {
        List<Item> listaDeItens = null;
        for(String s : listItens){
            listaDeItens.add(itemService.getItemById(s).get());
        }
        double price = calculateValue(listaDeItens);
        double frete = calculateFrete(listaDeItens);
        return new VendaDto(price,frete);
    }

    public double calculateFrete(List<Item> listItems){
        double price = 0.0;
        double discont = 0.95;
        double weight = calculateWeight(listItems);

        if(listItems.size() < 5){
            price +=10.0;
        }

        if(weight <= 2.0) {
            //Frete gratis
        }else if ( weight <= 10.0){
            price += weight * 2;
        }else if ( weight <= 50.0) {
            price += weight * 4;
        }else {
            price += weight * 7;
        }

        if(listItems.size() >= 3) {
            if( hasDiscontoFrete(listItems) ) {
                price = price * discont;
            }
        }

        return price;
    }

    public double calculateWeight(List<Item> listItems) {
        double weight = 0.0;
        try{
            for (Item item: listItems
                 ) {
                weight += item.getWeight();
            }
        }catch(Exception e){

        }
        return weight;
    }

    public double calculateValue(List<Item> listItems){
        double price = 0.0;
        try{
            for (Item item: listItems
            ) {
                price += item.getPrice();
            }
        }catch(Exception e){

        }

        price = hasDiscontoValue(price);

        return price;
    }

    public double hasDiscontoValue(double valueCarrinho) {
        double discontMoreThan1000 = 0.80;
        double discontMoreThan500 = 0.90;

        if( valueCarrinho > 1000.0) {
            return valueCarrinho * discontMoreThan1000;
        }else if (valueCarrinho >500.0) {
            return valueCarrinho * discontMoreThan500;
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

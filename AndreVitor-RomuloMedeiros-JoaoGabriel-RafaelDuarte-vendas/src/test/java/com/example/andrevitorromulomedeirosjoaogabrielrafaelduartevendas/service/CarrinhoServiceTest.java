package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaResponseDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.TypeItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoServiceTest {

    @InjectMocks
    CarrinhoService carrinhoService;
    @Mock
    ItemService itemService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.carrinhoService = new CarrinhoService(itemService);
    }
    @ParameterizedTest
    @CsvSource({
            "valor1,valor2,valor3,valor4"
    })
    void checkout(String id1, String id2, String id3, String id4) {
        List<String> listaDeIds = new ArrayList<>();
        listaDeIds.add(id1);
        listaDeIds.add(id2);
        listaDeIds.add(id3);
        listaDeIds.add(id4);
        assertEquals(new VendaResponseDto(2,3),carrinhoService.checkout(listaDeIds));
    }

    @Test
    void calculateFrete() {
    }

    @Test
    void calculatePeso() {
    }

    @ParameterizedTest
    @CsvSource({
            "1001.00,23.00,42.00,852.80",
            "501.00,65.00,32.00,538.20",
            "500.00,0.0,0.0, 500.00",
            "200.00,65.00,78.00, 343.00"
    })
    void calculateValue(double valor1, double valor2, double valor3, BigDecimal valor) {
        List<Item> mockListaItens = new ArrayList<>();// tem que retornar true porque tem 3 itens iguais
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        item1.setPrice(valor1);
        item2.setPrice(valor2);
        item3.setPrice(valor3);

        mockListaItens.add(item1);
        mockListaItens.add(item2);
        mockListaItens.add(item3);

        BigDecimal resultado = new BigDecimal(carrinhoService.calculateValue(mockListaItens));

        assertEquals(valor, resultado.setScale(2, RoundingMode.FLOOR));
    }

    @ParameterizedTest
    @CsvSource({
            "1001.00,800.80",
            "501.00, 450.90",
            "500.00, 500.00",
            "200.00, 200.00"
    })
    void hasDiscontoValue(double valor, BigDecimal desconto) {
        BigDecimal resultado = new BigDecimal(carrinhoService.hasDiscontoValue(valor));

        assertEquals(desconto, resultado.setScale(2, RoundingMode.FLOOR));
    }

    @Test
    void hasDiscontoFrete3ItensDoMesmoTipo() {
        List<Item> mockListaItens1 = new ArrayList<>();// tem que retornar true porque tem 3 itens iguais
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        item1.setType(TypeItem.CASA);
        item2.setType(TypeItem.CASA);
        item3.setType(TypeItem.CASA);

        mockListaItens1.add(item1);
        mockListaItens1.add(item2);
        mockListaItens1.add(item3);

        assertEquals(true, carrinhoService.hasDiscontoFrete(mockListaItens1));
    }
    @Test
    void hasDiscontoFrete2ItensDoMesmoTipo() {
        List<Item> mockListaItens1 = new ArrayList<>();// tem que retornar false porque tem menos de 3 itens iguais
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        item1.setType(TypeItem.CASA);
        item2.setType(TypeItem.CASA);
        item3.setType(TypeItem.COZINHA);

        mockListaItens1.add(item1);
        mockListaItens1.add(item2);
        mockListaItens1.add(item3);

        assertEquals(false, carrinhoService.hasDiscontoFrete(mockListaItens1));
    }
    @Test
    void hasDiscontoFrete0ItensDoMesmoTipo() {
        List<Item> mockListaItens1 = new ArrayList<>();// tem que retornar false porque tem menos de 3 itens iguais
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        item1.setType(TypeItem.CASA);
        item2.setType(TypeItem.ROUPA);
        item3.setType(TypeItem.COZINHA);

        mockListaItens1.add(item1);
        mockListaItens1.add(item2);
        mockListaItens1.add(item3);

        assertEquals(false, carrinhoService.hasDiscontoFrete(mockListaItens1));
    }
}
package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.service;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.dto.VendaResponseDto;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.TypeItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        Item item1 = new Item();

        when(itemService.getItemById(any())).thenReturn(Optional.of(item1));

        List<String> listaDeIds = new ArrayList<>();
        listaDeIds.add(id1);
        listaDeIds.add(id2);
        listaDeIds.add(id3);
        listaDeIds.add(id4);

        assertEquals(new VendaResponseDto(0.0,0.0),carrinhoService.checkout(listaDeIds));
    }

    @ParameterizedTest
    @MethodSource("provideCalculateFrete")
    void calculateFrete(List<Item> mockList, BigDecimal expected) {
        assertEquals(expected, carrinhoService.calculateFrete(mockList));
    }

    private static Stream<Arguments> provideCalculateFrete() {
        Item[] listSizeMoreThen5PesoLessThen2WithMoreThen2ItensWithSameType = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("0.00"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("0.00"), TypeItem.COZINHA),
                new Item("4","item4","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.CASA),
                new Item("5","item5","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.CASA),
                new Item("6","item6","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.CASA),
        };
        List<Item> mockListlistSizeMoreThen5PesoLessThen2WithMoreThen2ItensWithSameType  = new ArrayList<>(List.of(listSizeMoreThen5PesoLessThen2WithMoreThen2ItensWithSameType ));

        Item[] listSizeMoreThen5PesoLessThen2WithMoreThen0ItensWithSameType = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("0.00"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("0.00"), TypeItem.COZINHA),
                new Item("4","item4","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.ELETRONICO),
                new Item("5","item5","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.LIVRO),
                new Item("6","item6","description", new BigDecimal("1001.00"), new BigDecimal("0.00"), TypeItem.LIVRO),
        };
        List<Item> mockListlistSizeMoreThen5PesoLessThen2WithMoreThen0ItensWithSameType  = new ArrayList<>(List.of(listSizeMoreThen5PesoLessThen2WithMoreThen0ItensWithSameType ));


        Item[] listSizeMoreThen5PesoMoreThen2LessThen10WithMoreThen2ItensWithSameType = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("1.00"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("1.00"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("1.00"), TypeItem.COZINHA),
                new Item("4","item4","description", new BigDecimal("1001.00"), new BigDecimal("1.00"), TypeItem.ELETRONICO),
                new Item("5","item5","description", new BigDecimal("1001.00"), new BigDecimal("1.00"), TypeItem.ROUPA),
                new Item("6","item6","description", new BigDecimal("1001.00"), new BigDecimal("1.00"), TypeItem.CASA),
        };
        List<Item> mockListlistSizeMoreThen5PesoMoreThen2LessThen10WithMoreThen0ItensWithSameType  = new ArrayList<>(List.of(listSizeMoreThen5PesoMoreThen2LessThen10WithMoreThen2ItensWithSameType ));

        Item[] listSizeMoreThen5PesoMoreThen10LessThen50WithMoreThen2ItensWithSameType = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("10.00"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("10.00"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("10.00"), TypeItem.COZINHA),
                new Item("4","item4","description", new BigDecimal("1001.00"), new BigDecimal("10.00"), TypeItem.ELETRONICO),
                new Item("5","item5","description", new BigDecimal("1001.00"), new BigDecimal("1.00"), TypeItem.ROUPA),
                new Item("6","item6","description", new BigDecimal("1001.00"), new BigDecimal("1.00"), TypeItem.CASA),
        };
        List<Item> mockListlistSizeMoreThen5PesoMoreThen10LessThen50WithMoreThen2ItensWithSameType  = new ArrayList<>(List.of(listSizeMoreThen5PesoMoreThen10LessThen50WithMoreThen2ItensWithSameType ));

        Item[] listSizeMoreThen5PesoMoreThen50And2ItensWithSameType = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("10.00"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("10.00"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("10.00"), TypeItem.COZINHA),
                new Item("4","item4","description", new BigDecimal("1001.00"), new BigDecimal("10.00"), TypeItem.ELETRONICO),
                new Item("5","item5","description", new BigDecimal("1001.00"), new BigDecimal("10.00"), TypeItem.ROUPA),
                new Item("6","item6","description", new BigDecimal("1001.00"), new BigDecimal("10.00"), TypeItem.CASA),
        };
        List<Item> mockListlistSizeMoreThen5And0ItensWithSameType  = new ArrayList<>(List.of(listSizeMoreThen5PesoMoreThen50And2ItensWithSameType));


        return Stream.of(
                Arguments.of(mockListlistSizeMoreThen5PesoLessThen2WithMoreThen2ItensWithSameType, new BigDecimal("9.50")),
                Arguments.of(mockListlistSizeMoreThen5PesoLessThen2WithMoreThen0ItensWithSameType, new BigDecimal("10.00")),
                Arguments.of(mockListlistSizeMoreThen5PesoMoreThen2LessThen10WithMoreThen0ItensWithSameType, new BigDecimal("22.00")),
                Arguments.of(mockListlistSizeMoreThen5PesoMoreThen10LessThen50WithMoreThen2ItensWithSameType, new BigDecimal("178.00")),
                Arguments.of(mockListlistSizeMoreThen5And0ItensWithSameType, new BigDecimal("430.00"))

        );
    }

    @ParameterizedTest
    @MethodSource("provideWeight")
    void calculateWeight(List<Item> mockList, BigDecimal expected) {
        assertEquals(expected, carrinhoService.calculateWeight(mockList));
    }

    private static Stream<Arguments> provideWeight() {
        Item[] calculateWeightMoreThen1000 = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("1001.00"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("23.00"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("42.00"), TypeItem.COZINHA),
        };
        List<Item> mockListCalculateWeightMoreThen1000  = new ArrayList<>(List.of(calculateWeightMoreThen1000 ));

        Item[] calculateWeightMoreThen500 = {
                new Item("4","item4","description", new BigDecimal("501.00"), new BigDecimal("501.00"), TypeItem.CASA),
                new Item("5","item5","description", new BigDecimal("65.00"), new BigDecimal("65.00"), TypeItem.ELETRONICO),
                new Item("6","item6","description", new BigDecimal("32.00"), new BigDecimal("32.00"), TypeItem.CASA),
        };
        List<Item> mockListCalculateWeightMoreThen500 = new ArrayList<>(List.of(calculateWeightMoreThen500));

        Item[] calculateWeightEqual500 = {
                new Item("7","item7","description", new BigDecimal("500.00"), new BigDecimal("500.00"), TypeItem.CASA),
                new Item("8","item8","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("9","item9","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
        };
        List<Item> mockListCalculateWeightEqual500 = new ArrayList<>(List.of(calculateWeightEqual500));

        Item[] calculateValueLessThen500 = {
                new Item("7","item7","description", new BigDecimal("200.00"), new BigDecimal("200.00"), TypeItem.CASA),
                new Item("8","item8","description", new BigDecimal("65.00"), new BigDecimal("65.00"), TypeItem.CASA),
                new Item("9","item9","description", new BigDecimal("78.00"), new BigDecimal("78.00"), TypeItem.CASA),
        };
        List<Item> mockListCalculateWeightLessThen500 = new ArrayList<>(List.of(calculateValueLessThen500));


        return Stream.of(
                Arguments.of(mockListCalculateWeightMoreThen1000, new BigDecimal("1066.00")),
                Arguments.of(mockListCalculateWeightMoreThen500, new BigDecimal("598.00")),
                Arguments.of(mockListCalculateWeightEqual500, new BigDecimal("500.00")),
                Arguments.of(mockListCalculateWeightLessThen500, new BigDecimal("343.00"))
        );
    }


    @ParameterizedTest
    @MethodSource("provideCalculateValue")
    void calculateValue(List<Item> mockList, BigDecimal expected) {

        assertEquals(expected, carrinhoService.calculateValue(mockList));
    }

    private static Stream<Arguments> provideCalculateValue() {
        Item[] calculateValueMoreThen1000 = {
                new Item("1","item1","description", new BigDecimal("1001.00"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("23.00"), new BigDecimal("0.0"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("42.00"), new BigDecimal("0.0"), TypeItem.COZINHA),
        };
        List<Item> mockListCalculateValueMoreThen1000  = new ArrayList<>(List.of(calculateValueMoreThen1000 ));

        Item[] calculateValueMoreThen500 = {
                new Item("4","item4","description", new BigDecimal("501.00"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("5","item5","description", new BigDecimal("65.00"), new BigDecimal("0.0"), TypeItem.ELETRONICO),
                new Item("6","item6","description", new BigDecimal("32.00"), new BigDecimal("0.0"), TypeItem.CASA),
        };
        List<Item> mockListCalculateValueMoreThen500 = new ArrayList<>(List.of(calculateValueMoreThen500));

        Item[] calculateValueEqual500 = {
                new Item("7","item7","description", new BigDecimal("500.00"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("8","item8","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("9","item9","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
        };
        List<Item> mockListCalculateValueEqual500 = new ArrayList<>(List.of(calculateValueEqual500));

        Item[] calculateValueLessThen500 = {
                new Item("7","item7","description", new BigDecimal("200.00"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("8","item8","description", new BigDecimal("65.00"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("9","item9","description", new BigDecimal("78.00"), new BigDecimal("0.0"), TypeItem.CASA),
        };
        List<Item> mockListCalculateValueLessThen500 = new ArrayList<>(List.of(calculateValueLessThen500));


        return Stream.of(
                Arguments.of(mockListCalculateValueMoreThen1000, new BigDecimal("852.80")),
                Arguments.of(mockListCalculateValueMoreThen500, new BigDecimal("538.20")),
                Arguments.of(mockListCalculateValueEqual500, new BigDecimal("500.00")),
                Arguments.of(mockListCalculateValueLessThen500, new BigDecimal("343.00"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideHasDiscontoValue")
    void hasDiscontoValue(BigDecimal valueCarrinho, BigDecimal expected) {
        BigDecimal resultado = carrinhoService.hasDiscontoValue(valueCarrinho);

        assertEquals(expected, resultado);
    }
    private static Stream<Arguments> provideHasDiscontoValue() {

        return Stream.of(
                Arguments.of(new BigDecimal("1001.00"),new BigDecimal("800.80")),
                Arguments.of(new BigDecimal("501.00"),new BigDecimal("450.90")),
                Arguments.of(new BigDecimal("500.00"),new BigDecimal("500.00")),
                Arguments.of(new BigDecimal("200.00"),new BigDecimal("200.00"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideHasDiscontoFrete")
    void hasDiscontoFrete(List<Item> mockListaItens, boolean expected) {
        assertEquals(expected, carrinhoService.hasDiscontoFrete(mockListaItens));
    }
    private static Stream<Arguments> provideHasDiscontoFrete() {
        Item[] hasDiscontoFrete0ItensDoMesmoTipo = {
                new Item("1","item1","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("2","item2","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.ROUPA),
                new Item("3","item3","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.COZINHA),
        };
        List<Item> mockListhasDiscontoFrete0ItensDoMesmoTipo = new ArrayList<>(List.of(hasDiscontoFrete0ItensDoMesmoTipo));

        Item[] hasDiscontoFrete2ItensDoMesmoTipo = {
                new Item("4","item4","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("5","item5","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.ELETRONICO),
                new Item("6","item6","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
        };
        List<Item> mockListhasDiscontoFrete2ItensDoMesmoTipo = new ArrayList<>(List.of(hasDiscontoFrete2ItensDoMesmoTipo));

        Item[] hasDiscontoFrete3ItensDoMesmoTipo = {
                new Item("7","item7","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("8","item8","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
                new Item("9","item9","description", new BigDecimal("0.0"), new BigDecimal("0.0"), TypeItem.CASA),
        };
        List<Item> mockListhasDiscontoFrete3ItensDoMesmoTipo = new ArrayList<>(List.of(hasDiscontoFrete3ItensDoMesmoTipo));

        return Stream.of(
                Arguments.of(mockListhasDiscontoFrete0ItensDoMesmoTipo, false),
                Arguments.of(mockListhasDiscontoFrete2ItensDoMesmoTipo, false),
                Arguments.of(mockListhasDiscontoFrete3ItensDoMesmoTipo, true)
        );
    }
}
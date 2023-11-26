package com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.repository;

import com.example.andrevitorromulomedeirosjoaogabrielrafaelduartevendas.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}

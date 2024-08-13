package com.tenfar.ddd.domain.table.repository;

import com.tenfar.ddd.domain.table.entities.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    List<Table> findAll();

    Optional<Table> findById(String id);

    Table save(Table table);

    void deleteById(String id);
}

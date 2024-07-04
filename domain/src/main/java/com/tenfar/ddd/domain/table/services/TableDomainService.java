package com.tenfar.ddd.domain.table.services;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.domain.table.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableDomainService {

    private final TableRepository tableRepository;

    @Autowired
    public TableDomainService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Table createTable(Table table) {
        return tableRepository.save(table);
    }

    public Optional<Table> getTableById(String id) {
        return tableRepository.findById(id);
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public Table updateTable(Table table) {
        return tableRepository.save(table);
    }

    public void deleteTableById(String id) {
        tableRepository.deleteById(id);
    }
}

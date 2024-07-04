
package com.tenfar.ddd.application.services;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.domain.table.services.TableDomainService;
import com.tenfar.ddd.infrastructure.assembler.TableAssembler;
import com.tenfar.ddd.dto.TableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableApplicationService {

    private final TableDomainService tableDomainService;
    private final TableAssembler tableAssembler;

    @Autowired
    public TableApplicationService(TableDomainService tableDomainService, TableAssembler tableAssembler) {
        this.tableDomainService = tableDomainService;
        this.tableAssembler = tableAssembler;
    }

    public TableDTO createTable(TableDTO tableDTO) {
        Table table = tableAssembler.toEntity(tableDTO);
        Table createdTable = tableDomainService.createTable(table);
        return tableAssembler.toDTO(createdTable);
    }

    public Optional<TableDTO> getTableById(String id) {
        Optional<Table> table = tableDomainService.getTableById(id);
        return table.map(tableAssembler::toDTO);
    }

    public List<TableDTO> getAllTables() {
        List<Table> tables = tableDomainService.getAllTables();
        return tables.stream().map(tableAssembler::toDTO).collect(Collectors.toList());
    }

    public TableDTO updateTable(TableDTO tableDTO) {
        Table table = tableAssembler.toEntity(tableDTO);
        Table updatedTable = tableDomainService.updateTable(table);
        return tableAssembler.toDTO(updatedTable);
    }

    public void deleteTable(String id) {
        tableDomainService.deleteTableById(id);
    }
}
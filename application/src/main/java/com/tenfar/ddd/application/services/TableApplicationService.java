package com.tenfar.ddd.application.services;

import com.tenfar.ddd.application.events.publishers.TableEventPublisher;
import com.tenfar.ddd.common.utils.UuidGenerator;
import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.domain.table.events.TableCreatedEvent;
import com.tenfar.ddd.domain.table.services.TableDomainService;
import com.tenfar.ddd.dto.TableDTO;
import com.tenfar.ddd.infrastructure.assembler.TableAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(TableApplicationService.class);
    private final TableDomainService tableDomainService;
    private final TableAssembler tableAssembler;
    private final UuidGenerator uuidGenerator;
    private final TableEventPublisher tableEventPublisher;

    @Autowired
    public TableApplicationService(TableDomainService tableDomainService, TableAssembler tableAssembler, UuidGenerator uuidGenerator, TableEventPublisher tableEventPublisher) {
        this.tableDomainService = tableDomainService;
        this.tableAssembler = tableAssembler;
        this.uuidGenerator = uuidGenerator;
        this.tableEventPublisher = tableEventPublisher;
    }

    public TableDTO createTable(TableDTO tableDTO) {
        logger.info("Creating table with DTO: {}", tableDTO);
        Table table = tableAssembler.toEntity(tableDTO);
        Table createdTable = tableDomainService.createTable(table);
        logger.info("Table created with ID: {}", createdTable.getId());
        // 發布 TableCreatedEvent
        TableCreatedEvent event = new TableCreatedEvent(this, uuidGenerator, createdTable);
        tableEventPublisher.publishTableCreatedEvent(event);

        return tableAssembler.toDTO(createdTable);
    }

    public Optional<TableDTO> getTableById(String id) {
        /* Retrieve the table as an Optional */
        return tableDomainService.getTableById(id).map(tableAssembler::toDTO);
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
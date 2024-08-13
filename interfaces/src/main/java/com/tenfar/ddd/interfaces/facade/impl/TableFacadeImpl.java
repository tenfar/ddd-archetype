package com.tenfar.ddd.interfaces.facade.impl;

import com.tenfar.ddd.application.services.TableApplicationService;
import com.tenfar.ddd.dto.TableDTO;
import com.tenfar.ddd.interfaces.facade.TableFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TableFacadeImpl implements TableFacade {

    private final TableApplicationService tableService;

    public TableFacadeImpl(TableApplicationService tableService) {
        this.tableService = tableService;
    }

    @Override
    public List<TableDTO> getAllTables() {
        return tableService.getAllTables();
    }

    @Override
    public TableDTO getTableById(String id) {
        /* Check for null to avoid NPE **/
        Optional<TableDTO> table = tableService.getTableById(id);
        if (table.isEmpty()) {
            /* Handle the case where the table is not found **/
            throw new IllegalArgumentException("Table not found for id: " + id);
        }
        return table.get();
    }

    @Override
    public TableDTO createTable(TableDTO tableDTO) {
        tableService.createTable(tableDTO);
        return tableDTO;
    }

    @Override
    public TableDTO updateTable(TableDTO tableDTO) {
        tableService.updateTable(tableDTO);
        return tableDTO;
    }

    @Override
    public void deleteTable(String id) {
        tableService.deleteTable(id);
    }

    @Override
    public List<TableDTO> getTablesByPage(int pageNum, int pageSize) {
        return null;
    }
}
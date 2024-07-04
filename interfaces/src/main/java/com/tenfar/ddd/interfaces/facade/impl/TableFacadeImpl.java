package com.tenfar.ddd.interfaces.facade.impl;

import com.tenfar.ddd.application.services.TableApplicationService;
import com.tenfar.ddd.interfaces.facade.TableFacade;
import com.tenfar.ddd.dto.TableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableFacadeImpl implements TableFacade {

    @Autowired
    private TableApplicationService tableService;

    @Override
    public List<TableDTO> getAllTables() {
        return tableService.getAllTables();
    }

    @Override
    public TableDTO getTableById(String id) {
        return tableService.getTableById(id).get();
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
}

package com.tenfar.ddd.interfaces.facade;

import com.tenfar.ddd.dto.TableDTO;

import java.util.List;

public interface TableFacade {
    List<TableDTO> getAllTables();
    TableDTO getTableById(String id);
    TableDTO createTable(TableDTO tableDTO);
    TableDTO updateTable(TableDTO tableDTO);
    void deleteTable(String id);
}

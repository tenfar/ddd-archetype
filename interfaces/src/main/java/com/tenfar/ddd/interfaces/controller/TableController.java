package com.tenfar.ddd.interfaces.controller;

import com.tenfar.ddd.interfaces.facade.TableFacade;
import com.tenfar.ddd.dto.TableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableFacade tableFacade;

    @Autowired
    public TableController(TableFacade tableFacade) {
        this.tableFacade = tableFacade;
    }

    @PostMapping
    public ResponseEntity<TableDTO> createTable(@RequestBody TableDTO table) {
        TableDTO createdTable = tableFacade.createTable(table);
        return ResponseEntity.ok(createdTable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDTO> getTableById(@PathVariable String id) {
        TableDTO table = tableFacade.getTableById(id);
        return ResponseEntity.ok(table);
    }

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAllTables() {
        List<TableDTO> tables = tableFacade.getAllTables();
        return ResponseEntity.ok(tables);
    }

    @PutMapping
    public ResponseEntity<TableDTO> updateTable(@RequestBody TableDTO table) {
        TableDTO updatedTable = tableFacade.updateTable(table);
        return ResponseEntity.ok(updatedTable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableById(@PathVariable String id) {
        tableFacade.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}


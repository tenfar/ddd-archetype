package com.tenfar.ddd.interfaces.controller;

import com.tenfar.ddd.dto.TableDTO;
import com.tenfar.ddd.interfaces.facade.TableFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@Api(tags = "Table Controller", description = "操作相关表格")
public class TableController {

    private final TableFacade tableFacade;

    @Autowired
    public TableController(TableFacade tableFacade) {
        this.tableFacade = tableFacade;
    }

    @PostMapping
    @ApiOperation(value = "创建新表格", response = TableDTO.class)
    public TableDTO createTable(@RequestBody @ApiParam(name = "table", value = "要创建的表格对象", required = true) TableDTO table) {
        return tableFacade.createTable(table);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取表格", response = TableDTO.class)
    public TableDTO getTableById(@PathVariable @ApiParam(name = "id", value = "表格ID", required = true) String id) {
        return tableFacade.getTableById(id);
    }

    @GetMapping
    @ApiOperation(value = "获取所有表格", response = List.class)
    public List<TableDTO> getAllTables() {
        return tableFacade.getAllTables();
    }

    @PutMapping
    @ApiOperation(value = "更新现有表格", response = TableDTO.class)
    public TableDTO updateTable(@RequestBody @ApiParam(name = "table", value = "要更新的表格对象", required = true) TableDTO table) {
        return tableFacade.updateTable(table);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除表格")
    public void deleteTableById(@PathVariable @ApiParam(name = "id", value = "表格ID", required = true) String id) {
        tableFacade.deleteTable(id);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询表格", response = List.class)
    public List<TableDTO> getTablesByPage(
            @RequestParam @ApiParam(name = "pageNum", value = "页码", required = true) int pageNum,
            @RequestParam @ApiParam(name = "pageSize", value = "每页大小", required = true) int pageSize) {
        return tableFacade.getTablesByPage(pageNum, pageSize);
    }
}


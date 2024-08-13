package com.tenfar.ddd.interfaces.facade;

import com.tenfar.ddd.dto.TableDTO;

import java.util.List;

public interface TableFacade {
    /**
     * 获取所有表格
     *
     * @return 所有表格的列表
     */
    List<TableDTO> getAllTables();

    /**
     * 根据ID获取表格
     *
     * @param id 表格ID
     * @return 对应的表格对象
     */
    TableDTO getTableById(String id);

    /**
     * 创建新表格
     *
     * @param tableDTO 要创建的表格对象
     * @return 创建后的表格对象
     */
    TableDTO createTable(TableDTO tableDTO);

    /**
     * 更新现有表格
     *
     * @param tableDTO 要更新的表格对象
     * @return 更新后的表格对象
     */
    TableDTO updateTable(TableDTO tableDTO);

    /**
     * 根据ID删除表格
     *
     * @param id 表格ID
     */
    void deleteTable(String id);

    /**
     * 分页查询表格
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 分页后的表格列表
     */
    List<TableDTO> getTablesByPage(int pageNum, int pageSize);
}

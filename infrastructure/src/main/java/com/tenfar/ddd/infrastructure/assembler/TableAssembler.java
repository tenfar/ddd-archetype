package com.tenfar.ddd.infrastructure.assembler;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.dto.TableDTO;
import com.tenfar.ddd.infrastructure.mapper.TableMapper;
import org.springframework.stereotype.Component;

@Component
public class TableAssembler {

    public TableDTO toDTO(Table table) {
        return TableMapper.INSTANCE.toDTO(table);
    }

    public Table toEntity(TableDTO tableDTO) {
        return TableMapper.INSTANCE.toEntity(tableDTO);
    }
}

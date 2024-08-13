package com.tenfar.ddd.infrastructure.mapper;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.domain.table.vo.TableId;
import com.tenfar.ddd.dto.TableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "status", target = "status")
    TableDTO toDTO(Table table);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "capacity", target = "capacity")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "reservations", target = "reservations")
    Table toEntity(TableDTO tableDTO);

    // 添加自定义映射方法
    default TableId mapId(String id) {
        return new TableId(id); // 假设TableId有一个接受String的构造函数
    }
}
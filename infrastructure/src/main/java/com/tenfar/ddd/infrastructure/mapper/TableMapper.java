package com.tenfar.ddd.infrastructure.mapper;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.dto.TableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "capacity", target = "capacity")
    TableDTO toDTO(Table table);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "reservations", source = "reservations")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "capacity", target = "capacity")
    Table toEntity(TableDTO tableDTO);
}

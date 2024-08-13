package com.tenfar.ddd.infrastructure.persistence.mysql;

import com.tenfar.ddd.domain.table.repository.TableRepository;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TableMybatisRepository extends TableRepository {

}
package com.tenfar.ddd.infrastructure.persistence.mongodb;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.domain.table.repository.TableRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
/*
  MongoTableRepository接口继承自MongoRepository和TableRepository，用于操作MongoDB中的Table实体。
  使用方法：
  1. 通过继承MongoRepository，可以使用Spring Data MongoDB提供的方法进行CRUD操作。
  2. 通过继承TableRepository，可以自定义查询方法，满足特定业务需求。
  示例：
  - 保存Table实体：mongoTableRepository.save(table);
  - 根据ID查找Table实体：mongoTableRepository.findById(id);
  - 删除Table实体：mongoTableRepository.delete(table);
 */
public interface MongoTableRepository extends MongoRepository<Table, String>, TableRepository {

}
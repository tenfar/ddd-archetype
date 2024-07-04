package com.tenfar.ddd.infrastructure.persistence.mongodb;

import com.tenfar.ddd.domain.table.entities.Table;
import com.tenfar.ddd.domain.table.repository.TableRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoTableRepository extends MongoRepository<Table, String>, TableRepository {

}
package com.tenfar.ddd.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableDTOTest {

    private TableDTO tableDTO;

    @BeforeEach
    public void setUp() {
        tableDTO = new TableDTO();
    }

    @AfterEach
    public void tearDown() {
        tableDTO = null;
    }

    @Test
    public void testSetAndGetId() {
        String id = UUID.randomUUID().toString();
        tableDTO.setId(id);
        assertEquals(id, tableDTO.getId());
    }

    @Test
    public void capacity() {
        int capacity = 5;
        tableDTO.setCapacity(capacity);
        assertEquals(capacity, tableDTO.getCapacity());
    }
}
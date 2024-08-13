package com.tenfar.ddd.application.services;

import com.tenfar.ddd.application.events.publishers.TableEventPublisher;
import com.tenfar.ddd.common.utils.UuidGenerator;
import com.tenfar.ddd.domain.table.services.TableDomainService;
import com.tenfar.ddd.dto.TableDTO;
import com.tenfar.ddd.infrastructure.assembler.TableAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class TableApplicationServiceTest {

    @Mock
    private TableDomainService tableDomainService;

    @Mock
    private TableAssembler tableAssembler;

    @Mock
    private UuidGenerator uuidGenerator;

    @Mock
    private TableEventPublisher tableEventPublisher;

    @InjectMocks
    private TableApplicationService tableApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTable() {
        TableDTO tableDTO = new TableDTO();
        TableDTO result = tableApplicationService.createTable(tableDTO);
        verify(tableEventPublisher).publishTableCreatedEvent(any());
        assertEquals(tableDTO, result);
    }

    @Test
    void testGetTableById() {
        String id = "1";

        TableDTO tableDTO = new TableDTO();
        Optional<TableDTO> result = tableApplicationService.getTableById(id);
        assertEquals(tableDTO, result.get());
    }

    @Test
    void testGetTableByIdNotFound() {
        String id = "1";
        when(tableDomainService.getTableById(id)).thenReturn(Optional.empty());

        Optional<TableDTO> result = tableApplicationService.getTableById(id);

        assertNull(result);
    }

    @Test
    void testGetAllTables() {

        TableDTO tableDTO1 = new TableDTO();
        TableDTO tableDTO2 = new TableDTO();
        List<TableDTO> result = tableApplicationService.getAllTables();

        assertEquals(Arrays.asList(tableDTO1, tableDTO2), result);
    }

    @Test
    void testUpdateTable() {
        TableDTO tableDTO = new TableDTO();
        TableDTO result = tableApplicationService.updateTable(tableDTO);
        assertEquals(tableDTO, result);
    }

    @Test
    void testDeleteTable() {
        String id = "1";

        tableApplicationService.deleteTable(id);

        verify(tableDomainService).deleteTableById(id);
    }
}
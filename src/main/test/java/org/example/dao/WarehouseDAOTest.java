package org.example.dao;

import org.example.model.Warehouse;
import org.example.services.WarehouseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class WarehouseDAOTest {

    @BeforeEach
    void setUp() {
        System.out.println("Run before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Run after");
    }

   /* @Test
    void create() {
        Warehouse warehouse = new Warehouse.Builder().
                withProduct("Product").
                withAmount(50).
                withExpertId(1).build();
        assertEquals(true, WarehouseService.service().create(warehouse));
    }*/

    @Test
    void findAll() {
        assertEquals(8, WarehouseService.service().findAll().size());
    }

    @Test
    void findModelById() {
        Warehouse warehouse = WarehouseService.service().findModelById(7);
        assertEquals("Mango", warehouse.getProduct());
        assertEquals(45, warehouse.getAmount());
        assertEquals(7, warehouse.getExpertId());
    }

    @Test
    void updateAmount() {
        assertEquals(true, WarehouseService.service().updateAmount(10, "Product"));
    }

    @Test
    void deleteById() {
        assertEquals(true, WarehouseService.service().deleteById(10));
    }

    @Test
    void findByName() {
        Warehouse warehouse = WarehouseService.service().findByName("Cucumber");
        assertEquals("Cucumber", warehouse.getProduct());
        Warehouse warehouse1 = WarehouseService.service().findByName("Car");
        assertEquals(null, warehouse1);
    }

}
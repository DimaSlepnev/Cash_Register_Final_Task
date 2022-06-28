package org.example.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WarehouseTest {

    @Test
    public void WarehouseTest(){
        Warehouse.Builder warehouseBuilder = spy(Warehouse.Builder.class);
        warehouseBuilder.withId(0);
        verify(warehouseBuilder,times(1)).withId(0);

        warehouseBuilder.withProduct("Product");
        verify(warehouseBuilder,times(1)).withProduct("Product");

        warehouseBuilder.withAmount(2);
        verify(warehouseBuilder,times(1)).withAmount(2);

        warehouseBuilder.withExpertId(11);
        verify(warehouseBuilder,times(1)).withExpertId(11);


        Warehouse warehouse = spy(Warehouse.class);
        warehouse.setId(1);
        verify(warehouse,times(1)).setId(1);
        assertEquals(1,warehouse.getId());
        verify(warehouse,times(1)).getId();

        warehouse.setProduct("product");
        verify(warehouse,times(1)).setProduct("product");
        assertEquals("product",warehouse.getProduct());
        verify(warehouse,times(1)).getProduct();

        warehouse.setAmount(4);
        verify(warehouse,times(1)).setAmount(4);
        assertEquals(4,warehouse.getAmount());
        verify(warehouse,times(1)).getAmount();

        warehouse.setExpertId(20);
        verify(warehouse,times(1)).setExpertId(20);
        assertEquals(20,warehouse.getExpertId());
        verify(warehouse,times(1)).getExpertId();
    }
}
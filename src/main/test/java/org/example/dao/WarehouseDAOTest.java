package org.example.dao;

import org.example.model.Warehouse;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WarehouseDAOTest {

    @Test
    public void WarehouseDAOTest(){
        Warehouse warehouse = mock(Warehouse.class);
        WarehouseDAO warehouseDAO = mock(WarehouseDAO.class);
        when(warehouseDAO.findAll()).thenReturn(mock(List.class));
        when(warehouseDAO.findModelById(0)).thenReturn(mock(Warehouse.class));
        when(warehouseDAO.update(warehouse)).thenReturn(false);
        when(warehouseDAO.deleteById(0)).thenReturn(false);
        when(warehouseDAO.findByName("name")).thenReturn(mock(Warehouse.class));
        when(warehouseDAO.isExist("productName")).thenReturn(mock(Warehouse.class));
        when(warehouseDAO.updateAmount(10,"productName")).thenReturn(true);
        when(warehouseDAO.sortingBy("sorting",1,5)).thenReturn(mock(List.class));

        warehouseDAO = spy(WarehouseDAO.class);
        warehouseDAO.create(warehouse);
        verify(warehouseDAO,times(1)).create(warehouse);
        warehouseDAO.findAll();
        verify(warehouseDAO,times(1)).findAll();
        warehouseDAO.findModelById(0);
        verify(warehouseDAO,times(1)).findModelById(0);
        warehouseDAO.update(warehouse);
        verify(warehouseDAO,times(1)).update(warehouse);
        warehouseDAO.deleteById(0);
        verify(warehouseDAO,times(1)).deleteById(0);
        warehouseDAO.delete(warehouse);
        verify(warehouseDAO,times(1)).delete(warehouse);
        warehouseDAO.findByName("name");
        verify(warehouseDAO,times(1)).findByName("name");
        warehouseDAO.isExist("productName");
        verify(warehouseDAO,times(1)).isExist("productName");
        warehouseDAO.updateAmount(0,"potato");
        verify(warehouseDAO,times(1)).updateAmount(0,"potato");
        warehouseDAO.sortingBy("sorting",1,5);
        verify(warehouseDAO,times(1)).sortingBy("sorting",1,5);
    }

}
package org.example.dao;

import org.example.model.Bill;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillDAOTest {

    @Test
    public void BillDAOTest() {
        Bill bill = mock(Bill.class);
        BillDAO billDAO = mock(BillDAO.class);
        when(billDAO.findAll()).thenReturn(mock(List.class));
        when(billDAO.findModelById(1)).thenReturn(mock(Bill.class));
        when(billDAO.update(bill)).thenReturn(true);
        when(billDAO.deleteById(bill.getId())).thenReturn(false);
        when(billDAO.numberOfRows()).thenReturn(1);
        when(billDAO.updateAmountAndPriceById(1, 1, 0)).thenReturn(true);
        when(billDAO.findALlWithPagination(1, 5)).thenReturn(mock(List.class));
        when(billDAO.updateConfirmationById(0)).thenReturn(false);
        when(billDAO.sortingBy("sorting",1,5)).thenReturn(mock(List.class));

        billDAO = spy(BillDAO.class);
        billDAO.findAll();
        verify(billDAO, times(1)).findAll();
        billDAO.create(bill);
        verify(billDAO, times(1)).create(bill);
        billDAO.delete(bill);
        verify(billDAO, times(1)).delete(bill);
        billDAO.update(bill);
        verify(billDAO, times(1)).update(bill);
        billDAO.updateConfirmationById(0);
        verify(billDAO, times(1)).updateConfirmationById(0);
        billDAO.findModelById(0);
        verify(billDAO, times(1)).findModelById(0);
        billDAO.deleteById(0);
        verify(billDAO, times(1)).deleteById(0);
        billDAO.updateAmountAndPriceById(10,20,0);
        verify(billDAO,times(1)).updateAmountAndPriceById(10,20,0);
        billDAO.sortingBy("sort",1,5);
        verify(billDAO,times(1)).sortingBy("sort",1,5);
    }
}
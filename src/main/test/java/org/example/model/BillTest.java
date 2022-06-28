package org.example.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BillTest {

    @Test
    public void BillTest() {

        Bill.Builder billBuilder = spy(Bill.Builder.class);
        billBuilder.withId(0);
        verify(billBuilder,times(1)).withId(0);

        billBuilder.withProductId(0);
        verify(billBuilder,times(1)).withProductId(0);

        billBuilder.withBody("Body");
        verify(billBuilder,times(1)).withBody("Body");

        billBuilder.withAmount(50);
        verify(billBuilder,times(1)).withAmount(50);

        billBuilder.withPrice(5);
        verify(billBuilder,times(1)).withPrice(5);

        billBuilder.withConfirmation(false);
        verify(billBuilder,times(1)).withConfirmation(false);

        Bill bill = spy(Bill.class);
        bill.setId(1);
        verify(bill,times(1)).setId(1);
        assertEquals(1,bill.getId());
        verify(bill,times(1)).getId();

        bill.setProductId(1);
        verify(bill,times(1)).setProductId(1);
        assertEquals(1,bill.getProductId());
        verify(bill,times(1)).getProductId();

        bill.setBody("body");
        verify(bill,times(1)).setBody("body");
        assertEquals("body",bill.getBody());
        verify(bill,times(1)).getBody();

        bill.setAmount(100);
        verify(bill,times(1)).setAmount(100);
        assertEquals(100,bill.getAmount());
        verify(bill,times(1)).getAmount();

        bill.setPrice(10);
        verify(bill,times(1)).setPrice(10);
        assertEquals(10,bill.getPrice());
        verify(bill,times(1)).getPrice();

        bill.setConfirmation(false);
        verify(bill,times(1)).setConfirmation(false);
        assertFalse(bill.isConfirmation());
        verify(bill,times(1)).isConfirmation();
    }
}
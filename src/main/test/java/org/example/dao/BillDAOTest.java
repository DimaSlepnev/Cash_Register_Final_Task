package org.example.dao;

import org.example.model.Bill;
import org.example.services.BillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillDAOTest {

    @BeforeEach
    void setUp() {
        System.out.println("Run before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Run after");
    }

  /*  @Test
  *//*  void create() {
        Bill bill = new Bill.Builder().
                withProductId(7).
                withBody("Bill").
                withAmount(5).
                withPrice(100).
                withConfirmation(false).build();
        assertEquals(true, BillService.service().create(bill));
    }*/

    @Test
    void findAll() {
        assertEquals(3, BillService.service().findAll().size());
    }

    @Test
    void findModelById() {
        Bill bill = BillService.service().findModelById(12);
        assertEquals(7, bill.getProductId());
        assertEquals("Bill", bill.getBody());
        assertEquals(5, bill.getAmount());
        assertEquals(100, bill.getPrice());
    }

    @Test
    void updateAmountAndPriceById() {
        assertEquals(true, BillService.service().updateAmountAndPriceById(10,150,12));
    }

    @Test
    void updateConfirmationById() {
        assertEquals(true,BillService.service().updateConfirmationById(12));
    }

    @Test
    void deleteById() {
        assertEquals(true, BillService.service().deleteById(12));
    }
}
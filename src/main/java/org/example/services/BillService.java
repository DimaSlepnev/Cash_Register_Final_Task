package org.example.services;

import org.example.dao.BillDAO;

public class BillService {
    /*
    Provides connection with BillDAO
     */
    public static BillDAO service(){
        BillDAO billDAO = new BillDAO();
        return  billDAO;
    }
}

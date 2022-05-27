package org.example.services;

import org.example.dao.BillDAO;

public class BillService {
    public static BillDAO service(){
        BillDAO billDAO = new BillDAO();
        return  billDAO;
    }
}

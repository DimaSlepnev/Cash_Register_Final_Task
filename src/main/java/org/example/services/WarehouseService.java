package org.example.services;

import org.example.dao.WarehouseDAO;

public class WarehouseService {
    /*
   Provides connection with WarehouseDAO
    */
    public static WarehouseDAO service(){
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        return warehouseDAO;
    }
}

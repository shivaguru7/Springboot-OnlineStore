package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

//import com.example.demo.entities.OrderDetails;
//import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
//import com.example.demo.model.ProductDTO;
import com.example.demo.model.StoreDTO;
public interface StoreInterface
{
StoreDTO creatStore(Store store);
StoreDTO updateStore(int id,Store store);
String deleteStore(int id);
List<StoreDTO> getAllStore();
StoreDTO getStoreById(int id);

}

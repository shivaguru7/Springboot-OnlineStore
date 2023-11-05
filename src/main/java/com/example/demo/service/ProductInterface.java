package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.OrderDetails;
import com.example.demo.entities.Product;
import com.example.demo.model.ProductDTO;


public interface ProductInterface {
	ProductDTO createProduct(Product product);
	ProductDTO updateProduct(int id,Product product);
	String deleteProduct(int id);
	List<ProductDTO> getAllProduct();
	ProductDTO getProductById(int id);
	String assignStoreToProduct(int storeId,int pid);
	String orderProduct(int productId,OrderDetails orderDetails);
	String cancelProduct(Date purchaseDate,int productId,OrderDetails orderDetails);
}

package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.OrderDetails;
import com.example.demo.entities.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.ProductInterface;
import com.example.demo.util.Converter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private Converter converter;
	@Autowired
	private ProductInterface productinterface; 
	
	@PostMapping("/createProduct")
	ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO)
	{
		final Product product=converter.convertToProductEntity(productDTO);
		return new ResponseEntity<ProductDTO>(productinterface.createProduct(product),HttpStatus.CREATED);
	}
	@PutMapping("/updateProduct/{id}")
	ResponseEntity<ProductDTO> updateProduct(@Valid @PathVariable("id") int pid,@RequestBody ProductDTO productDTO)
	{
		final Product product =converter.convertToProductEntity(productDTO);
		return new ResponseEntity<ProductDTO>(productinterface.updateProduct(pid, product),HttpStatus.OK);
	}
	
	@GetMapping("/getAllproducts")
	List<ProductDTO> getAllproducts()
	{
	return productinterface.getAllProduct();
	}
	
	@GetMapping("/getProductById/{id}")
	ProductDTO getProductById(@PathVariable int id)
	{
		return productinterface.getProductById(id);
	}
	
	@PostMapping("assign/{sid}/{pid}")
	String assignStoreToProduct(@PathVariable int sid,@PathVariable int pid)
	{
		return productinterface.assignStoreToProduct(sid, pid);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	String deleteProduct(@PathVariable int id)
	{
	return productinterface.deleteProduct(id);	
	}
	
	@PostMapping("orderProduct/{pid}")
	 String orderProduct(@PathVariable("pid") int productId,@RequestBody OrderDetails orderDetails)
	 {
		 return productinterface.orderProduct(productId, orderDetails);
	 }
}

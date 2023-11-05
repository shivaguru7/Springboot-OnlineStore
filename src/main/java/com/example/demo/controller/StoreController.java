package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.model.ProductDTO;
import com.example.demo.model.StoreDTO;
import com.example.demo.service.StoreInterface;
import com.example.demo.util.Converter;

@RestController
public class StoreController {

	@Autowired(required=false)
	private StoreInterface storeInterface;
	
	@Autowired
	private Converter converter;
	
	@PostMapping("/createStore")
	ResponseEntity<StoreDTO> createStore(@Valid @RequestBody StoreDTO storeDTO)
	{
		final Store store =converter.convertToStoreEntity(storeDTO);
		return new ResponseEntity<StoreDTO>(storeInterface.creatStore(store),HttpStatus.CREATED);
	}
	@PutMapping("/updateStore/{id}")
	ResponseEntity<StoreDTO> updateStore(@Valid @PathVariable("id") int sid,@RequestBody StoreDTO storeDTO)
	{
		final Store store =converter.convertToStoreEntity(storeDTO);
		return new ResponseEntity<StoreDTO>(storeInterface.updateStore(sid, store),HttpStatus.OK);
	}
	
}


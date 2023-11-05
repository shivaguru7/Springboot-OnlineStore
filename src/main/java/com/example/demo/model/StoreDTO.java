package com.example.demo.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.entities.Product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreDTO 
{
	@NotNull
	private int storeId;
	@NotNull
	@Size(min = 2,max = 30,
	message = "Store name should have min 2 characters and max 30 characters")
	private String storeName;
	@NotNull
	@Size(min = 2,max = 30,
	message = "Store address should have min 2 characters and max 30 characters")
	private String address;
	List<Product> products;
}

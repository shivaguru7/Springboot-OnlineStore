package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.entities.Store;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductDTO
{
	@NotNull
	private int productid;
	
	@NotNull
	@Size(min = 2,max = 30,
	message = "product name should have min 2 characters and max 30 characters")
	private String productName;
	
	@NotNull
	private double productprice;
	
	@NotNull
	private int stock;

	private Date purchaseDate; 
	private Store store;
}

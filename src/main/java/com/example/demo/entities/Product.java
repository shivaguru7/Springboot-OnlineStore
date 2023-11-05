package com.example.demo.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product 
{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int productid;
	
	@Column(name = "name",length = 30)
private String productName;
	
	@Column(name = "price")
private double productprice;
	
	private Date purchaseDate; 
	
private int stock;

@ManyToOne(cascade = CascadeType.PERSIST)
@JsonIgnoreProperties("store")
Store store;


}

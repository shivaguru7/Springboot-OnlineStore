package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Store
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	

	@Column(name = "name",length = 30)
private String storename;
	@Column(length = 30)
private String address;

	@OneToMany(mappedBy = "store",cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("products")
List <Product> products;
}

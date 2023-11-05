package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductDTO;
import com.example.demo.model.StoreDTO;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.service.StoreInterface;
import com.example.demo.util.Converter;

public class StoreServiceImpl implements StoreInterface
{
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
private Converter converter;
	
	// create store operation 
	
	@Override
	public StoreDTO creatStore(Store store) {
		
		return converter.convertToStoreDTO(storeRepository.save(store));
	}

	// Update store operation 
	
	@Override
	public StoreDTO updateStore(int id, Store store) {
		
		Optional<Store> stor=storeRepository.findById(id);
		Store s;
		if(stor.isPresent())
		{
			 s=stor.get();
			 s.setStorename(store.getStorename());
				s.setAddress(store.getAddress());
				storeRepository.save(s);
		}
		else {
			throw new ResourceNotFoundException("Store","id",id);
		}
		return converter.convertToStoreDTO(s);

	}
	// Delete store operation 
	
	@Override
	public String deleteStore(int id) {
		Optional<Store> stor=storeRepository.findById(id);
		Store s;
		if(stor.isPresent())
		{
			 s=stor.get();
			 storeRepository.delete(s);
		}
		else {
			throw new ResourceNotFoundException("Store","id",id);
		}
		return "Store deleted success";
	}
	
// To read store operation using findall() Method
	@Override
	public List<StoreDTO> getAllStore() {
		// TODO Auto-generated method stub
List<Store> store=storeRepository.findAll();
		
		List<StoreDTO> storeDTOS=new ArrayList<>();
		
		for(Store s:store)
		{
			storeDTOS.add(converter.convertToStoreDTO(s));
		}
			return storeDTOS;
		}
	
	// To get store by using findbyid() Method 

	@Override
	public StoreDTO getStoreById(int id) 
	{
		// TODO Auto-generated method stub
		Optional<Store> stor=storeRepository.findById(id);
		Store s;
		if(stor.isPresent())
		{
			 s=stor.get();
		}
		else {
			throw new ResourceNotFoundException("Store","id",id);
		}
		return converter.convertToStoreDTO(s);
	}

}

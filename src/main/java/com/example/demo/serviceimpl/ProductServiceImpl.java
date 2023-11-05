package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.OrderDetails;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductDTO;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.StoreRepository;
//import com.example.demo.service.OrderDetails;
import com.example.demo.service.ProductInterface;
import com.example.demo.util.Converter;
@Service
public class ProductServiceImpl  implements ProductInterface{

	@Autowired
	private	ProductRepository productRepository;
		@Autowired
	private Converter converter;

		@Autowired
		private StoreRepository storeRepository;
	   @Autowired
		private OrderRepository orderRepository;
		
		@Override
		public ProductDTO createProduct(Product product) {
		//Product p=productRepository.save(product);
			return converter.convertToProductDTO(productRepository.save(product));
		}


		@Override
		public ProductDTO updateProduct(int id, Product product)throws ResourceNotFoundException
		{
			//Product p=productRepository.findById(id).get();
			Optional<Product> prod=productRepository.findById(id);
			Product p;
			if(prod.isPresent())
			{
				 	p=prod.get();
				 	p.setProductName(product.getProductName());
					p.setProductprice(product.getProductprice());
					p.setStock(product.getStock());
					productRepository.save(p);
			}
			else {
				throw new ResourceNotFoundException("Product","id",id);
			}
			return converter.convertToProductDTO(p);
}


		@Override
		public String deleteProduct(int id) throws ResourceNotFoundException
		{
			//Product p=productRepository.findById(id).get();
			Optional<Product> prod=productRepository.findById(id);
			Product p;
			if(prod.isPresent())
			{
				 p=prod.get();
				 productRepository.delete(p);
					
			}
			else {
				throw new ResourceNotFoundException("Product","id",id);
			}
			return "Product delete successfully";
		}


		@Override
		public List<ProductDTO> getAllProduct() {
		List<Product> products=productRepository.findAll();
		
		List<ProductDTO> productDTOS=new ArrayList<>();
		
		for(Product p:products)
		{
		productDTOS.add(converter.convertToProductDTO(p));
		}
			return productDTOS;
		}


		@Override
		public ProductDTO getProductById(int id) throws ResourceNotFoundException {
			Optional<Product> prod=productRepository.findById(id);
			Product p;
			if(prod.isPresent())
			{
				 p=prod.get();
			}
			else {
				throw new ResourceNotFoundException("Product","id",id);
			}
			return converter.convertToProductDTO(p);
		}


		@Override
		public String assignStoreToProduct(int storeId, int pid) throws ResourceNotFoundException
		{
			//Store store=storeRepository.findById(storeId).get();
			//Product p=productRepository.findById(pid).get();
			
			Optional<Store> store=storeRepository.findById(storeId);
			Optional<Product> prod=productRepository.findById(pid);
			Store s;
			Product p;
			
			if(prod.isPresent())
			{
				s=store.get();
				 p=prod.get();
				// p.setStore(store);
				 p.setStore(s);
					List<Product> products=new ArrayList<>();
					products.add(p);
					s.setProducts(products);
					//store.setProducts(products);
					productRepository.save(p);
			}
			else {
				throw new ResourceNotFoundException("Product","id",pid);
			}
			return "Store Id added successfully";
		}


		@Override
		public String orderProduct(int productId,OrderDetails orderDetails) throws ResourceNotFoundException
		{
			//Product p=productRepository.findById(productId).get();
			Optional<Product> prod=productRepository.findById(productId);
			double totalAmount=0.0;
			String message;
			Product p;
			if(prod.isPresent())
			{
				 p=prod.get();
				 totalAmount=(orderDetails.getQuantity()*p.getProductprice());
					orderDetails.setTotalAmount(totalAmount);
					List<Product> products=new ArrayList<>();
					products.add(p);
					orderDetails.setProducts(products);
					p.setStock(p.getStock()-orderDetails.getQuantity()); //30-2=28
					productRepository.save(p);
					orderRepository.save(orderDetails);
					message="Your order has been placed successfully!!"
							+ "Your total Amount is:"+totalAmount+"Your order will deliver within 7days";
			}
			/*if(prod!=null)
			{
				 totalAmount=(orderDetails.getQuantity()*prod.getProductprice());
				orderDetails.setTotalAmount(totalAmount);
				List<Product> products=new ArrayList<>();
				products.add(p);
				orderDetails.setProducts(products);
				p.setStock(p.getStock()-orderDetails.getQuantity()); //30-2=28
				productRepository.save(p);
				orderRepository.save(orderDetails);
				message="Your order has been placed successfully!!"
						+ "Your total Amount is:"+totalAmount+"Your order will deliver within 7days";
			}*/
			else {
				throw new ResourceNotFoundException("Product","id",productId);
			}
			
			return message;
			
			
		}

		// Cancellation Operation completed 
		
		@Override
		public String cancelProduct(Date purchaseDate,int productId,OrderDetails orderDetails)throws ResourceNotFoundException
		{
			// TODO Auto-generated method stub
			Optional<Product> prod=productRepository.findById(productId);
			String info;
			Product pro;
			if(prod.isPresent())
			{
			
				pro=prod.get();
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DAY_OF_MONTH, -7);
	        Date checkdays = calendar.getTime();
	        if (purchaseDate.before(checkdays)) {
	        	info="Cancellation is not possible after 7 days";
	        } else {
	            
	        	pro.setStock(pro.getStock()+orderDetails.getQuantity());
	        	productRepository.save(pro);
	        	info="After product cancellation.. stock value added 1";
		}
			}
			else {
				throw new ResourceNotFoundException("Product","id",productId);
			}
				
	        return info;
}
}
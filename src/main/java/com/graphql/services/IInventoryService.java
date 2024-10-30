package com.graphql.services;

import java.util.List;

import com.graphql.dtos.Product;

public interface IInventoryService {

	List<Product> viewProducts();
	
	List<Product> getAllProductByCategory(String category);
	
    void deleteProductById(Integer id);
    
    void craeteProduct(Product product);
    
    Product updateProduct(Product product , Integer id);
}

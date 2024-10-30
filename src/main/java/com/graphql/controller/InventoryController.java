package com.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.dtos.Product;
import com.graphql.services.IInventoryService;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return inventoryService.viewProducts();
	}

	@GetMapping("/getAllProductByCategory/{category}")
	public List<Product> getMethodName(@PathVariable String category) {
		return inventoryService.getAllProductByCategory(category);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProductById(@PathVariable Integer id) {
		inventoryService.deleteProductById(id);
		return "Product Deleted Successfully...";
		
	}
}

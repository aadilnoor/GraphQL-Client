package com.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.dtos.Product;
import com.graphql.services.IInventoryService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;

	@PostMapping("/createProduct")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		inventoryService.craeteProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

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

	@PutMapping("/upadteProduct/{id}")
	public ResponseEntity<Product> upadteProductPriceAndStock(@RequestBody Product product, @PathVariable Integer id) {
		inventoryService.updateProduct(product, id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}

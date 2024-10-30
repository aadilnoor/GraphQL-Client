package com.graphql.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import com.graphql.dtos.Product; 

@Service
public class InventoryService implements IInventoryService {

	@Autowired
	private HttpGraphQlClient graphQlClient;
	
	@Override
	public List<Product> viewProducts() {
		String getAllProductsQuery = "query GetAllProducts {\r\n"
				+ "    getAllProducts {\r\n"
				+ "        name\r\n"
				+ "        category\r\n"
				+ "        price\r\n"
				+ "    }\r\n"
				+ "}";
		return graphQlClient.document(getAllProductsQuery)
				.retrieve("getAllProducts")
				.toEntityList(Product.class)
				.block();
		
	}

	@Override
	public List<Product> getAllProductByCategory(String category) {
		String getAllProductByCategory = String.format("query GetProductByCategory {\r\n"
				+ "    getProductByCategory(category: \"%s\") {\r\n"
				+ "        name\r\n"
				+ "        price\r\n"
				+ "        id\r\n"
				+ "    }\r\n"
				+ "}",category);
		return  graphQlClient.document(getAllProductByCategory)
				.retrieve("getProductByCategory")
				.toEntityList(Product.class)
				.block();
	}

	@Override
	public void deleteProductById(Integer id) {
	    String deleteProduct = String.format("mutation DeleteProductById {\r\n"
	            + "    deleteProductById(id: %d)\r\n"
	            + "}", id);
	    
		try {
			String response = graphQlClient.document(deleteProduct).retrieve("deleteProductById").toEntity(String.class)
					.block();

			System.out.println("Product deleted successfully: " + response);

		} catch (FieldAccessException e) {
			System.out.println("GraphQL FieldAccessException occurred: " + e.getMessage());

			Throwable cause = e.getCause();
			if (cause != null) {
				System.out.println("Root cause: " + cause.getMessage());
			}

			throw new RuntimeException("Error occurred while deleting product. Check server logs for details.");

		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
			throw new RuntimeException("An unexpected error occurred while deleting product.");
		}
	}

}

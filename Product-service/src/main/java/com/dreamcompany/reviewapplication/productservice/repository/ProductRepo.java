package com.dreamcompany.reviewapplication.productservice.repository;



import com.dreamcompany.reviewapplication.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product,Integer> {
    Product findByProductName(String productName);
}

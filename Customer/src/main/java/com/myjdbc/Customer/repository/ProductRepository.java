package com.myjdbc.Customer.repository;

import com.myjdbc.Customer.model.CustomerInfo;
import com.myjdbc.Customer.model.ProductInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductInfo, Integer> {

    List<ProductInfo> findAll();
    Optional<ProductInfo> findById(Integer pid);

}

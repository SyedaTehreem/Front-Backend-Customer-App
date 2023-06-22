package com.myjdbc.Customer.service;

import com.myjdbc.Customer.model.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductInfo> getAllProducts();

    ProductInfo addProduct(ProductInfo productInfo);
    ProductInfo updateProduct(int productId, ProductInfo productInfo);

    boolean deleteProduct(int productId);
    ProductInfo getProductById(Integer pid);
}

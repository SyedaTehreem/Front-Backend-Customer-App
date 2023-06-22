package com.myjdbc.Customer.customerServiceImpl;

import com.myjdbc.Customer.model.ProductInfo;

import com.myjdbc.Customer.repository.ProductRepository;
import com.myjdbc.Customer.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public List<ProductInfo> getAllProducts() {
        Iterable<ProductInfo> productsIterable = productRepository.findAll();
        List<ProductInfo> productsList = StreamSupport.stream(productsIterable.spliterator(), false)
                .collect(Collectors.toList());
        return productsList;

    }
    // FOR Getting a single record from customer table

    public ProductInfo getProductById(Integer pid) {
        Optional<ProductInfo> optionalCustomer = productRepository.findById(pid);
        return optionalCustomer.orElse(null);
    }
//////Saving info
    public ProductInfo addProduct(ProductInfo productInfo) {
        // Implement the logic to add a new product
        // Save the ProductInfo object to the database
        ProductInfo createdProduct = productRepository.save(productInfo);
        return createdProduct;

    }
/////update
    @Override
    public ProductInfo updateProduct(int productId, ProductInfo updatedProductInfo) {

        Optional<ProductInfo> oldProduct = productRepository.findById(productId);
        if (oldProduct.isPresent()) {
            ProductInfo existingProduct = oldProduct.get();
            /* existingCustomer.setId(updatedCustomerInfo.getId());*/
            existingProduct.setName(updatedProductInfo.getName());
            existingProduct.setDescription(updatedProductInfo.getDescription());
            existingProduct.setPrice(updatedProductInfo.getPrice());


            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }

    }

    public boolean deleteProduct(int productId) {
        // Implement the logic to delete the customer
        // Return true if the deletion is successful, false otherwise
        try {
            productRepository.deleteById(productId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}

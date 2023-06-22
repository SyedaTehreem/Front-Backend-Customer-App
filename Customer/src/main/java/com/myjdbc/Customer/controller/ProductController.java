package com.myjdbc.Customer.controller;

import com.myjdbc.Customer.model.CustomerInfo;
import com.myjdbc.Customer.model.ProductInfo;
import com.myjdbc.Customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")


public class ProductController {
    @Autowired
    ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/get")
    public ResponseEntity<List<ProductInfo>> getAllProducts(){
        List<ProductInfo> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    /////for inserting record

    @PostMapping("/add")
    public ResponseEntity<ProductInfo> addProduct(@RequestBody @Valid ProductInfo productInfo) {
        ProductInfo createdProduct = productService.addProduct(productInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }


    ///////for updating
    @PutMapping("/{productId}")
    public ResponseEntity<ProductInfo> updateProduct(
            @PathVariable int productId,
            @RequestBody @Valid ProductInfo updatedProductInfo
    ) {
        ProductInfo updatedProduct = productService.updateProduct(productId, updatedProductInfo);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /////Delete

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        boolean deleted = productService.deleteProduct(productId);

        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    //// Add Product Form submission end point:
    @PostMapping("/submitForm")
    public ResponseEntity<ProductInfo> submitForm(@RequestBody @Valid ProductInfo productInfo) {
        ProductInfo createdProduct = productService.addProduct(productInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
}

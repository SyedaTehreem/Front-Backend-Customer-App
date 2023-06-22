package com.myjdbc.Customer.repoImpl;

import com.myjdbc.Customer.model.ProductInfo;
import com.myjdbc.Customer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }
///// insert method
    @Override
    public <S extends ProductInfo> S save(S products) {
        String sql = "INSERT INTO product_info(name,description,price) VALUES (?,?,?)";
        jdbcTemplate.update(sql,products.getName(),products.getDescription(),products.getPrice());

        return products;
    }
    //// Save All Logic -----Insert method

    public <S extends ProductInfo> Iterable<S> saveAll(Iterable<S> products) {
        List<S> savedProducts = new ArrayList<>();
        for(S product: products){
            String name = product.getName();
            String description = product.getDescription();
            Double price =product.getPrice();
            ///Implement save logic here
            String sql = "INSERT INTO product_info (name, description, price) Values(?, ?, ?)";
            jdbcTemplate.update(sql,name,description,price);
            savedProducts.add(product);
        }

        return savedProducts;
    }

 /////find by id

    public Optional<ProductInfo> findById(Integer pid) {
        String sql = "SELECT * FROM product_info WHERE pid = ?";
        List<ProductInfo> products = jdbcTemplate.query(sql,new Object[]{pid}, (rs, rowNum) ->{
            ProductInfo product = new ProductInfo();
            product.setPid(rs.getInt("pid"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            return product;
        } );
return products.stream().findFirst();
    }

/////Exist by id
    public boolean existsById(Integer pid) {
        ////Implementing exist by id logic here:
        String sql = "SELECT COUNT FROM product_info WHERE pid = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, pid);
        return count > 0;
    }

/////Find all method
    public List<ProductInfo> findAll() {
        String sql = "SELECT * FROM product_info";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductInfo product = new ProductInfo();
            product.setPid(rs.getInt("pid"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            return product;

        });
    }

 ////find all by id
    public Iterable<ProductInfo> findAllById(Iterable<Integer> pids) {
        ///implementing find all by id logic here:
        List<ProductInfo> products = new ArrayList<>();
        String sql = "SELECT * FROM product_info WHERE pid IN (:pids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("pids", pids);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, parameters);
        for(Map<String, Object> row : rows){

            ProductInfo product = new ProductInfo();
            product.setPid((Integer) row.get("pid"));
            product.setName((String) row.get("name"));
            product.setDescription((String) row.get("description"));
            product.setPrice((Double) row.get("price"));
            products.add(product);

        }
        return products;
    }
////// Count Method

    public long count() {
        // Implement count logic here
        String sql = "SELECT COUNT(*) FROM product_info";
        return jdbcTemplate.queryForObject(sql, Long.class);

    }

  //////Delete by Id
    public void deleteById(Integer pid) {
        // Implement delete by ID logic here
        String sql = "DELETE FROM product_info WHERE pid = ?";
        jdbcTemplate.update(sql, pid);

    }

///Delete (T) method
    public void delete(ProductInfo product) {
        // Implement delete logic here
        String sql = "DELETE FROM product_info WHERE pid = ?";
        jdbcTemplate.update(sql, product.getPid());
    }

  ///  //deleteAllById(Iterable<? extends ID>)

    public void deleteAllById(Iterable<? extends Integer> pids) {
        // Implement delete all by ID logic here
        List<Integer> idList = new ArrayList<>();
        pids.forEach(idList::add);
        String sql = "DELETE FROM product_info WHERE pid IN (:pids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("pids", idList);
        jdbcTemplate.update(sql, parameters);

    }

    ////deleteAll(Iterable<? extends T>)
    public void deleteAll(Iterable<? extends ProductInfo> products) {
        // Implement delete all logic here
        List<Integer> pids = new ArrayList<>();
        for (ProductInfo product : products) {
            pids.add(product.getPid());
        }
        String sql = "DELETE FROM product_info WHERE pid IN (:pids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("pids", pids);
        jdbcTemplate.update(sql, parameters);

    }

 ///Delete AlL
    public void deleteAll() {
        // Implement delete all logic here
        String sql = "DELETE FROM product_info";
        jdbcTemplate.update(sql);

    }

}

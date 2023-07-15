package com.ChinaMarket.ChinaMarket.Repository;

import com.ChinaMarket.ChinaMarket.Enum.Category;
import com.ChinaMarket.ChinaMarket.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategory(Category category);
}

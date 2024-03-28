package com.ChinaMarket.ChinaMarket.Repository;

import com.ChinaMarket.ChinaMarket.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
//    @Query(value = "select * from customer", nativeQuery = true)

//    List<Customer> findAllCustomers();

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findBymobNo(String mobNo);

    List<Customer> findByCity(String city);

    List<Customer> findByAge(int age);

    List<Customer> findByAgeLessThan(int age);

    List<Customer> findByAgeGreaterThan(int age);

//    Customer findByEmail(String email);
//
//    Customer findBymobNo(String mobNo);
}

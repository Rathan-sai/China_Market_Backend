package com.ChinaMarket.ChinaMarket.Repository;

import com.ChinaMarket.ChinaMarket.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}

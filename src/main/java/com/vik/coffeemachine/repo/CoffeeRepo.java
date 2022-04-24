package com.vik.coffeemachine.repo;

import com.vik.coffeemachine.entity.CoffeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepo extends JpaRepository<CoffeeMachine, Integer> {
    @Query(nativeQuery = true, value = "select * from coffee order by id desc limit 1")
    CoffeeMachine getLastOperation();
}

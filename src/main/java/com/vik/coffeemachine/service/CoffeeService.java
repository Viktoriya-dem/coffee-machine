package com.vik.coffeemachine.service;

import com.vik.coffeemachine.dto.CoffeeMachineDto;
import com.vik.coffeemachine.entity.CoffeeMachine;
import com.vik.coffeemachine.entity.CoffeeType;
import com.vik.coffeemachine.exception.NoCoffeeException;
import com.vik.coffeemachine.exception.NoMilkException;
import com.vik.coffeemachine.repo.CoffeeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepo coffeeRepo;
    private final ModelMapper modelMapper;

    @Transactional
    public String addMilk() {
        CoffeeMachine coffeeMachine = coffeeRepo.getLastOperation();
        CoffeeMachine nextCoffeeMachine = new CoffeeMachine();
        nextCoffeeMachine.setMilkCount(1000);
        nextCoffeeMachine.setCoffeeCount(coffeeMachine.getCoffeeCount());
        nextCoffeeMachine.setOperation("Add milk");
        coffeeRepo.save(nextCoffeeMachine);
        return "Milk added";
    }

    @Transactional(readOnly = true)
    public CoffeeMachineDto getInfo() {
        CoffeeMachineDto coffeeMachineDto = modelMapper
                .map(coffeeRepo.getLastOperation(), CoffeeMachineDto.class);
        return coffeeMachineDto;
    }

    @Transactional
    public String addCoffee() {
        CoffeeMachine coffeeMachine = coffeeRepo.getLastOperation();
        CoffeeMachine nextCoffeeMachine = new CoffeeMachine();
        nextCoffeeMachine.setCoffeeCount(500);
        nextCoffeeMachine.setMilkCount(coffeeMachine.getMilkCount());
        nextCoffeeMachine.setOperation("Add coffee");
        coffeeRepo.save(nextCoffeeMachine);
        return "Coffee added";
    }

    @Transactional
    public String makeCoffee(CoffeeType coffeeType) throws NoCoffeeException, NoMilkException {
        CoffeeMachine coffeeMachine = coffeeRepo.getLastOperation();
        if (coffeeMachine.getCoffeeCount() < coffeeType.getCoffee()) {
            throw new NoCoffeeException();
        }
        if (coffeeType.getMilk() != 0 && coffeeMachine.getMilkCount() < coffeeType.getMilk()) {
            throw new NoMilkException();
        }
        CoffeeMachine nextCoffeeMachine = new CoffeeMachine();
        nextCoffeeMachine.setCoffeeCount(coffeeMachine.getCoffeeCount() - coffeeType.getCoffee());
        nextCoffeeMachine.setMilkCount(coffeeMachine.getMilkCount() - coffeeType.getMilk());
        nextCoffeeMachine.setOperation("Coffee done, type " + coffeeType.getType());
        coffeeRepo.save(nextCoffeeMachine);
        return "Take your " + coffeeType.getType();
    }

}

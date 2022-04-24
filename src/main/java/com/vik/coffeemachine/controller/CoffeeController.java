package com.vik.coffeemachine.controller;

import com.vik.coffeemachine.dto.CoffeeMachineDto;
import com.vik.coffeemachine.entity.CoffeeType;
import com.vik.coffeemachine.service.CoffeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "Coffee-machine", description = "Coffee-machine operations")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @ApiOperation(value = "Add coffee to machine")
    @PostMapping("/add/coffee")
    public String addCoffee() {
        return coffeeService.addCoffee();
    }

    @ApiOperation(value = "Add milk to machine")
    @PostMapping("/add/milk")
    public String addMilk() {
        return coffeeService.addMilk();
    }

    @ApiOperation(value = "Get info about coffee and milk count")
    @GetMapping("/info")
    public CoffeeMachineDto getInfo() {
        return coffeeService.getInfo();
    }

    @ApiOperation(value = "Make coffee")
    @PostMapping("/make/{coffee}")
    public String makeCoffee(@PathVariable CoffeeType coffee) {
        return coffeeService.makeCoffee(coffee);
    }

}

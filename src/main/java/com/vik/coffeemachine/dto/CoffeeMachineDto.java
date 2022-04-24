package com.vik.coffeemachine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeMachineDto {
    private int coffeeCount;
    private int milkCount;
}

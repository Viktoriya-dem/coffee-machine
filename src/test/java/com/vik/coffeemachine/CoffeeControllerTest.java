package com.vik.coffeemachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vik.coffeemachine.config.ModelMapperConfig;
import com.vik.coffeemachine.controller.CoffeeController;
import com.vik.coffeemachine.dto.CoffeeMachineDto;
import com.vik.coffeemachine.entity.CoffeeMachine;
import com.vik.coffeemachine.repo.CoffeeRepo;
import com.vik.coffeemachine.service.CoffeeService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoffeeController.class)
@Import({CoffeeMachineApplication.class,
        CoffeeService.class,
        ModelMapperConfig.class})
public class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CoffeeRepo coffeeRepo;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void coffeeMachineInfoTest() throws Exception {
        when(coffeeRepo.getLastOperation())
                .thenReturn(new CoffeeMachine(1, 500,
                        1000, "Add milk"));
        final var result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/info"))
                .andExpect(status().isOk())
                .andReturn();
        CoffeeMachineDto coffeeMachineDto = objectMapper.readValue(result.getResponse()
                .getContentAsString(), CoffeeMachineDto.class);
        assertThat(coffeeMachineDto.getCoffeeCount()).isEqualTo(500);
        assertThat(coffeeMachineDto.getMilkCount()).isEqualTo(1000);
    }

    @Test
    public void makeCoffeeTest() throws Exception {
        when(coffeeRepo.getLastOperation())
                .thenReturn(new CoffeeMachine(1, 500,
                        1000, "Add milk"));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/make/CAPPUCCINO"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Take your cappuccino")));
    }

    @Test
    public void makeCoffeeNoMilkTest() throws Exception {
        when(coffeeRepo.getLastOperation())
                .thenReturn(new CoffeeMachine(1, 500,
                        10, "Add milk"));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/make/CAPPUCCINO"))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Add milk to machine!")));
    }

    @Test
    public void makeCoffeeNoCoffeeTest() throws Exception {
        when(coffeeRepo.getLastOperation())
                .thenReturn(new CoffeeMachine(1, 10,
                        1000, "Add milk"));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/make/CAPPUCCINO"))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Add coffee to machine!")));
    }

    @Test
    public void addMilkTest() throws Exception {
        when(coffeeRepo.getLastOperation())
                .thenReturn(new CoffeeMachine(1, 500,
                        500, "Add milk"));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/add/milk"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Milk added")));
    }

    @Test
    public void addCoffeeTest() throws Exception {
        when(coffeeRepo.getLastOperation())
                .thenReturn(new CoffeeMachine(1, 200,
                        1000, "Add milk"));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/add/coffee"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Coffee added")));
    }
}

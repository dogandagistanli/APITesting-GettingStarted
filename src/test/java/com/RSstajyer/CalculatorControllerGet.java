package com.RSstajyer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;


@Test


@RestController
public class CalculatorControllerGet {


    @GetMapping("/get/{id}")
    public Result multiply(@RequestBody Operands operands) {
        int product = operands.getA() * operands.getB();
        return new Result(product);
    }




}


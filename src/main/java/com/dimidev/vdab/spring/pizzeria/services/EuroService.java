package com.dimidev.vdab.spring.pizzeria.services;

import java.math.BigDecimal;

public interface EuroService {
    BigDecimal euroToDollar(BigDecimal amountOfEuros);
}

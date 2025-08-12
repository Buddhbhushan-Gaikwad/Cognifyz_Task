package com.SpingWithBushan;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyConverterTest {

    @Test
    public void testConversion() {
        CurrencyConverter converter = new CurrencyConverter();
        double result = converter.convert(10, 80);
        Assertions.assertEquals(800, result);
    }
}

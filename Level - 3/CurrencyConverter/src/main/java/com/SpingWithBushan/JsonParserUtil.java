package com.SpingWithBushan;

import org.json.JSONObject;

public class JsonParserUtil {

    public static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        JSONObject obj = new JSONObject(jsonResponse);
        JSONObject rates = obj.getJSONObject("rates");
        return rates.getDouble(targetCurrency);
    }
}

package com.SpingWithBushan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateService {

    // Replace with your actual API key
    private static final String API_KEY = "a56c63fb43a17657f7a44db4fedb88e4";
    private static final String API_URL = "https://api.exchangeratesapi.io/v1/latest?access_key=%s&base=%s&symbols=%s";

    public double getExchangeRate(String base, String target) throws IOException {
        if (base.equalsIgnoreCase(target)) return 1.0;

        String urlString = String.format(API_URL, API_KEY, target + "," + base);
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new IOException("HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        conn.disconnect();

        // API returns all rates relative to EUR
        double targetRate = JsonParserUtil.parseExchangeRate(response.toString(), target);
        double baseRate = JsonParserUtil.parseExchangeRate(response.toString(), base);

        return targetRate / baseRate;
    }
}

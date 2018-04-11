/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class TaxList {

    private HashMap<String, Tax> taxMap = new HashMap<>();
    public static String TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    public Tax getTax(String stateName) {
        return taxMap.get(stateName);
    }

    public String[] getStates() {
        Set<String> keySet = taxMap.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        return keyArray;
    }

    public Tax[] getTaxes() {
        Collection<Tax> taxCollection = taxMap.values();
        Tax[] taxArray = taxCollection.toArray(new Tax[0]);
        return taxArray;
    }

    public HashMap<String, Tax> readTaxList() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Tax currentTax = new Tax();
            currentTax.setStateName(currentTokens[0]);
            BigDecimal taxRate = new BigDecimal(currentTokens[1]);
            currentTax.setTaxRate(taxRate);
            taxMap.put(currentTokens[0], currentTax);

        }
        sc.close();

        return taxMap;
    }
}

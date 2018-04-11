/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.ProductTypes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class ProductList {

    private HashMap<String, ProductTypes> productMap = new HashMap<>();
    public static String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.productMap);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductList other = (ProductList) obj;
        if (!Objects.equals(this.productMap, other.productMap)) {
            return false;
        }
        return true;
    }

    public ProductTypes getProduct(String productName) {
        return productMap.get(productName);
    }

    public String[] getProductName() {
        Set<String> keySet = productMap.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        return keyArray;
    }

    public ProductTypes[] getProducts() {
        Collection<ProductTypes> productCollection = productMap.values();
        ProductTypes[] productArray = productCollection.toArray(new ProductTypes[0]);
        return productArray;
    }
    
    public void readProductList() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            ProductTypes currentProduct = new ProductTypes();
            currentProduct.setProductType(currentTokens[0]);
            BigDecimal cpsf = new BigDecimal(currentTokens[1]);
            currentProduct.setCostPerSquareFoot(cpsf);
            BigDecimal lcpsf = new BigDecimal(currentTokens[2]);
            currentProduct.setLaborCostPerSquare(lcpsf);
            productMap.put(currentTokens[0], currentProduct);

        }
        sc.close();
    }

}

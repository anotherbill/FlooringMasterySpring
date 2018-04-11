/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class ProductTypes {
    
    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquare;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquare() {
        return laborCostPerSquare;
    }

    public void setLaborCostPerSquare(BigDecimal laborCostPerSquare) {
        this.laborCostPerSquare = laborCostPerSquare;
    }
    
}

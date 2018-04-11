/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class OrderList {

    private HashMap<Integer, Order> orderMap = new HashMap<>();
    public String date = "";
    public String ORDER_FILE = "Orders_" + date + ".txt";

    public final String DELIMITER = ",";

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.orderMap);
        hash = 17 * hash + Objects.hashCode(this.date);
        hash = 17 * hash + Objects.hashCode(this.ORDER_FILE);
        hash = 17 * hash + Objects.hashCode(this.DELIMITER);
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
        final OrderList other = (OrderList) obj;
        if (!Objects.equals(this.orderMap, other.orderMap)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.ORDER_FILE, other.ORDER_FILE)) {
            return false;
        }
        if (!Objects.equals(this.DELIMITER, other.DELIMITER)) {
            return false;
        }
        return true;
    }

    public Order addOrder(Integer orderNumber, Order newOrder) {
        orderMap.put(orderNumber, newOrder);
        return newOrder;
    }
    
    public Order removeOrder(Integer orderNumber) {
        Order removedOrder = orderMap.remove(orderNumber);
        return removedOrder;
    }

    public Order getOrder(Integer orderNumber) {
        return orderMap.get(orderNumber);
    }

    public Order[] getOrders() {
        Collection<Order> orderCollection = orderMap.values();
        Order[] orderArray = orderCollection.toArray(new Order[0]);
        return orderArray;
    }

    public Order buildOrder(String orderDate, Integer orderNumber, String orderName, String orderState, BigDecimal stateTax, String orderProduct, BigDecimal lcps, BigDecimal cpsf,
            BigDecimal orderArea, BigDecimal totalLaborCost, BigDecimal totalMaterialCost, BigDecimal totalCost) {
        Order newOrder = new Order();
        newOrder.setDate(orderDate);
        // Get a New Order Number
        newOrder.setOrderNumber(orderNumber);
        // Enter Name
        newOrder.setCustomerName(orderName);
        // Enter State
        newOrder.setState(orderState);
        newOrder.setTaxRate(stateTax);
        // Enter Product Type
        newOrder.setProductType(orderProduct);
        newOrder.setLaborCostPerSquareFoot(lcps);
        newOrder.setCostPerSquareFoot(cpsf);
        // Enter Area
        newOrder.setArea(orderArea);
        // Do Math
        newOrder.setLaborCost(totalLaborCost);
        newOrder.setMaterialCost(totalMaterialCost);
        newOrder.setTotal(totalCost);

        addOrder(orderNumber, newOrder);

        return newOrder;
    }

    public void readOrderList(String date) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("Order_" + date + ".txt")));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order();
            currentOrder.setDate(date);
            currentOrder.setOrderNumber(Integer.valueOf(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            BigDecimal taxRate = new BigDecimal(currentTokens[3]);
            currentOrder.setTaxRate(taxRate);
            currentOrder.setProductType(currentTokens[4]);
            BigDecimal area = new BigDecimal(currentTokens[5]);
            currentOrder.setArea(area);
            BigDecimal cpsf = new BigDecimal(currentTokens[6]);
            currentOrder.setCostPerSquareFoot(cpsf);
            BigDecimal lcpsf = new BigDecimal(currentTokens[7]);
            currentOrder.setLaborCostPerSquareFoot(lcpsf);
            BigDecimal mc = new BigDecimal(currentTokens[8]);
            currentOrder.setMaterialCost(mc);
            BigDecimal lc = new BigDecimal(currentTokens[9]);
            currentOrder.setLaborCost(lc);
            BigDecimal total = new BigDecimal(currentTokens[10]);
            currentOrder.setTotal(total);

            orderMap.put(Integer.valueOf(currentTokens[0]), currentOrder);
        }
        sc.close();
    }

    public void writeOrderList(String date) throws IOException {

        PrintWriter out = new PrintWriter(new FileWriter("Order_" + date + ".txt"));

        Order[] orderList = getOrders();
        for (int i = 0; i < orderList.length; i++) {
            out.println(orderList[i].getOrderNumber() + DELIMITER
                    + orderList[i].getCustomerName().replaceAll(",", "|") + DELIMITER
                    + orderList[i].getState() + DELIMITER
                    + orderList[i].getTaxRate() + DELIMITER
                    + orderList[i].getProductType() + DELIMITER
                    + orderList[i].getArea() + DELIMITER
                    + orderList[i].getCostPerSquareFoot() + DELIMITER
                    + orderList[i].getLaborCostPerSquareFoot() + DELIMITER
                    + orderList[i].getMaterialCost() + DELIMITER
                    + orderList[i].getLaborCost() + DELIMITER
                    + orderList[i].getTotal()
            );
            out.flush();
        }
        out.close();
        clearOrders();

    }
    public void clearOrders(){
        orderMap = new HashMap<>();
    }
}


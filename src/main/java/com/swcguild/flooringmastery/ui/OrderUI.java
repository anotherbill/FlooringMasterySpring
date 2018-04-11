/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.ui;

import com.swcguild.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author apprentice
 */
public class OrderUI {

    ConsoleIO io = new ConsoleIO();

    public int displayOptions() {
        int userInput = 0;

        do {
            System.out.println("********************************************************************");
            System.out.println("*         Flooring Program                                         *");
            System.out.println("*                                                                  *");
            System.out.println("*  1. Display Orders                                               *");
            System.out.println("*  2. Add an Order                                                 *");
            System.out.println("*  3. Edit an Order                                                *");
            System.out.println("*  4. Remove an Order                                              *");
            System.out.println("*  5. Save Current Work                                            *");
            System.out.println("*  6. Quit                                                         *");
            System.out.println("*                                                                  *");
            System.out.println("********************************************************************");
            userInput = io.intPrompt("Please enter your selection: ");
            if (userInput < 1 || userInput > 6) {
                System.out.println("Invalid option, please select again");
            }
        } while (userInput < 1 || userInput > 6);
        return userInput;

    }

    public String enterDate() {
        DateFormat df = new SimpleDateFormat("mmddyyyy");
        String date = "";
        try {
            date = io.stringPrompt("Enter Date of order (mmddyyyy)");
            Date d1 = df.parse(date);
        } catch (Exception ex) {

        }
        return date;
    }

    public String enterName(String customerName) {
        customerName = io.stringPrompt("Enter Customer Name (" + customerName + "):");
        return customerName;
    }

    public String enterState(String stateName, String oldStateName) {
        System.out.println("Choose a state for Tax Purposes: ");
        System.out.println(stateName);
        return io.stringPrompt("Enter State Abbreviation:");
    }

    public String enterProduct(String productName) {
        System.out.println("Choose a product type: ");
        System.out.println(productName);
        return io.stringPrompt("Enter Product Type:");
    }

    public BigDecimal enterArea() {
        double tempdouble = io.doublePrompt("Enter Total Area:");
        BigDecimal tempArea = new BigDecimal(tempdouble);
        return tempArea;
    }

    public void printOrder(Order viewOrder) {
        System.out.println("Date                        : " + viewOrder.getDate());
        System.out.println("Order Number                : " + viewOrder.getOrderNumber());
        System.out.println("Name                        : " + viewOrder.getCustomerName());
        System.out.println("State                       : " + viewOrder.getState());
        System.out.println("Tax Rate                    : " + viewOrder.getTaxRate());
        System.out.println("Flooring                    : " + viewOrder.getProductType());
        System.out.println("Total Area                  : " + viewOrder.getArea());
        System.out.println("Cost Per Square Foot        : " + viewOrder.getCostPerSquareFoot());
        System.out.println("Labor Cost Per Square Foot  : " + viewOrder.getLaborCostPerSquareFoot());
        System.out.println("Materials Cost              : " + viewOrder.getMaterialCost());
        System.out.println("Labor Cost                  : " + viewOrder.getLaborCost());
        System.out.println("Total Cost    (includes Tax): " + viewOrder.getTotal());
    }

    public void printAllOrders(Order[] orderArray) {
        // Take Order array and split into pieces
        for (int i = 0; i < orderArray.length; i++) {
            System.out.println("Date                        : " + orderArray[i].getDate());
            System.out.println("Order Number                : " + orderArray[i].getOrderNumber());
            System.out.println("Name                        : " + orderArray[i].getCustomerName());
            System.out.println("State                       : " + orderArray[i].getState());
            System.out.println("Tax Rate                    : " + orderArray[i].getTaxRate());
            System.out.println("Flooring                    : " + orderArray[i].getProductType());
            System.out.println("Total Area                  : " + orderArray[i].getArea());
            System.out.println("Cost Per Square Foot        : " + orderArray[i].getCostPerSquareFoot());
            System.out.println("Labor Cost Per Square Foot  : " + orderArray[i].getLaborCostPerSquareFoot());
            System.out.println("Materials Cost              : " + orderArray[i].getMaterialCost());
            System.out.println("Labor Cost                  : " + orderArray[i].getLaborCost());
            System.out.println("Total Cost    (includes Tax): " + orderArray[i].getTotal());

        }
    }

    public Integer enterOrderNumber() {
        return io.intPrompt("Please enter order number: ");
    }

    public Integer displayEditOptions() {
        System.out.println("1. Name on Order");
        System.out.println("2. State where Order is being filled");
        System.out.println("3. Product type for Order");
        System.out.println("4. Total area Required");
        System.out.println("5. No edit");
        return io.intPrompt("What would you like to edit?");
    }

    public void invalidOption() {
        System.out.println("That is an invalid option, please select again.");
    }

    public void invalidEntry() {
        System.out.println("That is an invalid entry, please try again.");
    }

    public void nothingChanged() {
        System.out.println("No changes will be made.");
    }

    public String areYouSure() {
        return io.stringPrompt("Are you sure?");
    }
    public void printMessage(String message){
        System.out.println(message);
            }
    public void mustSave(){
        System.out.println("You must save work before using a different date");
    }

}

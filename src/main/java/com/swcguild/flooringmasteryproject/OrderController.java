/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasteryproject;

import com.swcguild.flooringmastery.factory.BusinessLogic;
import com.swcguild.flooringmastery.dao.OrderList;
import com.swcguild.flooringmastery.dao.TaxList;
import com.swcguild.flooringmastery.dao.ProductList;
import com.swcguild.flooringmastery.dto.Tax;
import com.swcguild.flooringmastery.dto.Order;
import com.swcguild.flooringmastery.dto.ProductTypes;
import com.swcguild.flooringmastery.ui.ConsoleIO;
import com.swcguild.flooringmastery.ui.OrderUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.aspectj.org.eclipse.jdt.internal.compiler.lookup.TypeConstants.IO;

/**
 *
 * @author apprentice
 */
public class OrderController {

    private ConsoleIO io;
    private OrderUI oui;
    private BusinessLogic bl;
    private OrderList ol;
    private TaxList tl;
    private ProductList pl;
    private String oldOrderDate = "01011000";
    private String orderDate = "01011000";
    private boolean firstTime = true;
    private boolean repeat = true;
    
       
//    public ServerInventoryController(IO io, ServerDao dao){
//        this.io = io;
//        this.dao = dao;
//    }
    
    public void setIo(ConsoleIO io) {
        this.io = io;
    }

    public void setBl(BusinessLogic bl) {
        this.bl = bl;
    }
    
    public void setOui(OrderUI oui){
        this.oui = oui;
    }

   
    public void setOl(OrderList ol) {
        this.ol = ol;
    }

    
    public void setTl(TaxList tl) {
        this.tl = tl;
    }

    
    public void setPl(ProductList pl) {
        this.pl = pl;
    }
    
    
    
    
    
    
    
    
    

    public void run() throws /*FileNotFoundException,*/ IOException {
        
//        setIo(new ConsoleIO());
//        setBl(new BusinessLogic());
//        setOui(new OrderUI());
//        setOl(new OrderList());
//        setTl(new TaxList());
//        setPl(new ProductList());
//        
        
        
        
        bl.globalOrderNumber = bl.readConfig();
        tl.readTaxList();
        pl.readProductList();

        while (repeat) {
            int userInput = oui.displayOptions();
            switch (userInput) {
                case 1:
                    System.out.println("Displaying Orders");
                    displayOrders();
                    break;
                case 2:
                    System.out.println("Add an Order");
                    addOrder();
                    break;
                case 3:
                    System.out.println("Edit an Order");
                    editOrder();
                    break;
                case 4:
                    System.out.println("Remove an Order");
                    removeOrder();
                    break;
                case 5:
                    if (!bl.testMode) {
                        System.out.println("Save Current Work");

                        ol.writeOrderList(orderDate);
                        bl.writeConfig(bl.globalOrderNumber);
                        firstTime = true;

                    } else {
                        System.out.println("You are in test mode, you may not save.");
                        firstTime = true;
                    }
                    break;
                case 6:
                    repeat = false;
                    //write file
                    System.out.println("Have a nice day!");
                    break;
                default:
                    oui.invalidOption();
                    break;

            }
        }
    }

    public void displayOrders() {
        //oldOrderDate = orderDate;
        oldOrderDate = orderDate;
        orderDate = oui.enterDate();
        if (!firstTime && !oldOrderDate.contentEquals(orderDate)) {
            System.out.println("You must save work before using a different date");
        } else {
            try {
                firstTime = false;
                ol.readOrderList(orderDate);
                Order[] orderArray = ol.getOrders();
                oui.printAllOrders(orderArray);
            } catch (FileNotFoundException dfe) {
                System.out.println("File for that date not found");
            }
        }
    }

    public void addOrder() {
        // Create a New Order
        String orderName;
        String orderState;
        BigDecimal stateTax = null;
        String orderProduct;
        BigDecimal lcps = null;
        BigDecimal cpsf = null;
        BigDecimal totalLaborCost;
        BigDecimal totalMaterialCost;
        BigDecimal totalCost;

        // Enter Current Date
        oldOrderDate = orderDate;
        orderDate = oui.enterDate();
        //Clear the Map if starting a new batch
        if (!firstTime && !oldOrderDate.contentEquals(orderDate)) {
            oui.mustSave();
            
        } //Edgecase: User views OrderList before adding Order
        /* else if (!oldOrderDate.contentEquals(orderDate)) {
         ol.clearHashMap();
         } */ else {

            try {
                firstTime = false;
                ol.readOrderList(orderDate);
                Order[] orderArray = ol.getOrders();
                oui.printAllOrders(orderArray);
            } catch (FileNotFoundException dfe) {

            }
                    // kick out if order dates are different

            // Enter Name
            String customerName = "";
            orderName = oui.enterName(customerName);
            // Enter State
            boolean stateValid = false;
            do {
                orderState = oui.enterState(Arrays.toString(tl.getStates()), null);
                // Is the State valid?
                Tax tempTax = tl.getTax(orderState);
                try {
                    stateTax = tempTax.getTaxRate();
                    stateValid = true;
                } catch (NullPointerException npe) {
                    oui.invalidEntry();
                    stateValid = false;
                }
            } while (stateValid == false);
            // Enter Product Type
            boolean productValid;

            do {
                orderProduct = oui.enterProduct(Arrays.toString(pl.getProductName()));
                // Is the Product valid?
                try {
                    ProductTypes tempProduct = pl.getProduct(orderProduct);
                    lcps = tempProduct.getLaborCostPerSquare();
                    cpsf = tempProduct.getCostPerSquareFoot();
                    productValid = true;
                } catch (NullPointerException npe) {
                    oui.invalidEntry();
                    productValid = false;
                }
            } while (productValid == false);
            // Enter Area
            BigDecimal orderArea;
            do {
                orderArea = oui.enterArea();
                if (orderArea.doubleValue() < 0) {
                    oui.invalidEntry();
                }
            } while (orderArea.doubleValue() < 0);
            // Do Math
            totalLaborCost = bl.laborCost(orderArea, lcps);
            totalMaterialCost = bl.laborCost(orderArea, cpsf);
            totalCost = bl.totalCost(totalLaborCost, totalMaterialCost, stateTax);
            ol.buildOrder(orderDate, bl.globalOrderNumber, orderName, orderState, stateTax, orderProduct, lcps, cpsf, orderArea, totalLaborCost, totalMaterialCost, totalCost);
            bl.globalOrderNumber++; // Increment Counter
        }

    }

    public void editOrder() {

        oldOrderDate = orderDate;
        orderDate = oui.enterDate();

        if (!firstTime && !oldOrderDate.contentEquals(orderDate)) {
            System.out.println("You must save work before using a different date");
        } else {
            //Edgecase: User views OrderList before adding Order
            if (!oldOrderDate.contentEquals(orderDate) && !firstTime) {
                ol.clearOrders();
            }
            try {
                firstTime = false;
                ol.readOrderList(orderDate);
                Order[] orderArray = ol.getOrders();
                oui.printAllOrders(orderArray);
            } catch (FileNotFoundException dfe) {
                System.out.println("File for that date not found");
            }
            // Ask for Order Number
            int orderNumber = oui.enterOrderNumber();
            Order editingOrder = ol.getOrder(orderNumber);

            int editNumber = oui.displayEditOptions();
            switch (editNumber) {
                case 1:
                    //Set New Name
                    String customerName = ol.getOrder(orderNumber).getCustomerName();
                    if (customerName != "") {
                        ol.getOrder(orderNumber).setCustomerName(oui.enterName(customerName));
                    } else {
                        oui.nothingChanged();
                    }
                    break;
                case 2:
                    //Get and Set new State
                    String orderState = ol.getOrder(orderNumber).getState();
                    orderState = oui.enterState(Arrays.toString(tl.getStates()), orderState);
                    if (!orderState.isEmpty()) {
                        ol.getOrder(orderNumber).setState(orderState);
                        Tax tempTax = tl.getTax(orderState);
                        //Set new Tax Rate
                        ol.getOrder(orderNumber).setTaxRate(tempTax.getTaxRate());
                        BigDecimal stateTax = tempTax.getTaxRate();
                        //Set new Total
                        ol.getOrder(orderNumber).setTotal(bl.totalCost(ol.getOrder(orderNumber).getLaborCost(), ol.getOrder(orderNumber).getMaterialCost(), stateTax));
                    } else {
                        oui.nothingChanged();
                    }
                    break;
                case 3:
                    // Get and Set new Product
                    String orderProduct = ol.getOrder(orderNumber).getProductType();
                    if (!orderProduct.isEmpty()) {
                        orderProduct = oui.enterProduct(Arrays.toString(pl.getProductName()));
                        ol.getOrder(orderNumber).setProductType(orderProduct);
                        ProductTypes tempProduct = pl.getProduct(orderProduct);
                        // Get and Set new costs for product type
                        ol.getOrder(orderNumber).setLaborCostPerSquareFoot(tempProduct.getLaborCostPerSquare());
                        BigDecimal lcps = tempProduct.getLaborCostPerSquare();
                        ol.getOrder(orderNumber).setCostPerSquareFoot(tempProduct.getCostPerSquareFoot());
                        BigDecimal cpsf = tempProduct.getCostPerSquareFoot();
                        // Calculate new Costs
                        BigDecimal totalLaborCost = bl.laborCost(ol.getOrder(orderNumber).getArea(), lcps);
                        ol.getOrder(orderNumber).setLaborCost(totalLaborCost);
                        BigDecimal totalMaterialCost = bl.laborCost(ol.getOrder(orderNumber).getArea(), cpsf);
                        ol.getOrder(orderNumber).setMaterialCost(totalMaterialCost);
                        // Set new Total
                        ol.getOrder(orderNumber).setTotal(bl.totalCost(totalLaborCost, totalMaterialCost, ol.getOrder(orderNumber).getTaxRate()));
                    } else {
                        oui.nothingChanged();
                    }
                    break;
                case 4:
                    // Get and Set new Area
                    BigDecimal orderArea = new BigDecimal(0);
                    try {
                        orderArea = oui.enterArea();
                    } catch (NumberFormatException nfe) {
                        oui.nothingChanged();
                    }
                    do {
                        if (orderArea.doubleValue() < 0) {
                            oui.invalidEntry();
                        }
                    } while (orderArea.doubleValue() < 0);

                    ol.getOrder(orderNumber).setArea(orderArea);
                    // Calculate new Costs
                    BigDecimal newtotalLaborCost = bl.laborCost(orderArea, ol.getOrder(orderNumber).getLaborCostPerSquareFoot());
                    ol.getOrder(orderNumber).setLaborCost(newtotalLaborCost);
                    BigDecimal newtotalMaterialCost = bl.laborCost(orderArea, ol.getOrder(orderNumber).getCostPerSquareFoot());
                    ol.getOrder(orderNumber).setMaterialCost(newtotalMaterialCost);
                    //Set new Total
                    ol.getOrder(orderNumber).setTotal(bl.totalCost(newtotalLaborCost, newtotalMaterialCost, ol.getOrder(orderNumber).getTaxRate()));
                    break;
                case 5:
                    break;
                default:
                    oui.invalidOption();
                    break;

            }
        }

    }

    public void removeOrder() throws IOException {
        //oldOrderDate = orderDate;
        orderDate = oui.enterDate();
        if (!firstTime && !oldOrderDate.contentEquals(orderDate)) {
            System.out.println("You must save work before using a different date");
        } else {
            try {
                firstTime = false;
                ol.readOrderList(orderDate);
                Order[] orderArray = ol.getOrders();
                oui.printAllOrders(orderArray);
            } catch (FileNotFoundException dfe) {
                System.out.println("File for that date not found");
            }

            Integer orderNumber = oui.enterOrderNumber();
            String areYouSure = oui.areYouSure();
            /*(areYouSure.compareToIgnoreCase("Yes")) || (areYouSure.compareToIgnoreCase("Y"))*/
            if (areYouSure.equalsIgnoreCase("yes") || areYouSure.equalsIgnoreCase("y")) {
                oui.printOrder(ol.getOrder(orderNumber));
                ol.removeOrder(orderNumber);
                ol.writeOrderList(orderDate);
            } else {
                oui.nothingChanged();
            }
        }
    }

}

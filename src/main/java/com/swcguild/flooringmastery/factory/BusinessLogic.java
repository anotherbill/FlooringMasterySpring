/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.factory;

import com.swcguild.flooringmastery.dao.OrderList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class BusinessLogic {

    OrderList newOrderList = new OrderList();
    public String configFileName = "config.txt";
    // private HashMap<Integer, Order> thisOrderList = new HashMap<>();
    private String DELIMITER = ",";
    //Order Number Generator
    // public int globalOrderNumber;
    public boolean testMode;
    public Integer globalOrderNumber = 0;
    
    public Integer readConfig() throws FileNotFoundException {

        
        Scanner sc = new Scanner(new BufferedReader(new FileReader(configFileName)));
        String currentLine;
        String[] currentTokens;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            globalOrderNumber = Integer.valueOf(currentTokens[0]);
            testMode = Boolean.valueOf(currentTokens[1]);

        }
        return globalOrderNumber;
    }
    public void writeConfig(Integer orderNum) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("config.txt"));
        out.println(orderNum + DELIMITER + testMode);
        out.flush();
        out.close();
        /*
        public static void WriteDVDLibrary() throws IOException {

 PrintWriter out = new PrintWriter(new FileWriter(DVD_FILE));
 DVD[] DVDList = dvd.getAllDVD();
 for (int i = 0; i < DVDList.length; i++) {

 out.println(DVDList[i].getTitle() + DELIMITER + DVDList[i].getReleaseDate() + DELIMITER + DVDList[i].getMpaaRating() + DELIMITER + DVDList[i].getDirectorName() + DELIMITER + DVDList[i].getStudio() + DELIMITER + DVDList[i].getNote());
 }
 out.close();
 }
        */
    }
    
    public Integer globalOrderNumberCounter(Integer globalOrderNumber) {
        return globalOrderNumber++;
    }



    
    
    public BigDecimal laborCost(BigDecimal area, BigDecimal laborCost) {
        return area.multiply(laborCost);
    }
    
    public BigDecimal materialCost(BigDecimal area, BigDecimal materialCost) {
        return area.multiply(materialCost);
    }
    
    public BigDecimal totalCost(BigDecimal laborCost, BigDecimal materialCost, BigDecimal tax) {
        return tax.multiply(laborCost.add(materialCost));
    }
}

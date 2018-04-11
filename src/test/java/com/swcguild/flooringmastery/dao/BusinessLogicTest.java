/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

//import flooringmasteryfactory.BusinessLogic;
//import flooringmasterydao.OrderList;
import com.swcguild.flooringmastery.factory.BusinessLogic;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class BusinessLogicTest {
    OrderList ol;
    BusinessLogic bl;
    
    public BusinessLogicTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ol = new OrderList();
        bl = new BusinessLogic();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test 
    
    
    
    
    public void readConfigTest() throws FileNotFoundException {
               bl.configFileName = "configtest.txt";
               
               
               Integer ordno = (bl.readConfig());
               Integer expectedResult  = 6;
               assertEquals(ordno,expectedResult); 
        
    }
    public void laborCostTest() {
        BigDecimal expectedResult;
        expectedResult = new BigDecimal(4);
        BigDecimal result;
        
        BigDecimal in1;
        BigDecimal in2;
        in1 = new BigDecimal(4);
        in2 = new BigDecimal(4);
        
        
        result = bl.laborCost(in1,in2);
        assertEquals(expectedResult, result);
    }
    /* 
    public BigDecimal laborCost(BigDecimal area, BigDecimal laborCost) {
        return area.multiply(laborCost);
    } 
    */
}

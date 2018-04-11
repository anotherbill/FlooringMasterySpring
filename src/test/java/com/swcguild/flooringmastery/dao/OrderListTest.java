/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

//import flooringmasterydao.OrderList;
//import flooringmasterydto.Order;
import com.swcguild.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderListTest {
    
    public OrderListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class OrderList.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        Integer orderNumber = null;
        Order newOrder = null;
        OrderList instance = new OrderList();
        Order expResult = null;
        Order result = instance.addOrder(orderNumber, newOrder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getOrder method, of class OrderList.
     */
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        Integer orderNumber = null;
        OrderList instance = new OrderList();
        Order expResult = null;
        Order result = instance.getOrder(orderNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getOrders method, of class OrderList.
     */
    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        OrderList instance = new OrderList();
        Order[] expResult = null;
        Order[] result = instance.getOrders();
 //       instance.
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of buildOrder method, of class OrderList.
     */
    @Test
    public void testBuildOrder() {
        System.out.println("buildOrder");
        String orderDate = "";
        Integer orderNumber = 1234;
        String orderName = "Brown";
        String orderState = "OH";
        BigDecimal stateTax = new BigDecimal(.065);
        String orderProduct = "Tile";
        BigDecimal lcps = new BigDecimal(1.00);
        BigDecimal cpsf = new BigDecimal(2.00);
        BigDecimal orderArea = new BigDecimal(100);
        BigDecimal totalLaborCost = new BigDecimal(100);
        BigDecimal totalMaterialCost = new BigDecimal(200);
        BigDecimal totalCost = new BigDecimal(300);
        OrderList instance = new OrderList();
        Order expResult = null;
        Order result = instance.buildOrder(orderDate, orderNumber, orderName, orderState, stateTax, orderProduct, lcps, cpsf, orderArea, totalLaborCost, totalMaterialCost, totalCost);
       // assertEquals(instance.getOrder(orderNumber),1234);
        assertEquals(instance.getOrder(orderNumber),result);
        System.out.println("Result " + result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of readOrderList method, of class OrderList.
     */
    @Test
    public void testReadOrderList() throws Exception {
        System.out.println("readOrderList");
        String date = "";
        OrderList instance = new OrderList();
        instance.readOrderList(date);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of writeOrderList method, of class OrderList.
     */
    @Test
    public void testWriteOrderList() throws Exception {
        System.out.println("writeOrderList");
        String date = "";
        OrderList instance = new OrderList();
        instance.writeOrderList(date);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of clearHashMap method, of class OrderList.
     */
//    @Test
//    public void testClearHashMap() throws FileNotFoundException {
//        System.out.println("clearHashMap");
//        OrderList instance = new OrderList();
//        instance.readOrderList("06012013");
//        
//        Order a = new Order();
//        a = instance.getOrder(1);
//        
//        System.out.println("Name:" +  a.getCustomerName() );
//        instance.clearOrders();
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}

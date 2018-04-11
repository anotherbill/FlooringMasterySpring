/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

//import flooringmasterydao.TaxList;
//import flooringmasterydto.Tax;
import com.swcguild.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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
public class TaxListTest {

    TaxList instance;

    public TaxListTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new TaxList();
        instance.TAX_FILE = "TestTaxes.txt";
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTax method, of class TaxList.
     */
    @Test
    public void testGetTax() {
        System.out.println("getTax");
        String stateName = "OH";
        
        // TaxList instance = new TaxList();
        Tax expResult = null;
        Tax result = instance.getTax(stateName);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getStates method, of class TaxList.
     */
    @Test
    public void testGetStates() throws IOException {
        System.out.println("getStates");
        // TaxList instance = new TaxList();
        instance.readTaxList();
        String[] expResult = {"PA", "IN", "OH", "MI"};

        String[] result = instance.getStates();
        System.out.println(" result " + result[0] + result[1] + result[2] + result[3]);
        
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxes method, of class TaxList.
     */
    @Test
    public void testGetTaxes() throws FileNotFoundException {
        System.out.println("getTaxes");
        // TaxList instance = new TaxList();
        instance.readTaxList();

        Tax[] expResult = {instance.getTax("PA"), instance.getTax("IN"), instance.getTax("OH"), instance.getTax("MI")};

        Tax[] result = instance.getTaxes();

        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of readTaxList method, of class TaxList.
     * @throws java.lang.Exception
     */
    
    @Test
    public void testReadTaxList() throws Exception {
        // TaxList tl = new TaxList();
        HashMap<String, Tax> importMap = new HashMap<>();

        System.out.println("readTaxList");
        // TaxList instance = new TaxList();
        // instance.TAX_FILE = "TestTaxes.txt";
        importMap = instance.readTaxList();
        
        assertEquals(importMap.size(), 4);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmasteryproject;

import com.swcguild.flooringmastery.ui.OrderUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProject {
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException,  FileNotFoundException{
        
        
        /*
        
        
       Beans!  
    <bean id="Io" class="com.swcguild.flooringmastery.ui.ConsoleIO"/>
    <bean id="Bl" class="com.swcguild.flooringmastery.factory.BusinessLogic"/>
    <bean id="Oui" class="com.swcguild.flooringmastery.ui.OrderUI"/>
    <bean id="Ol" class="com.swcguild.flooringmastery.dao.OrderList"/>
    <bean id="Tl" class="com.swcguild.flooringmastery.dao.TaxList"/>
    <bean id="Pl" class="com.swcguild.flooringmastery.dao.ProductList"/>
    
    <bean id="oc" class="com.swcguild.flooringmasteryproject.OrderController">
        <property name="io" ref="Io"/>
        <property name="bl" ref="Bl"/>
        <property name="oui" ref="Oui"/>
        <property name="ol" ref="Ol"/>
        <property name="tl" ref="Tl"/>
        <property name="pl" ref="Pl"/>
    </bean>

        
        */
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderController oc = (OrderController) ctx.getBean("oc");
        oc.run();
    }
    
}

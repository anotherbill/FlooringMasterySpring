/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.aspects;

import com.swcguild.flooringmastery.dao.OrderList;
import static com.swcguild.flooringmastery.dao.ProductList.DELIMITER;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.aspectj.lang.ProceedingJoinPoint;

import com.swcguild.flooringmastery.dto.Order;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class AuditLog {

    public Object auditLogMethod(ProceedingJoinPoint jp) {
        Object ret = null;
        try {
            PrintWriter out = new PrintWriter(new FileWriter("OrderLog" + ".txt"));

            out.println("Order added, changed or deleted on " + LocalDate.now());
            out.flush();
            out.close();
            System.out.println("In AuditLogMethod");
        } catch (Throwable t) {
            System.out.println("Exception in SimpleTimerAspect.timeMethod()");
        }
        return ret;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.ase.calculo;

import edu.uniandes.ecos.ase.dto.DatoEntradaDTO;
import edu.uniandes.ecos.ase.utilidad.LeerDatosFuenteExterna;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mariocelis
 */
public class CalculoSimpsonRuleTest {
    
    public CalculoSimpsonRuleTest() {
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
     * Test of calculoXi method, of class CalculoSimpsonRule.
     */
    @Test
    public void testCalculoXi() {
        System.out.println("calculoXi");
        double w = 0.11;
        double i = 2;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 0.22;
        double result = instance.calculoXi(w, i);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of coeficienteUno method, of class CalculoSimpsonRule.
     */
    @Test
    public void testCoeficienteUno() {
        System.out.println("coeficienteUno");
        double xi = 0.22;
        double dof = 9;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 1.0053777777777777;
        double result = instance.coeficienteUno(xi, dof);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of coeficienteDos method, of class CalculoSimpsonRule.
     */
    @Test   
    public void testCoeficienteDos() {
        System.out.println("coeficienteDos");
        double coeficienteUno = 1.0053777777777777;
        double dof = 9;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 0.9735395330248463;
        double result = instance.coeficienteDos(coeficienteUno, dof);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calcularGamma method, of class CalculoSimpsonRule.
     */
    @Test
    public void testCalcularGamma() {
        System.out.println("calcularGamma");
        double x = 5;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 24;
        double result = instance.calcularGamma(x);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of coeficienteTres method, of class CalculoSimpsonRule.
     */
    @Test
    public void testCoeficienteTres() {
        System.out.println("coeficienteTres");
        double dof = 9;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 0.3880349088716687;
        double result = instance.coeficienteTres(dof);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of fxi method, of class CalculoSimpsonRule.
     */
    @Test
    public void testFxi() {
        System.out.println("fxi");
        double coeficienteDos = 0.9735395330248463;
        double coeficienteTres = 0.3880349088716687;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 0.37776732398026314;
        double result = instance.fxi(coeficienteDos, coeficienteTres);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of factorFinal method, of class CalculoSimpsonRule.
     */
    @Test
    public void testFactorFinal() {
        System.out.println("factorFinal");
        double w = 0.11;
        double multiplicador = 2;
        double fxi = 0.37776732398026314;
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 0.027702937091885965;
        double result = instance.factorFinal(w, multiplicador, fxi);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of p method, of class CalculoSimpsonRule.
     */
    @Test
    public void testP() {
        System.out.println("p");
        List<Double> valores = new ArrayList<>();
        valores.add(0.014227947);
        valores.add(0.056530751);
        valores.add(0.027702937);
        valores.add(0.053590165);
        valores.add(0.025583311);
        valores.add(0.048241008);
        valores.add(0.02246659);
        valores.add(0.041368062);
        valores.add(0.018833639);
        valores.add(0.033942225);
        valores.add(0.00757227);
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        double expResult = 0.350058905;
        double result = instance.p(valores);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calcularSimpsonRule method, of class CalculoSimpsonRule.
     */
    @Test
    public void testCalcularSimpsonRule() {
        System.out.println("calcularSimpsonRule");
        DatoEntradaDTO datoEntradaDTO = new DatoEntradaDTO();
        datoEntradaDTO = LeerDatosFuenteExterna.leerArchivoPlanoSeparacionComa("src/main/resources/archivo/CASO_PRUEBA_UNO.txt");
        CalculoSimpsonRule instance = new CalculoSimpsonRule();
        datoEntradaDTO.setP(instance.calcularSimpsonRule(datoEntradaDTO));
        double expResult = 0.3500589042865573;
        double result = instance.calcularSimpsonRule(datoEntradaDTO);
        assertEquals(expResult, result, 0.0);
    }
    
}
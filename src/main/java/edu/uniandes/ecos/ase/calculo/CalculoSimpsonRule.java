/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.ase.calculo;

import edu.uniandes.ecos.ase.dto.DatoEntradaDTO;
import edu.uniandes.ecos.ase.utilidad.Constantes;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.special.Gamma;

/**
 *
 * @author mariocelis
 */
public class CalculoSimpsonRule implements ICalculoSimpsonRule {

    public CalculoSimpsonRule() {
    }

    @Override
    public double calculoXi(double w, double i) {
        double xi = 0;
        try {
            xi = w * i;
        } catch (Exception e) {
            System.out.println("Error calculando Xi: " + e.getMessage());
        }
        return xi;
    }

    @Override
    public double coeficienteUno(double xi, double dof) {
        double coeficienteUno = 0;
        try {
            coeficienteUno = 1 + ((Math.pow(xi, 2)) / dof);
        } catch (Exception e) {
            System.out.println("Error calculando el coeficiente 1: " + e.getMessage());
        }
        return coeficienteUno;
    }

    @Override
    public double coeficienteDos(double coeficienteUno, double dof) {
        double coeficienteDos = 0;
        try {
            coeficienteDos = Math.pow(coeficienteUno, (-(dof + 1) / 2));
        } catch (Exception e) {
            System.out.println("Error calculando el coeficiente 2: " + e.getMessage());
        }
        return coeficienteDos;
    }

    @Override
    public double calcularGamma(double x) {
        double valorGamma = 0;
        try {
            valorGamma = Gamma.gamma(x);
        } catch (Exception e) {
            System.out.println("Error calculando la función gamma, para el número: " + x + " Detalle: " + e.getMessage());
        }
        return valorGamma;
    }

    @Override
    public double coeficienteTres(double dof) {
        double coeficienteTres = 0;
        try {
            coeficienteTres = (calcularGamma((dof + 1) / 2)) / (((Math.pow(dof * Math.PI, 0.5))) * calcularGamma(dof / 2));
        } catch (Exception e) {
            System.out.println("Error calculando el coeficiente tres: " + e.getMessage());
        }
        return coeficienteTres;
    }

    @Override
    public double fxi(double coeficienteDos, double coeficienteTres) {
        return coeficienteDos * coeficienteTres;
    }

    @Override
    public double factorFinal(double w, double multiplicador, double fxi) {
        return (w / 3) * multiplicador * fxi;
    }

    @Override
    public double p(List<Double> valores) {
        double p = 0;
        try {
            for (Double valor : valores) {
                p = valor + p;
            }
        } catch (Exception e) {
            System.out.println("Error calculando el valor de p: " + e.getMessage());
        }
        return p;
    }

    @Override
    public double calcularSimpsonRule(DatoEntradaDTO datoEntradaDTO) {
        double w;
        double dof;
        double multiplicador;
        double p;
        CalculoSimpsonRule calculoSimpsonRule = new CalculoSimpsonRule();
        List<Double> listaValores = new ArrayList<>();
        try {
            if (datoEntradaDTO != null) {
                if (datoEntradaDTO.getX() != null) {
                    w = datoEntradaDTO.getX() / Constantes.NUM_SEC;
                    if (datoEntradaDTO.getDof() != null) {
                        dof = datoEntradaDTO.getDof();
                        for (int i = 0; i <= Constantes.NUM_SEC; i++) {
                            Double xi = calculoSimpsonRule.calculoXi(w, i);
                            Double coeficienteUno = calculoSimpsonRule.coeficienteUno(xi, dof);
                            Double coeficienteDos = calculoSimpsonRule.coeficienteDos(coeficienteUno, dof);
                            Double coeficienteTres = calculoSimpsonRule.coeficienteTres(dof);
                            Double fxi = calculoSimpsonRule.fxi(coeficienteDos, coeficienteTres);
                            if (i == 0 || i == Constantes.NUM_SEC) {
                                multiplicador = 1;
                            } else if (i % 2 != 0) {
                                multiplicador = 4;
                            } else {
                                multiplicador = 2;
                            }
                            Double factorFinal = calculoSimpsonRule.factorFinal(w, multiplicador, fxi);
                            listaValores.add(factorFinal);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error calculando la regla de simpson: " + e.getMessage());
        }
        p = calculoSimpsonRule.p(listaValores);
        return p;
    }
}

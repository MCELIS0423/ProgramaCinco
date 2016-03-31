/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.ase.calculo;

import edu.uniandes.ecos.ase.dto.DatoEntradaDTO;
import java.util.List;

/**
 *
 * @author mariocelis
 */
public interface ICalculoSimpsonRule {

    /**
     * Obtiene el valor de la multiplicación del índice con w
     *
     * @param w
     * @param i
     * @return El valor de Xi
     */
    public double calculoXi(double w, double i);

    /**
     * Formula descrita a continuación (1+(xi^2)/dof)
     *
     * @param xi
     * @param dof
     * @return Valor de la formula parametrizada como uno
     */
    public double coeficienteUno(double xi, double dof);

    /**
     * Método que retorna el valor de la formula siguiente:
     * (ResultadoCoeficienteUno)^(-(dof+1)/2)
     *
     * @param coeficienteUno
     * @param dof
     * @return Valor del la formula parametrizada como dos
     */
    public double coeficienteDos(double coeficienteUno, double dof);

    /**
     * Obtiene el valor gamma de un valor ingresado
     *
     * @param x
     * @return Valor gamma del número ingresado
     */
    public double calcularGamma(double x);

    /**
     * método que retorna el valor de la formula siguiente:
     * (GAMMA((dof+1)/2))/(((dof*PI)^(1/2))*(GAMMA(dof/2)))
     *
     * @param dof
     * @return Valor de la formula parametrzada como tres
     */
    public double coeficienteTres(double dof);

    /**
     * Realiza el producto entre el coeficiente tres y el coeficiente dos
     *
     * @param coeficienteDos
     * @param coeficienteTres
     * @return Valor de F(xi)
     */
    public double fxi(double coeficienteDos, double coeficienteTres);

    /**
     * Calcula el valor final de la formula para un xi dado
     *
     * @param w
     * @param multiplicador
     * @param fxi
     * @return Valor del factor final
     */
    public double factorFinal(double w, double multiplicador, double fxi);

    /**
     * El resultado general de todos los factores generales
     *
     * @param valores
     * @return la suma de todos los factores final
     */
    public double p(List<Double> valores);

    /**
     * Método encargado de realizar todos los cálculos necesarios para obtener
     * el valor del área bajo la curva a través de la regla de simpson
     *
     * @param datoEntradaDTO
     * @return valor del área
     */
    public double calcularSimpsonRule(DatoEntradaDTO datoEntradaDTO);
}
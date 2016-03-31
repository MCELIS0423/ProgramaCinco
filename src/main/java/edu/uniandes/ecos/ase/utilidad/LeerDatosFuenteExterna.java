/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.ase.utilidad;

import edu.uniandes.ecos.ase.dto.DatoEntradaDTO;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List; 

/**
 *
 * @author mariocelis
 */
public class LeerDatosFuenteExterna {

    /*Constructor de la aplicación*/
    public LeerDatosFuenteExterna() {
    }

    /**
     * @author Mario Celis
     * @since 27/01/2016
     * @return Lista con los datos leídos de un archivo plano
     */
    public static List<String> leerArchivoPlano(String rutaArchivo) {
        String lineaLeida = null;
        List<String> listaDato = new ArrayList<String>();
        try {
            /*Se abre el archivo*/
            FileInputStream fstream = new FileInputStream(rutaArchivo);
            /*Se crea el objeto de entrada*/
            DataInputStream entrada = new DataInputStream(fstream);
            /*Se crea el buffer de lectura*/
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            /*Se lee el buffer linea por línea*/
            while ((lineaLeida = buffer.readLine()) != null) {
                //Se imprime la línea por pantalla para revisar un registro uno a uno
                //System.out.println("Datos: "+lineaLeida);
                listaDato.add(lineaLeida);
            }
            //Se cierra el archivo
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error durante el cálculo: " + e.getMessage());
        }
        return listaDato;
    }

    /**
     * @author Mario Celis
     * @since 27/01/2016
     * @return Lista con los datos leídos de un archivo plano
     */
    public static DatoEntradaDTO leerArchivoPlanoSeparacionComa(String rutaArchivo) {
        String lineaLeida = null;
        DatoEntradaDTO datoEntradaDTO = new DatoEntradaDTO();
        try {
            /*Se abre el archivo*/
            FileInputStream fstream = new FileInputStream(rutaArchivo);
            /*Se crea el objeto de entrada*/
            DataInputStream entrada = new DataInputStream(fstream);
            /*Se crea el buffer de lectura*/
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            /*Se lee el buffer linea por línea*/
            while ((lineaLeida = buffer.readLine()) != null) {
                //Se imprime la línea por pantalla para revisar un registro uno a uno
                //System.out.println("Datos: "+lineaLeida);
                //Se separan los registros que están separados a través de una "," y se realiza el cociente entre ellos
                String[] valores;
                valores = lineaLeida.split(",");
                Double x = Double.parseDouble(valores[0]);
                Double dof = Double.parseDouble(valores[1]);
                datoEntradaDTO.setX(x);
                datoEntradaDTO.setDof(dof);
            }
            //Se cierra el archivo
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error leyendo el archivo plano: " + e.getMessage());
        }
        return datoEntradaDTO;
    }
}

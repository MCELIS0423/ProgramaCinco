
import edu.uniandes.ecos.ase.calculo.CalculoSimpsonRule;
import edu.uniandes.ecos.ase.dto.DatoEntradaDTO;
import edu.uniandes.ecos.ase.utilidad.LeerDatosFuenteExterna;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/calcularReglaSimpson", (req, res) -> {
            String retorno;
            CalculoSimpsonRule calculoSimpsonRule = new CalculoSimpsonRule();

            List<DatoEntradaDTO> listaResultado = new ArrayList<>();

            //Caso de prueba 1
            DatoEntradaDTO datoEntradaDTOUno = new DatoEntradaDTO();
            datoEntradaDTOUno = LeerDatosFuenteExterna.leerArchivoPlanoSeparacionComa("src/main/resources/archivo/CASO_PRUEBA_UNO.txt");
            datoEntradaDTOUno.setP(calculoSimpsonRule.calcularSimpsonRule(datoEntradaDTOUno));

            //Caso de prueba 2
            DatoEntradaDTO datoEntradaDTODos = new DatoEntradaDTO();
            datoEntradaDTODos = LeerDatosFuenteExterna.leerArchivoPlanoSeparacionComa("src/main/resources/archivo/CASO_PRUEBA_DOS.txt");
            datoEntradaDTODos.setP(calculoSimpsonRule.calcularSimpsonRule(datoEntradaDTODos));

            //Caso de prueba 3
            DatoEntradaDTO datoEntradaDTOTres = new DatoEntradaDTO();
            datoEntradaDTOTres = LeerDatosFuenteExterna.leerArchivoPlanoSeparacionComa("src/main/resources/archivo/CASO_PRUEBA_TRES.txt");
            datoEntradaDTOTres.setP(calculoSimpsonRule.calcularSimpsonRule(datoEntradaDTOTres));

            listaResultado.add(datoEntradaDTOUno);
            listaResultado.add(datoEntradaDTODos);
            listaResultado.add(datoEntradaDTOTres);

            retorno = "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<style>"
                    + "table, th, td {"
                    + "border: 1px solid black;"
                    + "border-collapse: collapse;"
                    + "}"
                    + "th, td {"
                    + "padding: 5px;"
                    + "text-align: left;"
                    + "}"
                    + "table#t01 {"
                    + "width: 100%;    "
                    + "background-color: #A9BCF5;"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<table id=\"t01\">"
                    + "<tbody>"
                    + "<tr>"
                    + "<th>x</th>"
                    + "<th>dof</th>"
                    + "<th>p</th>"
                    + "</tr>";
            int i = 1;
            for (DatoEntradaDTO dto : listaResultado) {
                retorno += "<tr>"
                        + "<td>" + dto.getX() + i + "</td>"
                        + "<td>" + dto.getDof() + "</td>"
                        + "<td>" + dto.getP() + "</td>"
                        + "</tr>";
                i++;
            }
            retorno += "</tbody>"
                    + "</table>"
                    + "</body>"
                    + "</html>";

            return retorno;
        });
    }
}

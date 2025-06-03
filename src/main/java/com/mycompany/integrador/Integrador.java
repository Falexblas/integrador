
package com.mycompany.integrador;

import Conexion.Conexiondb;
import java.sql.Connection;


public class Integrador {

    public static void main(String[] args) {
          Connection conn = Conexiondb.getConexion();
        // Aquí puedes usar la conexión para hacer consultas
        Conexiondb.cerrarConexion(); // Cierra cuando termines
    }
}

package com.centrodiagnostico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de arranque de la aplicación.
 * Al ejecutarse, Spring Boot:
 *  - crea el ApplicationContext,
 *  - escanea los paquetes de com.centrodiagnostico buscando componentes
 *    (@RestController, @Service, @Repository),
 *  - resuelve e inyecta automáticamente las dependencias entre ellos
 *    (Inyección de Dependencias por constructor),
 *  - levanta el servidor Tomcat embebido.
 *
 * Requerimiento 6: si esta clase corre sin excepciones y el log muestra
 * "Tomcat started on port(s): 8080" y "Started CentroDiagnosticoApplication",
 * el contexto se inicializó correctamente.
 */
@SpringBootApplication
public class CentroDiagnosticoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentroDiagnosticoApplication.class, args);
    }
}

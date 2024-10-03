package com.hazinlab.gestortareasbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GestorTareasBackendApplicationTests {

  @Test
  void contextLoads() {
    // Si el contexto de Spring se carga correctamente, esta prueba pasará.
  }

  /**
   * Verifica que el método main se ejecuta sin lanzar excepciones.
   */
  @Test
  void main() {
    // Ejecutar el método main de la aplicación
    GestorTareasBackendApplication.main(new String[] {});
  }
}

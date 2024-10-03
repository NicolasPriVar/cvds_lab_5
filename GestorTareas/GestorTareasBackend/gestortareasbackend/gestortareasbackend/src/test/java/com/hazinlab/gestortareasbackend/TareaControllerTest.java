package com.hazinlab.gestortareasbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.hazinlab.gestortareasbackend.controller.TareaController;
import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.model.TareaDTO;
import com.hazinlab.gestortareasbackend.service.TareaService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TareaControllerTest {

  @Mock
  private TareaService tareaService; // Se crea un mock de TareaService.

  @InjectMocks
  private TareaController tareaController; // Se inyecta el mock en TareaController.

  private Tarea tarea; // Se define un objeto Tarea para usar en las pruebas.

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this); // Inicializa los mocks.
    tarea = new Tarea("tarea prueba", "Tarea de prueba", false); // Crea una nueva tarea de prueba.
    tarea.setId("1"); // Establece un ID para la tarea.
  }

  @Test
  public void testObtenerTareas() {
    // Simula que el servicio devuelve una lista con la tarea de prueba.
    when(tareaService.obtenerTareas()).thenReturn(Arrays.asList(tarea));

    // Llama al método del controlador que se está probando.
    List<Tarea> tareas = tareaController.obtenerTareas();

    // Verifica que la lista devuelta tenga el tamaño correcto y la descripción esperada.
    assertEquals(1, tareas.size()); // Comprueba que hay 1 tarea.
    assertEquals("Tarea de prueba", tareas.get(0).getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testAgregarTarea() {
    // Simula que el servicio agrega una tarea y devuelve la tarea de prueba.
    when(tareaService.agregarTarea(any(Tarea.class))).thenReturn(tarea);

    // Llama al método del controlador que se está probando.
    Tarea nuevaTarea = tareaController.agregarTarea(tarea);

    // Verifica que la tarea agregada no sea nula y que tenga la descripción correcta.
    assertNotNull(nuevaTarea); // Asegura que la nueva tarea no es nula.
    assertEquals("Tarea de prueba", nuevaTarea.getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testEliminarTarea() {
    // Simula que el servicio elimina una tarea sin lanzar excepciones.
    doNothing().when(tareaService).eliminarTarea(tarea.getId());

    // Llama al método del controlador que se está probando.
    tareaController.eliminarTarea(tarea.getId());

    // Verifica que el método del servicio fue llamado exactamente una vez.
    verify(tareaService, times(1)).eliminarTarea(tarea.getId());
  }

  @Test
  public void testMarcarCompletada() {
    // Marca la tarea como completada.
    tarea.setCompletada(true);
    // Simula que el servicio devuelve la tarea completada.
    when(tareaService.marcarCompletada(tarea.getId())).thenReturn(tarea);

    // Llama al método del controlador que se está probando.
    Tarea tareaCompletada = tareaController.marcarCompletada(tarea.getId());

    // Verifica que la tarea esté marcada como completada.
    assertTrue(
      tareaCompletada.isCompletada(),
      "La tarea debería estar marcada como completada"
    );

    // Verifica que el método del servicio fue llamado exactamente una vez.
    verify(tareaService, times(1)).marcarCompletada(tarea.getId());
  }

  @Test
  public void testAgregarTareaConNombreVacio() {
    // Configura una tarea con nombre vacío.
    tarea.setNombre("");

    // Simula que el servicio devuelve null debido a un nombre inválido.
    when(tareaService.agregarTarea(any(Tarea.class))).thenReturn(null);

    // Llama al método del controlador que se está probando.
    Tarea nuevaTarea = tareaController.agregarTarea(tarea);

    // Verifica que la tarea no fue creada.
    assertNull(nuevaTarea, "La tarea no debería crearse con un nombre vacío");
  }

  @Test
  public void testActualizarTarea() {
    // Establece una tarea existente con ID.
    tarea.setId("1");

    // Crea un TareaDTO con los nuevos datos.
    TareaDTO tareaDTO = new TareaDTO();
    tareaDTO.setNombre("Nuevo Nombre");
    tareaDTO.setDescripcion("Nueva Descripción");

    // Simula que el servicio encuentra la tarea existente y la actualiza.
    when(
      tareaService.actualizarTarea(eq(tarea.getId()), anyString(), anyString())
    )
      .thenAnswer(invocation -> {
        // Obtener los argumentos de la invocación.
        String nombre = invocation.getArgument(1);
        String descripcion = invocation.getArgument(2);
        // Actualiza la tarea existente con los nuevos valores.
        tarea.setNombre(nombre);
        tarea.setDescripcion(descripcion);
        return tarea; // Devuelve la tarea actualizada.
      });

    // Llama al método del controlador que se está probando.
    Tarea resultado = tareaController.actualizarTarea(tarea.getId(), tareaDTO);

    // Verifica que la tarea tenga el nombre y la descripción actualizados.
    assertEquals(
      "Nuevo Nombre",
      resultado.getNombre(),
      "El nombre debería ser actualizado"
    );
    assertEquals(
      "Nueva Descripción",
      resultado.getDescripcion(),
      "La descripción debería ser actualizada"
    );

    // Verifica que el método del servicio fue llamado exactamente una vez.
    verify(tareaService, times(1))
      .actualizarTarea(
        tarea.getId(),
        tareaDTO.getNombre(),
        tareaDTO.getDescripcion()
      );
  }

  @Test
  public void testAgregarTareaDesdeDTO() {
    // Crea un TareaDTO con datos de prueba.
    TareaDTO tareaDTO = new TareaDTO();
    tareaDTO.setNombre("Tarea desde DTO");
    tareaDTO.setDescripcion("Descripción de la tarea desde DTO");

    // Simula que el servicio agrega la tarea y devuelve la tarea creada.
    Tarea nuevaTarea = new Tarea(
      tareaDTO.getNombre(),
      tareaDTO.getDescripcion(),
      false
    );
    when(tareaService.agregarTarea(any(Tarea.class))).thenReturn(nuevaTarea);

    // Llama al método del controlador que se está probando.
    Tarea resultado = tareaController.agregarTarea(nuevaTarea);

    // Verifica que la nueva tarea no sea nula y tenga los datos correctos.
    assertNotNull(resultado);
    assertEquals(
      "Tarea desde DTO",
      resultado.getNombre(),
      "El nombre debe coincidir con el TareaDTO"
    );
    assertEquals(
      "Descripción de la tarea desde DTO",
      resultado.getDescripcion(),
      "La descripción debe coincidir con el TareaDTO"
    );
  }

  @Test
  public void testEliminarTareaNoExistente() {
    // Simula que el servicio lanza una excepción al intentar eliminar una tarea no existente.
    doThrow(new RuntimeException("Tarea no encontrada"))
      .when(tareaService)
      .eliminarTarea("999");

    // Verifica que al intentar eliminar una tarea no existente se lance la excepción.
    Exception exception = assertThrows(
      RuntimeException.class,
      () -> {
        tareaController.eliminarTarea("999");
      }
    );

    // Comprueba que el mensaje de la excepción es el esperado.
    assertEquals("Tarea no encontrada", exception.getMessage());
  }

  @Test
  public void testMarcarCompletadaNoExistente() {
    // Simula que el servicio lanza una excepción al intentar marcar como completada una tarea no existente.
    doThrow(new RuntimeException("Tarea no encontrada"))
      .when(tareaService)
      .marcarCompletada("999");

    // Verifica que al intentar marcar como completada una tarea no existente se lance la excepción.
    Exception exception = assertThrows(
      RuntimeException.class,
      () -> {
        tareaController.marcarCompletada("999");
      }
    );

    // Comprueba que el mensaje de la excepción es el esperado.
    assertEquals("Tarea no encontrada", exception.getMessage());
  }
}

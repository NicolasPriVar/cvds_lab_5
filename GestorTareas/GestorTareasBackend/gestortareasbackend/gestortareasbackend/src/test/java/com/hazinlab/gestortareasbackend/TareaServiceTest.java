package com.hazinlab.gestortareasbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.repository.TareaRepository;
import com.hazinlab.gestortareasbackend.service.TareaService;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TareaServiceTest {

  @Mock
  private TareaRepository tareaRepository; // Se crea un mock de TareaRepository.

  @InjectMocks
  private TareaService tareaService; // Se inyecta el mock en TareaService.

  private Tarea tarea; // Se define un objeto Tarea para usar en las pruebas.

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this); // Inicializa los mocks.
    tarea = new Tarea("Tarea de prueba", "Tarea de prueba", false); // Crea una nueva tarea de prueba.
    tarea.setId("1"); // Establece un ID para la tarea.
  }

  @Test
  public void testObtenerTareas() {
    // Simula que el repositorio devuelve una lista con la tarea de prueba.
    when(tareaRepository.findAll()).thenReturn(Arrays.asList(tarea));

    // Llama al método del servicio que se está probando.
    List<Tarea> tareas = tareaService.obtenerTareas();

    // Verifica que la lista devuelta tenga el tamaño correcto y la descripción esperada.
    assertEquals(1, tareas.size()); // Comprueba que hay 1 tarea.
    assertEquals("Tarea de prueba", tareas.get(0).getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testAgregarTarea() {
    // Simula que el repositorio guarda una tarea y devuelve la tarea de prueba.
    when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

    // Llama al método del servicio que se está probando.
    Tarea nuevaTarea = tareaService.agregarTarea(tarea);

    // Verifica que la tarea agregada no sea nula y que tenga la descripción correcta.
    assertNotNull(nuevaTarea); // Asegura que la nueva tarea no es nula.
    assertEquals("Tarea de prueba", nuevaTarea.getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testEliminarTarea() {
    // Simula que el repositorio elimina una tarea sin lanzar excepciones.
    doNothing().when(tareaRepository).deleteById(tarea.getId());

    // Llama al método del servicio que se está probando.
    tareaService.eliminarTarea(tarea.getId());

    // Verifica que el método del repositorio fue llamado exactamente una vez.
    verify(tareaRepository, times(1)).deleteById(tarea.getId());
  }

  @Test
  public void testActualizarTarea() {
    // Simula que el repositorio actualiza una tarea y devuelve la tarea de prueba.
    when(tareaRepository.findById(tarea.getId()))
      .thenReturn(java.util.Optional.of(tarea));
    when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

    // Llama al método del servicio que se está probando.
    Tarea tareaActualizada = tareaService.actualizarTarea(
      tarea.getId(),
      "Tarea actualizada",
      "Tarea actualizada"
    );

    // Verifica que la tarea actualizada no sea nula y que tenga la descripción correcta.
    assertNotNull(tareaActualizada); // Asegura que la tarea actualizada no es nula.
    assertEquals("Tarea actualizada", tareaActualizada.getDescripcion()); // Comprueba la descripción.
  }

  @Test
  public void testActualizarTareaNoEncontrada() {
    // Simula que el repositorio no encuentra la tarea con el ID dado.
    when(tareaRepository.findById("inexistente"))
      .thenReturn(java.util.Optional.empty());

    // Verifica que se lance una excepción al intentar actualizar la tarea.
    assertThrows(
      NoSuchElementException.class,
      () -> {
        tareaService.actualizarTarea("inexistente", "Nombre", "Descripción");
      }
    );
  }
}

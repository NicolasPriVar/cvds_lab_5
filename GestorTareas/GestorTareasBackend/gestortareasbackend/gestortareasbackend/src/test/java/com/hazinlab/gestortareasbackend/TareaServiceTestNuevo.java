package com.hazinlab.gestortareasbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.repository.TareaRepository;
import com.hazinlab.gestortareasbackend.service.TareaService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TareaServiceTestNuevo {

  @Mock
  private TareaRepository tareaRepository;

  @InjectMocks
  private TareaService tareaService;

  private Tarea tarea;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    tarea = new Tarea("Tarea de prueba", "Descripción de tarea", false);
    tarea.setId("1");
  }

  @Test
  public void testConsultaExitosaConTareaRegistrada() {
    // Simula que el repositorio devuelve una lista con la tarea de prueba.
    when(tareaRepository.findAll())
      .thenReturn(Collections.singletonList(tarea));

    // Llama al método del servicio que se está probando.
    List<Tarea> tareas = tareaService.obtenerTareas();

    // Verifica que la lista devuelta tenga el tamaño correcto y el ID esperado.
    assertEquals(1, tareas.size());
    assertEquals("1", tareas.get(0).getId());
  }

  @Test
  public void testConsultaSinTareasRegistradas() {
    // Simula que el repositorio no devuelve ninguna tarea.
    when(tareaRepository.findAll()).thenReturn(Collections.emptyList());

    // Llama al método del servicio que se está probando.
    List<Tarea> tareas = tareaService.obtenerTareas();

    // Verifica que la lista devuelta esté vacía.
    assertTrue(tareas.isEmpty());
  }

  @Test
  public void testCreacionExitosaSinTareasRegistradas() {
    // Simula que el repositorio guarda una nueva tarea y devuelve la tarea de prueba.
    when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);

    // Llama al método del servicio que se está probando.
    Tarea nuevaTarea = tareaService.agregarTarea(tarea);

    // Verifica que la tarea creada no sea nula y que tenga el ID correcto.
    assertNotNull(nuevaTarea);
    assertEquals("1", nuevaTarea.getId());
  }

  @Test
  public void testEliminacionExitosaConTareaRegistrada() {
    // Simula que el repositorio elimina una tarea sin lanzar excepciones.
    doNothing().when(tareaRepository).deleteById(tarea.getId());

    // Llama al método del servicio que se está probando.
    tareaService.eliminarTarea(tarea.getId());

    // Verifica que el método del repositorio fue llamado exactamente una vez.
    verify(tareaRepository, times(1)).deleteById(tarea.getId());
  }

  @Test
  public void testEliminacionYConsultaSinTareas() {
    // Simula que el repositorio guarda una tarea y luego la elimina.
    when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);
    tareaService.agregarTarea(tarea);
    doNothing().when(tareaRepository).deleteById(tarea.getId());

    // Elimina la tarea.
    tareaService.eliminarTarea(tarea.getId());

    // Consulta las tareas y verifica que no hay ninguna.
    List<Tarea> tareas = tareaService.obtenerTareas();
    assertTrue(tareas.isEmpty());
  }
}

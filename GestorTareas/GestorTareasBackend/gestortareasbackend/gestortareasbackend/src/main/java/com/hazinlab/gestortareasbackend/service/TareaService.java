package com.hazinlab.gestortareasbackend.service;

import com.hazinlab.gestortareasbackend.model.Tarea;
import com.hazinlab.gestortareasbackend.repository.TareaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona las operaciones relacionadas con la entidad Tarea.
 * Proporciona métodos para obtener, agregar, eliminar y marcar tareas como completadas.
 */
@Service
public class TareaService {

  @Autowired
  private TareaRepository tareaRepository;

  /**
   * Obtiene la lista de todas las tareas almacenadas en la base de datos.
   *
   * @return Una lista de objetos Tarea.
   */
  public List<Tarea> obtenerTareas() {
    return tareaRepository.findAll();
  }

  /**
   * Agrega una nueva tarea a la base de datos.
   *
   * @param tarea La tarea a agregar.
   * @return La tarea agregada, incluyendo su ID asignado.
   */
  public Tarea agregarTarea(Tarea tarea) {
    System.out.println(
      "Tarea recibida: " + tarea.getNombre() + ", " + tarea.getDescripcion()
    ); // Logging para depuración

    return tareaRepository.save(tarea);
  }

  /**
   *
   * @param id
   */
  public Tarea actualizarTarea(String id, String nombre, String descripcion) {
    System.err.println(
      "Tarea recibida: " + id + ", " + nombre + ", " + descripcion
    );
    Tarea tarea = tareaRepository.findById(id).orElseThrow();
    tarea.actualizarTarea(nombre, descripcion);
    return tareaRepository.save(tarea);
  }

  /**
   * Elimina una tarea de la base de datos por su ID.
   *
   * @param id El ID de la tarea a eliminar.
   */
  public void eliminarTarea(String id) {
    tareaRepository.deleteById(id);
  }

  /**
   * Marca una tarea como completada.
   *
   * @param id El ID de la tarea a marcar como completada.
   * @return La tarea actualizada, ahora marcada como completada.
   * @throws NoSuchElementException Si no se encuentra la tarea con el ID especificado.
   */
  public Tarea marcarCompletada(String id) {
    System.err.println("Tarea recibida: " + id);
    Tarea tarea = tareaRepository.findById(id).orElseThrow();
    tarea.setCompletada(true);
    return tareaRepository.save(tarea);
  }
}

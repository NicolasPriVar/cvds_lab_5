package com.hazinlab.gestortareasbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa una tarea en el sistema.
 * La clase Tarea se utiliza para almacenar la información de una tarea,
 * incluyendo su descripción, estado de completada y un identificador único.
 */
@Document(collection = "tareas")
public class Tarea {

  @Id
  private String id;

  private String nombre;
  private String descripcion;
  private boolean completada;

  /**
   * Constructor vacío (necesario para deserialización).
   */
  public Tarea() {}

  /**
   * Constructor con parámetros.
   *
   * @param descripcion la descripción de la tarea.
   * @param completada el estado de la tarea (completada o no).
   */
  public Tarea(String nombre, String descripcion, boolean completada) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.completada = completada;
  }

  public void actualizarTarea(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  /**
   * Obtiene el ID de la tarea.
   *
   * @return el ID de la tarea.
   */
  public String getId() {
    return id;
  }

  /**
   * Establece el ID de la tarea.
   *
   * @param id el nuevo ID de la tarea.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Obtiene la descripción de la tarea.
   *
   * @return la descripción de la tarea.
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Obtiene el nombre de la tarea.
   *
   * @return el nombre de la tarea.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Establece el nombre de la tarea.
   *
   * @param nombre el nuevo nombre de la tarea.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Establece la descripción de la tarea.
   *
   * @param descripcion la nueva descripción de la tarea.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Verifica si la tarea está completada.
   *
   * @return true si la tarea está completada, false en caso contrario.
   */
  public boolean isCompletada() {
    return completada;
  }

  /**
   * Establece el estado de la tarea como completada o no.
   *
   * @param completada el nuevo estado de la tarea.
   */
  public void setCompletada(boolean completada) {
    this.completada = completada;
  }
}

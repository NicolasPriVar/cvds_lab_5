package com.hazinlab.gestortareasbackend.model;

/**
 * La clase TareaDTO representa un objeto de transferencia de datos (DTO) que
 * se utiliza para manejar la información sobre una tarea en la aplicación.
 * Se creó para facilitar la comunicación entre la capa de presentación y la
 * capa de servicio, permitiendo que los datos sean transferidos de manera
 * estructurada y simplificada.
 *
 * Este DTO encapsula los atributos esenciales de una tarea, que son el
 * nombre y la descripción, asegurando que se envíen y reciban de manera
 * consistente en las solicitudes y respuestas de la API.
 */
public class TareaDTO {

  private String nombre; // Nombre de la tarea
  private String descripcion; // Descripción de la tarea

  // Getters y Setters

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
   * @param nombre el nombre a establecer para la tarea.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
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
   * Establece la descripción de la tarea.
   *
   * @param descripcion la descripción a establecer para la tarea.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}

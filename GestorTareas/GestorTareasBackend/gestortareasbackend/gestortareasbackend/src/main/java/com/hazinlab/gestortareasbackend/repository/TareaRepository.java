package com.hazinlab.gestortareasbackend.repository;

import com.hazinlab.gestortareasbackend.model.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interfaz que define el repositorio para la entidad Tarea.
 * Esta interfaz hereda de MongoRepository, lo que proporciona métodos
 * CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad Tarea.
 */
public interface TareaRepository extends MongoRepository<Tarea, String> {
  // No se requieren métodos adicionales, ya que MongoRepository proporciona
  // la funcionalidad necesaria para manejar tareas en la base de datos MongoDB.
}

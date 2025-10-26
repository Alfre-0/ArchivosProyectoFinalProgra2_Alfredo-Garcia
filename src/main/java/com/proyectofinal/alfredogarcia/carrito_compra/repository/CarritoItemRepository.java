package com.proyectofinal.alfredogarcia.carrito_compra.repository;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para operaciones CRUD sobre la entidad CarritoItem.
 */
@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

    /**
     * Devuelve los ítems del carrito de un usuario específico.
     */
    List<CarritoItem> findByCodUsuario(String codUsuario);
}

package com.proyectofinal.alfredogarcia.carrito_compra.repository;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para operaciones CRUD sobre la entidad Producto.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

//Para método heredado finAll

}

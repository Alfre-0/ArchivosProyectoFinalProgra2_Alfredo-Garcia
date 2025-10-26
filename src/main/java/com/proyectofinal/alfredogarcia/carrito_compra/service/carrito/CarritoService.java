package com.proyectofinal.alfredogarcia.carrito_compra.service.carrito;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import java.util.List;

/**
 * Define las operaciones de negocio para manejar el carrito de compras.
 */
public interface CarritoService {

    /**
     * Agrega un producto al carrito de un usuario.
     */
    CarritoItem agregarProducto(String codUsuario, Long idProducto, int cantidad);

    /**
     * Elimina un ítem del carrito por su id.
     */
    void eliminarItem(Long idShoppingCart);

    /**
     * Lista todos los ítems del carrito de un usuario.
     */
    List<CarritoItem> listarPorUsuario(String codUsuario);

    /**
     * Vacía completamente el carrito de un usuario.
     */
    void vaciarCarrito(String codUsuario);
}

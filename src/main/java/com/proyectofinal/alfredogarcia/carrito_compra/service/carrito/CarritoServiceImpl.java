package com.proyectofinal.alfredogarcia.carrito_compra.service.carrito;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.repository.CarritoItemRepository;
import com.proyectofinal.alfredogarcia.carrito_compra.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementa la lógica de negocio del carrito de compras.
 */
@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoItemRepository carritoItemRepository;
    private final ProductoRepository productoRepository;

    public CarritoServiceImpl(CarritoItemRepository carritoItemRepository, ProductoRepository productoRepository) {
        this.carritoItemRepository = carritoItemRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public CarritoItem agregarProducto(String codUsuario, Long idProducto, int cantidad) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + idProducto));

        CarritoItem item = new CarritoItem();
        item.setProducto(producto);
        item.setCodUsuario(codUsuario);
        item.setCantidad(cantidad);

        return carritoItemRepository.save(item);
    }

    @Override
    public void eliminarItem(Long idShoppingCart) {
        carritoItemRepository.deleteById(idShoppingCart);
    }

    @Override
    public List<CarritoItem> listarPorUsuario(String codUsuario) {
        return carritoItemRepository.findByCodUsuario(codUsuario);
    }

    @Override
    public void vaciarCarrito(String codUsuario) {
        List<CarritoItem> items = carritoItemRepository.findByCodUsuario(codUsuario);
        carritoItemRepository.deleteAll(items);
    }
}


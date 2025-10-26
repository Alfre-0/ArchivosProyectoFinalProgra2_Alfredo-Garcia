package com.proyectofinal.alfredogarcia.carrito_compra.service.producto;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
}



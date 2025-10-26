package com.proyectofinal.alfredogarcia.carrito_compra.controller;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.service.producto.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }
}


package com.proyectofinal.alfredogarcia.carrito_compra.controller;

import com.proyectofinal.alfredogarcia.carrito_compra.service.producto.ProductoService;
import com.proyectofinal.alfredogarcia.carrito_compra.service.carrito.CarritoService;
import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProductoService productoService;
    private final CarritoService carritoService;

    public HomeController(ProductoService productoService, CarritoService carritoService) {
        this.productoService = productoService;
        this.carritoService = carritoService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Inicio");
        model.addAttribute("content", "productos"); // reutilizamos la vista de productos como home
        model.addAttribute("productos", productoService.listarProductos());
        return "layout";
    }

    @GetMapping("/productos")
    public String productos(Model model) {
        model.addAttribute("pageTitle", "Productos");
        model.addAttribute("content", "productos");
        model.addAttribute("productos", productoService.listarProductos());
        return "layout";
    }

    @GetMapping("/carrito")
    public String carrito(Model model) {
        final String codUsuario = "demo"; // <- placeholder
        List<CarritoItem> items = carritoService.listarPorUsuario(codUsuario);

        double total = items.stream()
                .mapToDouble(i -> {
                    Double precio = i.getProducto() != null ? i.getProducto().getPrecio() : 0.0;
                    Integer cant = i.getCantidad() != null ? i.getCantidad() : 0;
                    return precio * cant;
                })
                .sum();

        int totalItems = items.stream()
                .mapToInt(i -> i.getCantidad() != null ? i.getCantidad() : 0)
                .sum();

        model.addAttribute("pageTitle", "Carrito");
        model.addAttribute("content", "carrito");
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("codUsuario", codUsuario);

        return "layout";
    }
}


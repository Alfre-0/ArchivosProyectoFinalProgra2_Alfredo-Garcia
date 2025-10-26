package com.proyectofinal.alfredogarcia.carrito_compra.controller;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.service.producto.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void listar_debeRetornarJson200() throws Exception {
        when(productoService.listarProductos())
                .thenReturn(List.of(
                        new Producto(1L,"Cam","3DcAM01","/img/camera.jpg", 100.0)
                ));

        mockMvc.perform(get("/api/productos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idProducto").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Cam"));
    }
}


package com.proyectofinal.alfredogarcia.carrito_compra.controller;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.service.carrito.CarritoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CarritoController.class)
class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @Test
    void agregarProducto_debeRetornar201() throws Exception {
        CarritoItem ci = new CarritoItem();
        ci.setIdShoppingCart(10L);
        ci.setCodUsuario("demo");
        ci.setCantidad(1);
        ci.setProducto(new Producto(1L,"Cam","3DcAM01","/img/camera.jpg",100.0));

        when(carritoService.agregarProducto(anyString(), anyLong(), anyInt())).thenReturn(ci);

        mockMvc.perform(post("/api/carrito/agregar")
                        .param("codUsuario", "demo")
                        .param("idProducto", "1")
                        .param("cantidad", "1"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idShoppingCart").value(10))
            .andExpect(jsonPath("$.producto.idProducto").value(1));
    }

    @Test
    void eliminarItem_debeRetornar204() throws Exception {
        mockMvc.perform(delete("/api/carrito/item/{id}", 99))
            .andExpect(status().isNoContent());
    }

    @Test
    void vaciarCarrito_debeRetornar204() throws Exception {
        mockMvc.perform(delete("/api/carrito/vaciar/{user}", "demo"))
            .andExpect(status().isNoContent());
    }
}

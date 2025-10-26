package com.proyectofinal.alfredogarcia.carrito_compra.service.carrito;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.repository.CarritoItemRepository;
import com.proyectofinal.alfredogarcia.carrito_compra.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarritoServiceImplTest {

    @Test
    void agregarProducto_debeCrearYGuardarItem() {
        CarritoItemRepository repoItems = mock(CarritoItemRepository.class);
        ProductoRepository repoProd = mock(ProductoRepository.class);
        CarritoServiceImpl service = new CarritoServiceImpl(repoItems, repoProd);

        when(repoProd.findById(1L)).thenReturn(Optional.of(
                new Producto(1L,"Cam","3DcAM01","/img/camera.jpg", 100.0)
        ));
        when(repoItems.save(any(CarritoItem.class))).thenAnswer(inv -> inv.getArgument(0));

        CarritoItem saved = service.agregarProducto("demo", 1L, 2);

        assertThat(saved.getCantidad()).isEqualTo(2);
        assertThat(saved.getCodUsuario()).isEqualTo("demo");
        assertThat(saved.getProducto()).isNotNull();

        ArgumentCaptor<CarritoItem> cap = ArgumentCaptor.forClass(CarritoItem.class);
        verify(repoItems).save(cap.capture());
        assertThat(cap.getValue().getProducto().getIdProducto()).isEqualTo(1L);
    }

    @Test
    void listarPorUsuario_debeRetornarItemsDelUsuario() {
        CarritoItemRepository repoItems = mock(CarritoItemRepository.class);
        ProductoRepository repoProd = mock(ProductoRepository.class);
        CarritoServiceImpl service = new CarritoServiceImpl(repoItems, repoProd);

        when(repoItems.findByCodUsuario("demo")).thenReturn(List.of(new CarritoItem(), new CarritoItem()));

        assertThat(service.listarPorUsuario("demo")).hasSize(2);
        verify(repoItems).findByCodUsuario("demo");
    }

    @Test
    void vaciarCarrito_debeEliminarTodos() {
        CarritoItemRepository repoItems = mock(CarritoItemRepository.class);
        ProductoRepository repoProd = mock(ProductoRepository.class);
        CarritoServiceImpl service = new CarritoServiceImpl(repoItems, repoProd);

        when(repoItems.findByCodUsuario("demo")).thenReturn(List.of(new CarritoItem(), new CarritoItem()));
        service.vaciarCarrito("demo");

        verify(repoItems, times(1)).deleteAll(anyList());
    }
}


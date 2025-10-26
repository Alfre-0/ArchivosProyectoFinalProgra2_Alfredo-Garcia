package com.proyectofinal.alfredogarcia.carrito_compra.service.producto;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import com.proyectofinal.alfredogarcia.carrito_compra.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductoServiceImplTest {

    @Test
    void listarProductos_debeRetornarLista() {
        // arrange
        ProductoRepository repo = Mockito.mock(ProductoRepository.class);
        ProductoServiceImpl service = new ProductoServiceImpl(repo);

        when(repo.findAll()).thenReturn(List.of(
                new Producto(1L,"Cam","3DcAM01","/img/camera.jpg", 100.0),
                new Producto(2L,"Lap","LPN45","/img/laptop.jpg", 200.0)
        ));

        // act
        List<Producto> out = service.listarProductos();

        // assert
        assertThat(out).hasSize(2);
        verify(repo, times(1)).findAll();
    }
}

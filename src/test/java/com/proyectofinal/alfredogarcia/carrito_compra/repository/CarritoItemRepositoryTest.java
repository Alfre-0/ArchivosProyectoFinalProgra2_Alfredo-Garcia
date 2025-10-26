package com.proyectofinal.alfredogarcia.carrito_compra.repository;

import com.proyectofinal.alfredogarcia.carrito_compra.entity.CarritoItem;
import com.proyectofinal.alfredogarcia.carrito_compra.entity.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EntityScan(basePackages = "com.proyectofinal.alfredogarcia.carrito_compra.entity")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl",
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password="
})
@Sql(statements = {
        "CREATE TABLE IF NOT EXISTS producto (" +
        "  idProducto BIGINT AUTO_INCREMENT PRIMARY KEY," +
        "  nombre VARCHAR(255)," +
        "  codigo VARCHAR(255) NOT NULL," +
        "  imagen VARCHAR(255)," +
        "  precio DOUBLE" +
        ");",
        "CREATE TABLE IF NOT EXISTS shopping_cart (" +
        "  idShoppingCart BIGINT AUTO_INCREMENT PRIMARY KEY," +
        "  idProducto BIGINT," +
        "  codUsuario VARCHAR(128)," +
        "  cantidad INT," +
        "  CONSTRAINT fk_prod FOREIGN KEY (idProducto) REFERENCES producto(idProducto)" +
        ");"
})
class CarritoItemRepositoryTest {

    @Autowired
    private CarritoItemRepository repoItems;

    @Autowired
    private ProductoRepository repoProd;

    @Test
    void save_y_findByCodUsuario_debeRetornarItemsDelUsuario() {
        // Producto base
        Producto p = new Producto();
        p.setNombre("Cam");
        p.setCodigo("3DcAM01");
        p.setImagen("/img/camera.jpg");
        p.setPrecio(100.0);
        p = repoProd.save(p);

        // Guarda el id en una variable efectivamente final para la lambda
        final Long prodId = p.getIdProducto();

        // Dos ítems para "demo"
        CarritoItem i1 = new CarritoItem();
        i1.setProducto(p);
        i1.setCodUsuario("demo");
        i1.setCantidad(2);
        repoItems.save(i1);

        CarritoItem i2 = new CarritoItem();
        i2.setProducto(p);
        i2.setCodUsuario("demo");
        i2.setCantidad(1);
        repoItems.save(i2);

        List<CarritoItem> items = repoItems.findByCodUsuario("demo");
        assertThat(items).hasSize(2);
        assertThat(items).allSatisfy(it -> {
            assertThat(it.getProducto()).isNotNull();
            assertThat(it.getProducto().getIdProducto()).isEqualTo(prodId);
        });
    }

    @Test
    void deleteAll_porUsuario_debeVaciar() {
        Producto p = new Producto();
        p.setNombre("Lap");
        p.setCodigo("LPN45");
        p.setImagen("/img/laptop.jpg");
        p.setPrecio(200.0);
        p = repoProd.save(p);

        CarritoItem a = new CarritoItem();
        a.setProducto(p); a.setCodUsuario("demo"); a.setCantidad(1);
        CarritoItem b = new CarritoItem();
        b.setProducto(p); b.setCodUsuario("demo"); b.setCantidad(3);
        repoItems.save(a);
        repoItems.save(b);

        List<CarritoItem> antes = repoItems.findByCodUsuario("demo");
        repoItems.deleteAll(antes);

        List<CarritoItem> despues = repoItems.findByCodUsuario("demo");
        assertThat(despues).isEmpty();
    }

    @Test
    void deleteById_debeEliminarUno() {
        Producto p = new Producto();
        p.setNombre("Watch");
        p.setCodigo("wristWear03");
        p.setImagen("/img/watch.jpg");
        p.setPrecio(300.0);
        p = repoProd.save(p);

        CarritoItem it = new CarritoItem();
        it.setProducto(p);
        it.setCodUsuario("demo");
        it.setCantidad(5);
        it = repoItems.save(it);
        final Long id = it.getIdShoppingCart();

        repoItems.deleteById(id);

        List<CarritoItem> rest = repoItems.findByCodUsuario("demo");
        assertThat(rest.stream().noneMatch(x -> id.equals(x.getIdShoppingCart()))).isTrue();
    }
}



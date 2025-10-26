package com.proyectofinal.alfredogarcia.carrito_compra.repository;

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
@EntityScan(basePackages = "com.proyectofinal.alfredogarcia.carrito_compra.entity") // solo afecta a esta clase
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)        // fuerza H2 embebida
@ActiveProfiles("test")
@TestPropertySource(properties = {
        // Desactivamos DDL de Hibernate para evitar conflictos: la tabla la creamos nosotros
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl",
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password="
})
// Creación EXACTAMENTE con los nombres que usa la entidad (@Table(name="producto"), idProducto, etc.)
@Sql(statements = {
        "CREATE TABLE IF NOT EXISTS producto (" +
        "  idProducto BIGINT AUTO_INCREMENT PRIMARY KEY," +
        "  nombre VARCHAR(255)," +
        "  codigo VARCHAR(255) NOT NULL," +
        "  imagen VARCHAR(255)," +
        "  precio DOUBLE" +
        ")"
})
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository repo;

    @Test
    void save_y_findAll_debeFuncionar() {
        Producto p1 = new Producto();
        p1.setNombre("Cam");
        p1.setCodigo("3DcAM01");
        p1.setImagen("/img/camera.jpg");
        p1.setPrecio(100.0);
        repo.save(p1);

        Producto p2 = new Producto();
        p2.setNombre("Lap");
        p2.setCodigo("LPN45");
        p2.setImagen("/img/laptop.jpg");
        p2.setPrecio(200.0);
        repo.save(p2);

        List<Producto> all = repo.findAll();

        assertThat(all).hasSize(2);
        assertThat(all).allSatisfy(p -> assertThat(p.getIdProducto()).isNotNull());
        assertThat(all).extracting(Producto::getNombre)
                .containsExactlyInAnyOrder("Cam", "Lap");
    }
}


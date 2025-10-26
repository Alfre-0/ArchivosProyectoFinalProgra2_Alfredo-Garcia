package com.proyectofinal.alfredogarcia.carrito_compra.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa un ítem en el carrito de compras.
 * Cada registro está asociado a un producto y a un usuario.
 */
@Entity
@Table(name = "shopping_cart")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idShoppingCart")
    private Long idShoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @Column(name = "codUsuario", length = 128)
    private String codUsuario;

    @Column(name = "cantidad")
    private Integer cantidad;

    public CarritoItem() {
    }

    // Getters y setters
    public Long getIdShoppingCart() {
        return idShoppingCart;
    }

    public void setIdShoppingCart(Long idShoppingCart) {
        this.idShoppingCart = idShoppingCart;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CarritoItem{" +
                "idShoppingCart=" + idShoppingCart +
                ", productoId=" + (producto != null ? producto.getIdProducto() : null) +
                ", codUsuario='" + codUsuario + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}

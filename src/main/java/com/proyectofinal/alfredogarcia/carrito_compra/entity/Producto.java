package com.proyectofinal.alfredogarcia.carrito_compra.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "precio")
    private Double precio;

    // ----- Constructores -----
    public Producto() {}

    public Producto(Long idProducto, String nombre, String codigo, String imagen, Double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigo = codigo;
        this.imagen = imagen;
        this.precio = precio;
    }

    // ----- Getters y Setters -----
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio=" + precio +
                '}';
    }
}

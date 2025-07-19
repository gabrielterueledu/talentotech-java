package com.techlab.pedidos;

import com.techlab.productos.Producto;

import java.util.List;

public class Pedido {
    private int id;
    private List<Producto> productos;

    public Pedido(int id, List<Producto> productos) {
        this.id = id;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }
}

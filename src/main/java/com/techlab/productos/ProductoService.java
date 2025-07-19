package com.techlab.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techlab.excepciones.StockInsuficienteException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(int id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

    public void realizarPedido(List<Producto> pedidoProductos) throws StockInsuficienteException {
        for (Producto pedidoProducto : pedidoProductos) {
            Optional<Producto> productoOptional = productoRepository.findById(pedidoProducto.getId());
            if (productoOptional.isPresent()) {
                Producto almacenProducto = productoOptional.get();
                if (almacenProducto.getStock() < 1) {
                    throw new StockInsuficienteException("No hay stock suficiente para el producto: " + almacenProducto.getNombre());
                }
                almacenProducto.setStock(almacenProducto.getStock() - 1);
                productoRepository.save(almacenProducto);
            }
        }
    }
}

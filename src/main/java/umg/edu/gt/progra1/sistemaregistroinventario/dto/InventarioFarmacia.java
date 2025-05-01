package umg.edu.gt.progra1.sistemaregistroinventario.dto;

public class InventarioFarmacia {
    private ProductoDTO[] productos;
    private int contadorProductos;

    public InventarioFarmacia() {
        productos = new ProductoDTO[100];
        contadorProductos = 0;
    }

    public void agregarProducto(ProductoDTO producto) {
        if (contadorProductos < 100) {
            productos[contadorProductos] = producto;
            contadorProductos++;
        }
    }

    public ProductoDTO buscarProductoPorCodigo(String codigoBarras) {
        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i].getCodigoBarras().equals(codigoBarras)) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean venderProducto(String codigoBarras, int cantidad) {
        ProductoDTO producto = buscarProductoPorCodigo(codigoBarras);
        if (producto != null && producto.getCantidadStock() >= cantidad) {
            producto.setCantidadStock(producto.getCantidadStock() - cantidad);
            return true;
        }
        return false;
    }

    public boolean actualizarPrecio(String codigoBarras, double nuevoPrecio) {
        ProductoDTO producto = buscarProductoPorCodigo(codigoBarras);
        if (producto != null) {
            producto.setPrecioUnitario(nuevoPrecio);
            return true;
        }
        return false;
    }

    public ProductoDTO[] getProductos() { return productos; }
    public int getContadorProductos() { return contadorProductos; }
}







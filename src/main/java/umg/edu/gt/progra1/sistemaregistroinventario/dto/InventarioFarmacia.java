package umg.edu.gt.progra1.sistemaregistroinventario.dto;

public class InventarioFarmacia {

    private ProductoDTO[] productos;
    private int contadorProductos;

    //Constructor
    public InventarioFarmacia(){
        this.productos = new ProductoDTO[100];
        this.contadorProductos = 0;
    }


    //Metodo Agregar Producto
    public void agregarProducto(ProductoDTO producto){
        if(contadorProductos < 100){
            productos[contadorProductos] = producto;
            contadorProductos++;
            System.out.println("Producto agregado correctamente.");
        }else{
            System.out.println("El inventario esta lleno. No se puede agregar mas producto.");
        }
    }

    //Metodo para buscar producto por codigo de barras
    public ProductoDTO buscarProductoPorCodigo(String codigoBarras){
        for(int i = 0; i < contadorProductos; i++){
            if(productos[i].getCodigoBarras().equals(codigoBarras)){
                return productos[i];
            }
        }
        return null; //Aqui si para si no encuentra el producto
    }

    //Metodo para mostrar el Inventario
    public void mostrarInventario(){
        System.out.println("\n---INVENTARIO DE FARMACIA---");
        for(int i = 0; i < contadorProductos; i++){
            ProductoDTO p = productos[i];
            System.out.println("Codigo: " + p.getCodigoBarras() +
                             " | Nombre: " + p.getNombre() +
                             " | Precio: " + p.getPrecioUnitario() +
                             " | Stock: " + p.getCantidadStock());
        }
        System.out.println("Total de productos: " + contadorProductos + "\n");
    }

    //Metodo para Vender Producto
    public boolean venderProducto (String codigoBarras, int cantidadVendida){
        ProductoDTO producto = buscarProductoPorCodigo(codigoBarras);
        if (producto != null && producto.getCantidadStock() >= cantidadVendida){
            producto.setCantidadStock(producto.getCantidadStock()-cantidadVendida);
            return true; //Aqui supone que la venta se realizo
        }
        return false; //No encontro el producto o no hay suficiente stock
    }

    //Metodo para Actualizar el Precio
    public boolean actualizarPrecio(String codigoBarras, double nuevoPrecio){
        ProductoDTO producto = buscarProductoPorCodigo(codigoBarras);
        if (producto != null){
            producto.setPrecioUnitario(nuevoPrecio);
            return true;//Precio actualizado
        }
        return false; //No encontro el producto
    }







}
package umg.edu.gt.progra1.sistemaregistroinventario.dto;

import java.io.*;

import java.io.*;
import java.util.Arrays;

public class InventarioFarmacia {
    private static final int MAX = 100;
    private ProductoDTO[] productos;
    private int contadorProductos;

    public InventarioFarmacia() {
        productos = new ProductoDTO[MAX];
        contadorProductos = 0;
    }

    public void agregarProducto(ProductoDTO p) {
        if (contadorProductos < MAX) {
            productos[contadorProductos++] = p;
        }
    }

    public ProductoDTO buscarProductoPorCodigo(String codigo) {
        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i].getCodigoBarras().equalsIgnoreCase(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean venderProducto(String codigo, int cantidad) {
        ProductoDTO p = buscarProductoPorCodigo(codigo);
        if (p != null && p.getCantidadStock() >= cantidad) {
            p.setCantidadStock(p.getCantidadStock() - cantidad);
            return true;
        }
        return false;
    }

    public boolean actualizarPrecio(String codigo, double nuevoPrecio) {
        ProductoDTO p = buscarProductoPorCodigo(codigo);
        if (p != null) {
            p.setPrecioUnitario(nuevoPrecio);
            return true;
        }
        return false;
    }

    public ProductoDTO[] getProductos() {
        return Arrays.copyOf(productos, contadorProductos);
    }

    public void guardarInventarioEnArchivo(String InventarioFarmacia) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(InventarioFarmacia))) {
            for (int i = 0; i < contadorProductos; i++) {
                ProductoDTO p = productos[i];
                pw.printf("%s,%s,%.2f,%d%n",
                        p.getNombre(),
                        p.getCodigoBarras(),
                        p.getPrecioUnitario(),
                        p.getCantidadStock());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarInventarioDesdeArchivo(String InventarioFarmacia) {
        File f = new File(InventarioFarmacia);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            contadorProductos = 0;
            while ((linea = br.readLine()) != null && contadorProductos < MAX) {
                if (linea.isBlank() || linea.startsWith("#")) continue;
                String[] campos = linea.split(",");
                ProductoDTO p = new ProductoDTO(
                        campos[0].trim(),
                        campos[1].trim(),
                        Double.parseDouble(campos[2].trim()),
                        Integer.parseInt(campos[3].trim())
                );
                productos[contadorProductos++] = p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



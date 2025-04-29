package umg.edu.gt.progra1.sistemaregistroinventario;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.InventarioFarmacia;
import umg.edu.gt.progra1.sistemaregistroinventario.dto.ProductoDTO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InventarioFarmacia inventarioFarmacia = new InventarioFarmacia();

        while (true){
            System.out.println("\n=== Sistema de Inventario Farmacia ===");
            System.out.println("1. Agregar Producto.");
            System.out.println("2. Buscar Producto por Codigo.");
            System.out.println("3. Mostrar Inventario.");
            System.out.println("4. Vender Produto");
            System.out.println("5. Actualizar Precio.");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion.");

            int opcion = scanner.nextInt();
            scanner.nextLine(); //limpia el buffer.


            //Aun no llego a esta parte.
            /*switch (opcion) {
                case 1:
                    agregarProducto
            }*/


        }

    }






}

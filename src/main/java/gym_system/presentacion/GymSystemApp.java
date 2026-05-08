package gym_system.presentacion;

import gym_system.datos.ClienteDAO;
import gym_system.datos.IClienteDAO;
import gym_system.dominio.Cliente;

import java.util.Scanner;

public class GymSystemApp {
    public static void main(String[] args) {
        GymSystemApp();

    }

    private static void GymSystemApp(){
        var salir = false;
        var sc = new Scanner(System.in);

        //Objeto de la clase ClienteDAO
        IClienteDAO clienteDao = new ClienteDAO();
        while (!salir){
            try {
                var opcion = mostrarMenu(sc);
                salir = ejecutarOpciones(sc, opcion, clienteDao);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner sc){
        System.out.print("""
                *** Gym System APP ***
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente 
                6. Salir
                
                Elije una opcion: \s""");
        return Integer.parseInt(sc.nextLine());

    }

    private static boolean ejecutarOpciones(Scanner sc, int opcion,
                                            IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> { //Listar clientes
                System.out.println("--- Listado de clientes---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> { //Buscar cliente
                System.out.println("Introduce el ID del usuario que deseas buscar: ");
                var idCliente = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorID(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado: " + cliente);
                }
                else {
                    System.out.println("No se encontro el cliente " + cliente);
                }
            }
            case 3 -> { //Agregar cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Nombre: ");
                var nombre = sc.nextLine();
                System.out.print("Apellido: ");
                var apellido = sc.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(sc.nextLine());

                //Creamos el objeto cliente
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente agregado: " + cliente);
                }else {
                    System.out.println("Error, cliente NO agregado: " + cliente);
                }
            }
            case 4 -> { //Modificar cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.print("ID a modificar: ");
                var id = Integer.parseInt(sc.nextLine());
                System.out.print("Nombre: ");
                var nombre = sc.nextLine();
                System.out.print("Apellido: ");
                var apellido = sc.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(sc.nextLine());

                //Creamos el objeto a modificar
                var cliente = new Cliente(id, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if(modificado){
                    System.out.println("Cliente modificado: " + cliente);
                }else {
                    System.out.println("Error, NO se pudo modificar el cliente: " + cliente);
                }
            }
            case 5 -> { //Eliminar cliente
                System.out.println("--- Eliminar cliente ---");
                System.out.print("ID cliente: ");
                var idCliente = Integer.parseInt(sc.nextLine());
                var cliente = new Cliente(idCliente);
                var elimnado = clienteDAO.eleminarCliente(cliente);
                if(elimnado){
                    System.out.println("Cliente eliminado: " + cliente);
                }
                else {
                    System.out.println("No se pudo eliminar el cliente: " + cliente);
                }
            }
            case 6 -> { //Salir
                System.out.println("Hasta pronto...");
                salir = true;
            }
            default -> System.out.println("Opcion no valida: " + opcion);
        }
        return salir;
    }
}

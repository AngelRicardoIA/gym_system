package gym_system.datos;

import gym_system.conexion.Conexion;
import gym_system.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static gym_system.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar cliente: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorID(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al recuperar el cliente: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "INSERT INTO cliente(nombre, apellido, membresia)"
                + " VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println("Error al agregar el cliente: " + e.getMessage() );
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? " +
                " WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar el cliente: " + e.getMessage() );
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eleminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM cliente WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar el cliente: " + e.getMessage() );
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new  ClienteDAO();

        //Buscar cliente por ID
//        var cliente1 = new Cliente(2);
//        System.out.println(cliente1);
//        var encontrado = clienteDAO.buscarClientePorID(cliente1);
//        if (encontrado){
//            System.out.println("Cliente: "+ cliente1);
//        }else {
//            System.out.println("No se encontro: "+ cliente1.getId());
//        }

        //Agregar cliente
//        var nuevoCliente = new Cliente("Alejandro", "Aceves", 3400);
//        var agregado = clienteDAO.agregarCliente(nuevoCliente);
//        if (agregado){
//            System.out.println("Nuevo cliente: " + nuevoCliente);
//        }
//        else {
//            System.out.println("No se agrego el cliente: " + nuevoCliente);
//        }

        //Modificar
//        var modificarCliente = new Cliente(4, "Jorge Alejandro", "Vazquez", 2312);
//        var modificado = clienteDAO.modificarCliente(modificarCliente);
//        if (modificado){
//            System.out.println("Modificado: " +modificarCliente);
//        }
//        else {
//            System.out.println("No se pudo modificar: " + modificarCliente);
//        }

        //Eliminar clientes
//        var clienteEliminado = new Cliente(12);
//        var eliminado = clienteDAO.eleminarCliente(clienteEliminado);
//        if (eliminado){
//            System.out.println("Eliminado: " + clienteEliminado);
//        }
//        else {
//            System.out.println("No se pudo eliminar: " + clienteEliminado);
//        }
//
//        //Listar clientes
//        var clientes = clienteDAO.listarClientes();
//        clientes.forEach(System.out::println);

    }
}

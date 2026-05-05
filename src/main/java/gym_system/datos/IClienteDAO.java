package gym_system.datos;

import gym_system.dominio.Cliente;
import java.util.List;

public interface IClienteDAO {
    List<Cliente> listarClientes();
    boolean buscarClientePorID(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eleminarCliente(Cliente cliente);

}

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private List<Pedido> pedidos;

    public Pizzeria() {
        pedidos=new ArrayList<>();
    }

    public void agregarPedido(Pedido p){
        pedidos.add(p);
    }
    public void eliminarPedido(String nombreCliente){
        for(int i=0;i<pedidos.size();i++){
            Pedido p=pedidos.get(i);
            if(p.getNombreCliente().equals(nombreCliente)){
                pedidos.remove(i);
            }
        }
    }
    public void mostrarPedidos(){
        if(pedidos.isEmpty()){
            System.out.println("No hay pedidos");
            return;
        }
        System.out.println("Lista de pedidos:");
        pedidos.forEach(System.out::println);  //por cada pedido hace un print
    }

    public List<Pedido> getPedidos(){
        return pedidos;
    }
}

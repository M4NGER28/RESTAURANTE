package restaurante;
import java.util.ArrayList;
import java.util.List;
public class Cliente {
    private final int numeroMesa, comensales;
    private final List<Orden> ordenes = new ArrayList<>();
    public Cliente(int numeroMesa, int comensales) {
        if(numeroMesa<=0 || comensales<=0) throw new IllegalArgumentException("Mesa y comensales deben ser positivos");
        this.numeroMesa=numeroMesa; this.comensales=comensales;
    }
    public int getNumeroMesa(){return numeroMesa;}
    public int getComensales(){return comensales;}
    public void agregarOrden(Orden orden){if(orden.getCliente()!=this) throw new IllegalArgumentException("La orden corresponde a otro cliente");ordenes.add(orden);}
    public List<Orden> getOrdenes(){return java.util.Collections.unmodifiableList(ordenes);}
}

package restaurante;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Orden {
    private final Cliente cliente;
    private final String mesero;
    private final LocalDateTime fechaHora;
    private final List<DetalleOrden> detalles=new ArrayList<>();
    private Pago pago;
    public Orden(Cliente cliente,String mesero){
        if(cliente==null||mesero==null||mesero.trim().isEmpty())throw new IllegalArgumentException("Cliente y mesero obligatorios");
        this.cliente=cliente;this.mesero=mesero;this.fechaHora=LocalDateTime.now();
    }
    public void agregarDetalle(ItemMenu item,int cantidad){
        if(pago!=null)throw new IllegalStateException("La orden ya fue pagada");
        detalles.add(new DetalleOrden(item,cantidad));
    }
    public double calcularSubtotal(){return detalles.stream().mapToDouble(DetalleOrden::getSubtotal).sum();}
    public Cliente getCliente(){return cliente;}
    public String getMesero(){return mesero;}
    public LocalDateTime getFechaHora(){return fechaHora;}
    public List<DetalleOrden> getDetalles(){return java.util.Collections.unmodifiableList(detalles);}
    public Pago getPago(){return pago;}
    public void registrarPago(Pago pago){
        if(this.pago!=null||pago==null||detalles.isEmpty())throw new IllegalStateException("No se puede registrar el pago");
        if(Math.abs(pago.getSubtotal()-calcularSubtotal())>0.01)throw new IllegalArgumentException("Subtotal inconsistente");
        this.pago=pago;
    }
}

package restaurante;
public class DetalleOrden {
    private final ItemMenu item;
    private final int cantidad;
    public DetalleOrden(ItemMenu item,int cantidad){
        if(item==null||cantidad<=0) throw new IllegalArgumentException("Detalle invalido");
        this.item=item;this.cantidad=cantidad;
    }
    public ItemMenu getItem(){return item;}
    public int getCantidad(){return cantidad;}
    public double getSubtotal(){return item.getPrecio()*cantidad;}
}

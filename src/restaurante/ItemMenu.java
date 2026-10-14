package restaurante;
public abstract class ItemMenu {
    private final String nombre;
    private final double precio;
    protected ItemMenu(String nombre,double precio){
        if(nombre==null||nombre.trim().isEmpty()||precio<0||!Double.isFinite(precio)) throw new IllegalArgumentException("Item invalido");
        this.nombre=nombre;this.precio=precio;
    }
    public String getNombre(){return nombre;}
    public double getPrecio(){return precio;}
    public abstract String getTipo();
    @Override public String toString(){return nombre+" ("+getTipo()+") - $"+String.format("%,.0f",precio);}
}

package restaurante;
public class Bebida extends ItemMenu {
    public Bebida(String nombre,double precio){super(nombre,precio);}
    @Override public String getTipo(){return "Bebida";}
}

package restaurante;
public class Platillo extends ItemMenu {
    public Platillo(String nombre,double precio){super(nombre,precio);}
    @Override public String getTipo(){return "Platillo";}
}

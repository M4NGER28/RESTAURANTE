package restaurante;
public class Efectivo extends Pago {
    private final double entregado;
    public Efectivo(double subtotal,double propina,double impuestos,double entregado){
        super(subtotal,propina,impuestos);
        if(!Double.isFinite(entregado)||entregado<calcularTotal())throw new IllegalArgumentException("Efectivo insuficiente");
        this.entregado=entregado;
    }
    public double getEntregado(){return entregado;}
    public double getCambio(){return entregado-calcularTotal();}
    @Override public String getMetodo(){return "Efectivo";}
}

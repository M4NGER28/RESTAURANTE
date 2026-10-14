package restaurante;
public abstract class Pago {
    private final double subtotal,propina,impuestos;
    protected Pago(double subtotal,double propina,double impuestos){
        if(!Double.isFinite(subtotal)||!Double.isFinite(propina)||!Double.isFinite(impuestos)||subtotal<0||propina<0||impuestos<0)
            throw new IllegalArgumentException("Montos invalidos");
        this.subtotal=subtotal;this.propina=propina;this.impuestos=impuestos;
    }
    public double getSubtotal(){return subtotal;}
    public double getPropina(){return propina;}
    public double getImpuestos(){return impuestos;}
    public double calcularTotal(){return subtotal+propina+impuestos;}
    public abstract String getMetodo();
}

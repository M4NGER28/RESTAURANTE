package restaurante;
import java.time.YearMonth;
public class Credito extends Pago {
    private final String numeroTarjeta,tipo,titular;
    private final YearMonth expiracion;
    public Credito(double subtotal,double propina,double impuestos,String numeroTarjeta,String tipo,YearMonth expiracion,String titular){
        super(subtotal,propina,impuestos);
        if(numeroTarjeta==null||!numeroTarjeta.matches("[0-9]{13,19}")||tipo==null||tipo.trim().isEmpty()||titular==null||titular.trim().isEmpty()||expiracion==null||expiracion.isBefore(YearMonth.now()))
            throw new IllegalArgumentException("Datos de tarjeta invalidos");
        this.numeroTarjeta=numeroTarjeta;this.tipo=tipo;this.expiracion=expiracion;this.titular=titular;
    }
    public String getTarjetaEnmascarada(){return "**** **** **** "+numeroTarjeta.substring(numeroTarjeta.length()-4);}
    public String getTipo(){return tipo;}
    public String getTitular(){return titular;}
    public YearMonth getExpiracion(){return expiracion;}
    public boolean realizarCargo(){return true;} // Simulacion educativa; no conecta a un banco
    @Override public String getMetodo(){return "Credito (simulado)";}
}

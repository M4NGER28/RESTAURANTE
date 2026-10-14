package restaurante;
import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class RestauranteApp extends JFrame {
    private final JTextField mesa=new JTextField("1",5),comensales=new JTextField("2",5),mesero=new JTextField("Ana",12),cantidad=new JTextField("1",5),propina=new JTextField("0",8),impuestos=new JTextField("0",8),entregado=new JTextField("50000",8);
    private final JComboBox<ItemMenu> menu=new JComboBox<>();
    private final JComboBox<String> metodo=new JComboBox<>(new String[]{"Efectivo","Credito (simulado)"});
    private final JTextArea salida=new JTextArea(17,56);
    private final List<Cliente> clientes=new ArrayList<>();
    private Cliente clienteActual;
    private Orden ordenActual;
    public RestauranteApp(){
        super("Restaurante POO - Gestion de ordenes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));
        JPanel controles=new JPanel();controles.setLayout(new BoxLayout(controles,BoxLayout.Y_AXIS));
        JPanel a=new JPanel(new FlowLayout(FlowLayout.LEFT));
        a.add(new JLabel("Mesa"));a.add(mesa);a.add(new JLabel("Comensales"));a.add(comensales);a.add(new JLabel("Mesero"));a.add(mesero);
        JButton nueva=new JButton("Nueva orden");a.add(nueva);controles.add(a);
        JPanel b=new JPanel(new FlowLayout(FlowLayout.LEFT));
        menu.addItem(new Platillo("Hamburguesa",18000));menu.addItem(new Platillo("Pizza",22000));menu.addItem(new Platillo("Pollo",25000));
        menu.addItem(new Bebida("Agua",4000));menu.addItem(new Bebida("Jugo",7000));menu.addItem(new Bebida("Gaseosa",5000));
        b.add(new JLabel("Producto"));b.add(menu);b.add(new JLabel("Cantidad"));b.add(cantidad);
        JButton agregar=new JButton("Agregar item");b.add(agregar);controles.add(b);
        JPanel c=new JPanel(new FlowLayout(FlowLayout.LEFT));
        c.add(new JLabel("Propina $"));c.add(propina);c.add(new JLabel("Impuestos $"));c.add(impuestos);c.add(new JLabel("Metodo"));c.add(metodo);controles.add(c);
        JPanel d=new JPanel(new FlowLayout(FlowLayout.LEFT));
        d.add(new JLabel("Efectivo entregado $"));d.add(entregado);
        JButton pagar=new JButton("Pagar orden");d.add(pagar);
        JButton otra=new JButton("Ver historial");d.add(otra);controles.add(d);
        add(controles,BorderLayout.NORTH);
        salida.setEditable(false);salida.setFont(new Font(Font.MONOSPACED,Font.PLAIN,13));
        add(new JScrollPane(salida),BorderLayout.CENTER);
        nueva.addActionListener(e->ejecutar(()->{
            int m=entero(mesa.getText()),n=entero(comensales.getText());
            if(clienteActual==null||clienteActual.getNumeroMesa()!=m||clienteActual.getComensales()!=n){
                clienteActual=new Cliente(m,n);clientes.add(clienteActual);
            }
            ordenActual=new Orden(clienteActual,mesero.getText());clienteActual.agregarOrden(ordenActual);mostrar();
        }));
        agregar.addActionListener(e->ejecutar(()->{
            if(ordenActual==null)throw new IllegalStateException("Primero crea una orden");
            ordenActual.agregarDetalle((ItemMenu)menu.getSelectedItem(),entero(cantidad.getText()));mostrar();
        }));
        pagar.addActionListener(e->ejecutar(()->{
            if(ordenActual==null)throw new IllegalStateException("Primero crea una orden");
            double sub=ordenActual.calcularSubtotal(),p=dinero(propina.getText()),i=dinero(impuestos.getText());
            Pago pago;
            if(metodo.getSelectedIndex()==0){pago=new Efectivo(sub,p,i,dinero(entregado.getText()));}
            else {
                JTextField num=new JTextField(),tipo=new JTextField("Visa"),vence=new JTextField("2028-12"),titular=new JTextField();
                JPanel panel=new JPanel(new GridLayout(0,2));
                panel.add(new JLabel("Numero tarjeta (solo datos ficticios)"));panel.add(num);
                panel.add(new JLabel("Tipo"));panel.add(tipo);
                panel.add(new JLabel("Vence (AAAA-MM)"));panel.add(vence);
                panel.add(new JLabel("Titular"));panel.add(titular);
                if(JOptionPane.showConfirmDialog(this,panel,"Credito simulado",JOptionPane.OK_CANCEL_OPTION)!=JOptionPane.OK_OPTION)return;
                Credito credito=new Credito(sub,p,i,num.getText().trim(),tipo.getText(),YearMonth.parse(vence.getText().trim()),titular.getText());
                if(!credito.realizarCargo())throw new IllegalStateException("Cargo rechazado");
                pago=credito;
            }
            ordenActual.registrarPago(pago);mostrar();
            JOptionPane.showMessageDialog(this,"Pago registrado (credito: solo simulacion)");
        }));
        otra.addActionListener(e->{
            StringBuilder sb=new StringBuilder("HISTORIAL DE CLIENTES Y ORDENES\n");
            for(Cliente cl:clientes){sb.append("\nMesa ").append(cl.getNumeroMesa()).append(" / ").append(cl.getComensales()).append(" comensales\n");
                for(Orden o:cl.getOrdenes())sb.append("  ").append(o.getFechaHora()).append(" - $").append(String.format("%,.0f",o.calcularSubtotal())).append(" - ").append(o.getPago()==null?"Pendiente":o.getPago().getMetodo()).append("\n");
            }salida.setText(sb.toString());
        });
        setSize(850,560);setLocationRelativeTo(null);mostrar();
    }
    private int entero(String s){return Integer.parseInt(s.trim());}
    private double dinero(String s){return Double.parseDouble(s.trim());}
    private void ejecutar(Runnable r){try{r.run();}catch(Exception ex){JOptionPane.showMessageDialog(this,ex.getMessage(),"Revisa los datos",JOptionPane.ERROR_MESSAGE);}}
    private void mostrar(){
        if(ordenActual==null){salida.setText("Bienvenido. Crea una orden para comenzar.");return;}
        StringBuilder sb=new StringBuilder("RESTAURANTE - RESUMEN DE ORDEN\n");
        sb.append("Mesa: ").append(ordenActual.getCliente().getNumeroMesa()).append("  Comensales: ").append(ordenActual.getCliente().getComensales()).append("\n");
        sb.append("Mesero: ").append(ordenActual.getMesero()).append("  Fecha/hora: ").append(ordenActual.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");
        for(DetalleOrden det:ordenActual.getDetalles())sb.append(det.getCantidad()).append(" x ").append(det.getItem().getNombre()).append(" [").append(det.getItem().getTipo()).append("]  $").append(String.format("%,.0f",det.getSubtotal())).append("\n");
        sb.append("\nSubtotal: $").append(String.format("%,.0f",ordenActual.calcularSubtotal()));
        if(ordenActual.getPago()!=null){
            Pago p=ordenActual.getPago();
            sb.append("\nPropina: $").append(String.format("%,.0f",p.getPropina())).append("\nImpuestos: $").append(String.format("%,.0f",p.getImpuestos()));
            sb.append("\nTOTAL: $").append(String.format("%,.0f",p.calcularTotal())).append("\nMetodo: ").append(p.getMetodo());
            if(p instanceof Efectivo)sb.append("\nCambio: $").append(String.format("%,.0f",((Efectivo)p).getCambio()));
            if(p instanceof Credito)sb.append("\nTarjeta: ").append(((Credito)p).getTarjetaEnmascarada());
        }else sb.append("\nEstado: PENDIENTE DE PAGO");
        salida.setText(sb.toString());
    }
    public static void main(String[] args){SwingUtilities.invokeLater(()->new RestauranteApp().setVisible(true));}
}

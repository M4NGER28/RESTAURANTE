# Sistema de Gestión de Restaurante (POO)

Proyecto educativo en Java con interfaz gráfica Swing, compatible con Apache NetBeans.

## Funcionalidades
- Crear clientes por mesa y cantidad de comensales.
- Registrar una o varias órdenes por cliente con mesero y fecha/hora.
- Agregar platillos y bebidas con cantidad y precio.
- Calcular subtotal, propina, impuestos y total.
- Pagar en efectivo con cálculo de cambio o simular un pago a crédito.
- Consultar historial de órdenes en memoria.

## Clases
`Cliente`, `Orden`, `ItemMenu`, `Platillo`, `Bebida`, `DetalleOrden`, `Pago`, `Efectivo`, `Credito` y `RestauranteApp`.

## Ejecución en NetBeans
1. Archivo > Nuevo proyecto > Java with Ant > Java Application (o proyecto Java estándar).
2. Asigna el nombre `RestaurantePOO`.
3. En `Source Packages`, crea el paquete `restaurante`.
4. Copia los archivos `.java` de `src/restaurante/` a ese paquete.
5. Ejecuta la clase `restaurante.RestauranteApp` (Run File).

Requiere JDK 8 o superior. No utiliza librerías externas.

## Demostración
1. Ingresa mesa, comensales y mesero. Pulsa **Nueva orden**.
2. Selecciona productos y cantidades y pulsa **Agregar item**.
3. Ingresa propina e impuestos como valores monetarios.
4. Selecciona Efectivo e indica el monto entregado, o Crédito con datos ficticios.
5. Pulsa **Pagar orden** y consulta el resumen o el historial.

## Consideraciones
- El pago con tarjeta es **simulado**, no procesa transacciones reales.
- No ingreses tarjetas reales: es una demostración académica.
- La información se conserva solo mientras la aplicación está abierta.
- Los impuestos se ingresan manualmente, no se calculan automáticamente.
- Se permite crear otra orden para la misma mesa y cantidad de comensales mientras siga seleccionado ese cliente.
- No se incluye base de datos.



## Autores
MANGER RAMIREZ 1152732
EMMANUEL ROPERO 1152460

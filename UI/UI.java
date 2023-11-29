package picadoRLuisCarlos.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class UI {
    private final PrintStream out = System.out;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public void menu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Menu de registro.");
        System.out.println("2. Control de registros.");
        System.out.println("3. Cerrar sesion.");
        System.out.println("\nIngrese una opcion");
    }
    public void menu1(){
        System.out.println("Menú de Inicio");
        System.out.println("1. Iniciar sesión con cédula");
        System.out.println("2. Crear Cuenta");
        System.out.println("3. Salir");
        System.out.println("Ingrese una opcion: ");
    }
    public void menu2(){
        out.println("\nMenú de Registro:");
        out.println("1. Registrar nuevo cliente.");
        out.println("2. Registrar nueva mascota.");
        out.println("3. Registrar nueva cita.");
        out.println("4. Registrar nueva reservación.");
        out.println("5. Volver al menú anterior.");
        out.println("\nIngrese una opción:");
    }
    public void menu3(){
        out.println("\nControl de registros:");
        out.println("1. Mostrar citas registradas.");
        out.println("2. Mostrar reservaciones registradas.");
        out.println("3. Mostrar clientes registrados.");
        out.println("4. Mostrar mascotas registradas.");
        out.println("5. Mostrar usuarios administradores registrados.");
        out.println("6. Volver al menú principal.");
        out.println("\nIngrese una opción:");
    }

    public int optionReader() throws Exception {
        return Integer.parseInt(in.readLine());
    }

    public void printText(String mensaje){
        out.println(mensaje);
    }

    public String readText() throws Exception {
        return in.readLine();
    }

}
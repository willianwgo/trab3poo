import biblioteca.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Sistema biblioteca = new Sistema();
        
        Scanner entrada = new Scanner(System.in);
        System.out.print("Defina data para o sistema:\nDia: ");
        int dia = entrada.nextInt();
        System.out.print("Mes: ");
        int mes = entrada.nextInt();
        System.out.print("Ano: ");
        int ano = entrada.nextInt(); 
        
        Calendario calendario = new Calendario(dia, mes, ano);
        calendario.getTime();
        
        biblioteca.menu();
    }
}

package biblioteca;

public class Calendario {
  private static int dia;
  private static int mes;
  private static int ano;
  
  public Calendario(int dia, int mes, int ano) {
      Calendario.dia = dia;
      Calendario.mes = mes;
      Calendario.ano = ano;
  }

  public static int getDia() {
      return dia;
  }
  
  public static int getMes() {
      return mes;
  }
    
  public static int getAno() {
      return ano;
  }
  
  	//imprime na tela data atualizada
  public static void getTime() {
        System.out.print("\nSistema rodando em: ");
        System.out.print(dia + "/");
        System.out.print(mes + "/");
        System.out.print(ano + "\n\n");

    }
  
}

import biblioteca.Sistema;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        Sistema biblioteca = new Sistema();
        Sistema.getTime();
        biblioteca.menu();
    }
}

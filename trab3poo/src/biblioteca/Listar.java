package biblioteca;

import java.io.*;
import java.util.StringTokenizer;

// A classe lista imprime o arquivo correspondente

public class Listar {

    // O metodo 'ListarUsuario' imprime toda o arquivo de usuarios

    public void ListarUsuarios() {
        String str;
        StringTokenizer st;

        try {

            InputStream is = new FileInputStream("usuarios.csv");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(isr);

            str = buffer.readLine();

            System.out.println("\t" + "USUARIOS");

            while(str != null) {

                st = new StringTokenizer(str, ",");

                st.nextToken();

                // Cria as strings para pegar os dados da linha do arquivo correspondente

                String Nome = st.nextToken();
                String Email = st.nextToken();
                String AreaAtuacao = st.nextToken();
                String Curso = st.nextToken();
                String Categoria = st.nextToken();

                System.out.print(Nome + "\t");
                System.out.print(Email + "\t");

                if(AreaAtuacao.charAt(0) != ' ') {
                    System.out.print(AreaAtuacao + "\t");
                }
                if(Curso.charAt(0) != ' ') {
                    System.out.print(Curso + "\t");
                }

                System.out.println(Categoria);

                str = buffer.readLine();
            }
        }catch(IOException ex) {
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    // O metodo 'ListarLivros' imprime toda o arquivo de livros

    public void ListarLivros() {
        String str;
        StringTokenizer st;

        try {

            System.out.println("\t" + "LIVROS");

            InputStream is = new FileInputStream("livros.csv");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(isr);

            str = buffer.readLine();

            // Cria as strings para pegar os dados da linha do arquivo correspondente

            while(str != null) {

                st = new StringTokenizer(str, ",");

                String Titulo = st.nextToken();
                String Autor = st.nextToken();
                String Editora = st.nextToken();

                // Imprime as strings

                System.out.print(Titulo + "\t");
                System.out.print(Autor + "\t");
                System.out.println(Editora);

                str = buffer.readLine();
            }
        }catch(IOException ex) {
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    // O metodo 'ListarEmprestimos' imprime toda o arquivo de emprestimos    

    public void ListarEmprestimos() {
        String str;
        StringTokenizer st;

        try {

            InputStream is = new FileInputStream("emprestimos.csv");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(isr);

            str = buffer.readLine();

            System.out.println("\t" + "LISTA DE EMPRESTIMOS");

            while(str != null) {

                st = new StringTokenizer(str, ",");

                // Cria as strings para pegar os dados da linha do arquivo correspondente

                String Email = st.nextToken();
                String Data = st.nextToken();
                String Titulo = st.nextToken();
                String Autor = st.nextToken();
                String Editora = st.nextToken();

                // Imprime as strings
                if(Email.charAt(0) != '*') {
                    System.out.print(Email + "\t");
                    System.out.print(Data + "\t");
                    System.out.print(Titulo + "\t");
                    System.out.print(Autor + "\t");
                    System.out.println(Editora);
                }

                str = buffer.readLine();
            }
        }catch(IOException ex) {
            System.out.println("Erro ao escrever no arquivo");
        }
    }
}
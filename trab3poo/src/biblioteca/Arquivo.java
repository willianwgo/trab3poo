package biblioteca;

import java.io.*;

public class Arquivo {
    String tipo = "Usuarios";
    String tipo2 = "Livros";
    String tipo3 = "Emprestimos";

    FileWriter arquivo;
    BufferedWriter buff;

    public void EscreverArquivo(String TipoArquivo, String str){
        if(TipoArquivo.equals(tipo)){
            try{
                arquivo = new FileWriter("usuarios.csv", true);
                buff = new BufferedWriter(arquivo);
            }
            catch(IOException ex){
                System.out.println("Erro ao abrir o arquivo");
            }
        }
        else if(TipoArquivo.equals(tipo2)){
            try{
                arquivo = new FileWriter("livros.csv", true);
                buff = new BufferedWriter(arquivo);
            }
            catch(IOException ex){
                System.out.println("Erro ao abrir o arquivo");
            }
        }
        else if(TipoArquivo.equals(tipo3)){
            try{
                arquivo = new FileWriter("emprestimos.csv", true);
                buff = new BufferedWriter(arquivo);
            }
            catch(IOException ex){
                System.out.println("Erro ao abrir o arquivo");
            }
        }

        try {
            buff.write(str);
            buff.close();
        }
        catch(IOException ex) {
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    public void escreverSuspensao(String str) {
        try{
            arquivo = new FileWriter("suspensao.csv", true);
            buff = new BufferedWriter(arquivo);
            buff.write(str);
            buff.close();
        }
        catch(IOException ex){
            System.out.println("Erro ao abrir o arquivo");
        }
    }

}

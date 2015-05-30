package biblioteca;

import java.io.*;

// Classe Arquivo escreve a string 

public class Arquivo {

    // Strings que armazenam os tipos de usuarios

    String tipo = "Usuarios";
    String tipo2 = "Livros";
    String tipo3 = "Emprestimos";

    // Abre o arquivo 

    FileWriter arquivo;
    BufferedWriter buff;

    //cria arquivos iniciais
    public static void criarArquivos() {
        try {
            InputStream is;
            is = new FileInputStream("usuarios.csv");
            is = new FileInputStream("livros.csv");
            is = new FileInputStream("emprestimos.csv");
            is = new FileInputStream("suspensao.csv");
        }
        catch(IOException ex){
            System.out.println("Erro ao criar arquivos");
        }
    }

    // Escreve uma string no arquivo correspondente a categoria do usuario

    public void EscreverArquivo(String TipoArquivo, String str){

        // Seleciona a string do tipo 'Professor'

        if(TipoArquivo.equals(tipo)){
            try{
                arquivo = new FileWriter("usuarios.csv", true);
                buff = new BufferedWriter(arquivo);
            }
            catch(IOException ex){
                System.out.println("Erro ao abrir o arquivo");
            }
        }

        // Seleciona a string do tipo 'Aluno'

        else if(TipoArquivo.equals(tipo2)){
            try{
                arquivo = new FileWriter("livros.csv", true);
                buff = new BufferedWriter(arquivo);
            }
            catch(IOException ex){
                System.out.println("Erro ao abrir o arquivo");
            }
        }

        // Seleciona a string do tipo 'Comunidade'

        else if(TipoArquivo.equals(tipo3)){
            try{
                arquivo = new FileWriter("emprestimos.csv", true);
                buff = new BufferedWriter(arquivo);
            }
            catch(IOException ex){
                System.out.println("Erro ao abrir o arquivo");
            }
        }

        // Fecha o arquivo aberto anteriormente

        try {
            buff.write(str);
            buff.close();
        }
        catch(IOException ex) {
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    // Escreve no arquivo a data de suspensao
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
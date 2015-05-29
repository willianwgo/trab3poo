package biblioteca;

import java.util.Scanner;

public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private int permissao;

    Scanner entrada = new Scanner(System.in);

    //Pega do usuario dados do livro
    public void setLivro() {
        System.out.print("Titulo: ");
        titulo = entrada.nextLine();

        System.out.print("Autor: ");
        autor = entrada.nextLine();

        System.out.print("Editora: ");
        editora = entrada.nextLine();

        System.out.print("Quem tem permissao para emprestar?\n <0>Aluno/Professor/Comunidade\n <1>Aluno/Professor\n");
        permissao = entrada.nextInt();
    }

    // int: permissao
    // <0> = aluno/professor/comunidade
    // <1> = aluno/prof

    int disponibilidade = 1;
    // <0> = indisponivel
    // <1> = disponivel
    
    //retorna string pronta para salvar no arquivo -> livros.csv
    public String getLivro() {
        return titulo + "," + autor + "," + editora + "," + permissao + "," + "disponibilidade" + "\n";
    }

}

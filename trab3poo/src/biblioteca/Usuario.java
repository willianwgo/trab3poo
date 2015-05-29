package biblioteca;

import java.util.Scanner;

/* Classe usuario possui os dados do sistema relativos ao usuario e as funcoes para inicializar essses dados */

public class Usuario {
    int Flag = 0;
    String Nome;
    String Email;
    String AreaAtuacao;
    String Curso;
    String Categoria;
    private int Senha;

    String ColocaArquivo;

    Scanner ler = new Scanner(System.in);

    // Strings inicializadas com as possibilidades da categoria
    String categoria = "Professor";
    String categoria2 = "Aluno";
    String categoria3 = "Comunidade";

    // Funcoes que inicializam os dados

    public void setNome() {
        System.out.printf("Nome: ");
        Nome = ler.nextLine();
    }

    public void setEmail() {
        System.out.printf("Email: ");
        Email = ler.nextLine();
    }

    public void setCategoria() {
        System.out.println("Entre com: Aluno, Professor ou Comunidade: ");
        System.out.printf("Categoria: ");
        Categoria = ler.nextLine();
    }

    public void setAreaAtuacao() {

        System.out.printf("Area de atuacao: ");
        AreaAtuacao = ler.nextLine();
    }

    public void setCurso() {

        System.out.printf("Curso: ");
        Curso = ler.nextLine();
    }

    private void setSenha() {
        System.out.printf("Digite uma senha (Somente numeros): ");
        Senha = ler.nextInt();
    }

    // Funcao que seleciona o tipo de usuario e preenche a string que sera armazenada no arquivo
    // Cada tipo de usuario possui strings diferentes pois cada categoria de usuario possui atributos diferentes

    public void SelecionaTipoCadastro(Usuario user){

        // se for 'Professor' chama as funcoes setNome, setEmail, setAreaAtuacao e setSenha

        user.setCategoria();
        if(Categoria.equals(categoria)){
            user.setNome();
            user.setEmail();
            user.setAreaAtuacao();
            user.setSenha();
            ColocaArquivo = Flag + "," + Nome + "," + Email + "," + AreaAtuacao + "," + " ," + Categoria + "," + Senha + "\n";
        }
        
        // se for 'Aluno' chama as funcoes setNome, setEmail, setCurso e setSenha

        else if(Categoria.equals(categoria2)){
            user.setNome();
            user.setEmail();
            user.setCurso();
            user.setSenha();
            ColocaArquivo = Flag + "," + Nome + "," + Email + "," + " ," + Curso + "," + Categoria + "," + Senha + "\n";
        }

        // se for 'Comunidade' chama as funcoes setNome, setEmail e setSenha

        else if(Categoria.equals(categoria3)){
            user.setNome();
            user.setEmail();
            user.setSenha();
            ColocaArquivo = Flag + "," + Nome + "," + Email + "," + " ," + " ," + Categoria + "," + Senha + "\n";
        }

        // Caso a categoria seja invalida

        else
            System.out.println("Categoria invalida !");
    }
}
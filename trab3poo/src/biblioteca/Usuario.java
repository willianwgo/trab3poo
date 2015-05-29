package biblioteca;

import java.util.Scanner;

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

    public void SelecionaTipoCadastro(Usuario user){

        user.setCategoria();
        if(Categoria.equals(categoria)){
            user.setNome();
            user.setEmail();
            user.setAreaAtuacao();
            user.setSenha();
            ColocaArquivo = Flag + "," + Nome + "," + Email + "," + AreaAtuacao + "," + " ," + Categoria + "," + Senha + "\n";
        }
        else if(Categoria.equals(categoria2)){
            user.setNome();
            user.setEmail();
            user.setCurso();
            user.setSenha();
            ColocaArquivo = Flag + "," + Nome + "," + Email + "," + " ," + Curso + "," + Categoria + "," + Senha + "\n";
        }
        else if(Categoria.equals(categoria3)){
            user.setNome();
            user.setEmail();
            user.setSenha();
            ColocaArquivo = Flag + "," + Nome + "," + Email + "," + " ," + " ," + Categoria + "," + Senha + "\n";
        }
        else {
            System.out.println("Categoria invalida !");
        }

    }
}

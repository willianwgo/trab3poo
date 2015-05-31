package biblioteca;

import java.util.Scanner;

public class Sistema {
    Scanner entrada = new Scanner(System.in);
    private int opcao;

	//mostra menu ao usuario
	public void menu() {
        //Arquivo.criarArquivos();
        System.out.println("1 - Cadastro de novo usuario\n2 - Entrar como Administrador\n3 - Entrar como Usuario\n0 - Sair");
        opcao = entrada.nextInt();

        if(opcao == 1) {
            cadastrarUsuario();
        }
        else if(opcao == 2){
            entrarAdm();
        }
        else if(opcao == 3){
            entrarSistema();
        }
        else if(opcao == 0) {
            System.exit(0);
        }
        else{
            menu();
        }
    }

    private void cadastrarUsuario() {
        //cadastra usuario
		Usuario usuario = new Usuario();
        usuario.SelecionaTipoCadastro(usuario);

		//salva no arquivo -> usuarios.csv
        Arquivo arquivo = new Arquivo();
        arquivo.EscreverArquivo("Usuarios", usuario.ColocaArquivo);

        System.out.println("Cadastro realizado\n");
        menu();
    }

	//acesso ao sistema como administrador
    private void entrarAdm() {
        entrada.nextLine();
        String login, senha;
        int opcao;

			//verifica se login e senha
            System.out.print("Login: ");
            login = entrada.nextLine();
            System.out.print("Senha: ");
            senha = entrada.nextLine();

            if(!login.equals("adm") || !senha.equals("adm")) {
                System.out.println("Login ou senha invalida\n");
                menu();
            }

        Listar l = new Listar();

        while(true) {
            System.out.println("\n1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Listar emprestimos");
            System.out.println("4 - Listar Usuarios");
            System.out.println("0 - Voltar");

            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    l.ListarLivros();
                    break;
                case 3:
                    l.ListarEmprestimos();
                    break;
                case 4:
                    l.ListarUsuarios();
                    break;
                case 0:
                    menu();
                    break;
            }
        }
    }

    private void cadastrarLivro() {
        //pega o livro do usuario
		Livro livro = new Livro();
        livro.setLivro();

		//salva no arquivo -> livros.csv
        Arquivo arquivo = new Arquivo();
        arquivo.EscreverArquivo("Livros", livro.getLivro());

        System.out.println("Livro cadastrado\n");
        menu();
    }

	//entrar no sistema como usuario qualquer
    private void entrarSistema() {
        String email;
        int suspenso;

        entrada.nextLine(); //limpa buffer teclado

		System.out.print("Digite seu email: ");
        email = entrada.nextLine();

		Verifica verifica = new Verifica(email);

		//verifica se usuario existe e a senha esta correta
        if(verifica.login() == true) {

            suspenso = verifica.atraso();

			//opcoes disponiveis para usuario
            while (true) {
                System.out.println("\n1 - Fazer emprestimo");
                System.out.println("2 - Devolucao de livro");
                System.out.println("3 - Dias suspensos");
                System.out.println("0 - Voltar");

                opcao = entrada.nextInt();

                switch (opcao) {
                    case 1:
                        emprestimo(verifica);
                        break;
                    case 2:
                        devolucao(verifica, suspenso);
                        break;
                    case 3:
                        verifica.suspenso(suspenso);
                        break;
                    case 0:
                        menu();
                        break;
                }
            }
        }
        else {
            System.out.println("Email ou senha invalida.");
            menu();
        }
    }

	//calcula a data de devolucao do livro
    public static String dataDevolucao(String categoria) {
        int ano, mes, dia, tipo;

		//verifica qual categoria
        if(categoria.equals("Aluno")) {
            tipo = 1;
        }
        else if(categoria.equals("Professor")) {
            tipo = 2;
        }
        else {
            tipo = 3;
        }

        ano = Calendario.getAno();
        mes = Calendario.getMes();
        dia = Calendario.getDia();

	//realiza os calculos para obter a data de devolucao
        if(tipo == 1 || tipo == 3) {
            if(dia > 15) {
                dia -= 15;
                if(mes == 12) {
                    mes = 1;
                    ano += 1;
                }
                else {
                    mes += 1;
                }
            }
            else {
                dia += 15;
            }
        }
        else {
            if(mes == 12) {
                mes = 2;
                ano += 1;
            }
            else if(mes == 11) {
                mes = 1;
                ano += 1;
            }
            else {
                mes +=2;
            }
        }
        return Integer.toString(dia) +"/"+ Integer.toString(mes) +"/"+ Integer.toString(ano);
    }

    private void emprestimo(Verifica verifica) {
        String titulo, autor, editora;

        entrada.nextLine(); //limpar buffer teclado

        //pega entrada do usuario
	System.out.print("Titulo: ");
        titulo = entrada.nextLine();

        System.out.print("Autor: ");
        autor = entrada.nextLine();

        System.out.print("Editora: ");
        editora = entrada.nextLine();

	//realiza o emprestimo do livro
        verifica.livro(titulo, autor, editora);
    }

	//devolucao do livro
    private void devolucao(Verifica verifica, int suspenso) {
        String titulo, autor, editora;

        entrada.nextLine(); //limpar buffer teclado

	//pega entradas do usuario
        System.out.print("Titulo: ");
        titulo = entrada.nextLine();

        System.out.print("Autor: ");
        autor = entrada.nextLine();

        System.out.print("Editora: ");
        editora = entrada.nextLine();

        //realiza a devolucao do livro
	verifica.devolucao(titulo, autor, editora, suspenso);
    }

    //calcula a data em que ficara suspenso
    public static String dataSuspensao(int suspenso) {
        int ano, mes, dia, somaDia, i=0, somaMes;

        ano = Calendario.getAno();
        mes = Calendario.getMes();
        dia = Calendario.getDia();

        somaDia = suspenso + dia;

        //realiza os calculos para obter a data de devolucao
        if(somaDia < 31) {
            dia = somaDia;
        }
        else {
            while (somaDia > 31) {
                somaDia = somaDia - 30;
                i++;
            }
            dia = somaDia;
        }
        somaMes = mes + i;

        if(somaMes > 12) {
            mes = somaMes-12;
            ano = ano+1;
        }
        else {
            mes = somaMes;
        }

        return Integer.toString(dia) +"/"+ Integer.toString(mes)+"/"+ Integer.toString(ano);
    }

}

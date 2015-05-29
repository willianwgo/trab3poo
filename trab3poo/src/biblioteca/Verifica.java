package biblioteca;

import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Verifica {
    InputStream is;
    InputStreamReader isr;
    BufferedReader buffer;

    String email;

    public Verifica(String email) {
        this.email = email;
    }

    public boolean login() {
        Scanner in = new Scanner(System.in);
        String str;
        StringTokenizer st;

        System.out.print("Digite sua senha: ");
        int senha = in.nextInt();

        try {
            is = new FileInputStream("usuarios.csv");
            isr = new InputStreamReader(is);
            buffer = new BufferedReader(isr);

            str = buffer.readLine();

            while(str != null) {
                st = new StringTokenizer(str, ",");
                st.nextToken();
                st.nextToken();
                if (st.nextToken().equals(email)) {
                    st.nextToken();
                    st.nextToken();
                    st.nextToken();
                    if (st.nextToken().equals(Integer.toString(senha))) {
                        return true;
                    }
                }
                str = buffer.readLine();
            }
            return false;
        }
        catch (IOException e) {System.out.println("Error> Login");}
        return false;
    }

    private String getCategoria() {
        String str;
        StringTokenizer st;

        try {
            is = new FileInputStream("usuarios.csv");
            isr = new InputStreamReader(is);
            buffer = new BufferedReader(isr);

            str = buffer.readLine();

            while(str != null) {
                st = new StringTokenizer(str, ",");
                st.nextToken();
                st.nextToken();
                if (st.nextToken().equals(email)) {
                    st.nextToken();
                    st.nextToken();
                    return st.nextToken();
                }
                str = buffer.readLine();
            }
        }
        catch (IOException e) {System.out.println("Error");}

        return null;
    }

    private int getSuspenso() {
        String str;
        StringTokenizer st;
        int suspenso;

        try {
            is = new FileInputStream("usuarios.csv");
            isr = new InputStreamReader(is);
            buffer = new BufferedReader(isr);

            str = buffer.readLine();

            while(str != null) {
                st = new StringTokenizer(str, ",");
                suspenso = Integer.parseInt(st.nextToken());
                st.nextToken();
                if (st.nextToken().equals(email)) {
                    return suspenso;
                }
                str = buffer.readLine();
            }
        }
        catch (IOException e) {System.out.println("Error");}

        return -1;
    }

    private int getQuantidadeLivros() {
        String str;
        StringTokenizer st;

        int quantidade = 0;

        try {
            InputStream is = new FileInputStream("emprestimos.csv");
            isr = new InputStreamReader(is);
            buffer = new BufferedReader(isr);

            str = buffer.readLine();

            while(str != null) {
                st = new StringTokenizer(str, ",");

                if (st.nextToken().equals(email)) {
                    quantidade++;
                }
                str = buffer.readLine();
            }
        }
        catch (IOException e) {System.out.println("Error");}

        return quantidade;
    }

    public boolean livro(String titulo, String autor, String editora) {
        try {
            //ver se esta suspenso, se nao estorou o limite, aluno/comunidade/prof
            int suspenso = getSuspenso();
            int quantidade = getQuantidadeLivros();
            String categoria = getCategoria();

            if(suspenso == 1) {
                System.out.println("Usuario suspenso por atraso na entrega de livros.");
                return false;
            }

            if(categoria.equals("Comunidade")) {
                if(quantidade == 2) {
                    System.out.println("Limite de emprestimos por usuario.");
                    return false;
                }
            }
            if(categoria.equals("Aluno")) {
                if(quantidade == 4) {
                    System.out.println("Limite de emprestimos por usuario.");
                    return false;
                }
            }
            if(categoria.equals("Professor")) {
                if(quantidade == 6) {
                    System.out.println("Limite de emprestimos por usuario.");
                    return false;
                }
            }

            //abrir livros -> verificar se existe e se esta disponivel;
            RandomAccessFile livros = new RandomAccessFile("livros.csv", "rw");

            Arquivo arquivo = new Arquivo();

            while (true) {
                int i = livros.read();
                char[] seq = new char[500];

                //testa fim do arquivo
                if (i == -1) {
                    System.out.println("Livro nao cadastrado.");
                    return false;
                }

                char c = (char) i;
                int flag = 0;

                i = 0;
                while (flag != 3) {
                    seq[i++] = c;
                    c = (char) livros.read();
                    if (c == ',') {
                        flag++;
                    }
                }
                seq[i] = c;
                String str = new String(seq);

                StringTokenizer st = new StringTokenizer(str, ",");

                if (st.nextToken().equals(titulo)) {
                    if (st.nextToken().equals(autor)) {
                        if (st.nextToken().equals(editora)) {
                            i = livros.read()-48;
                            if (i == 1 && categoria.equals("Comunidade")) {
                                //se i==1(aluno/prof) e usuario eh comunidade
                                System.out.println("Livro disponivel somente para aluno/professor.");
                                return false;
                            } else {
                                livros.seek(livros.getFilePointer() + 1);
                                i = livros.read() - 48;
                                if (i == 1) {
                                    livros.seek(livros.getFilePointer() - 1);
                                    livros.writeBytes("0"); //-> grava 0
                                    String data = Sistema.dataDevolucao(categoria);
                                    str = email + "," + data + "," +titulo  + "," + autor  + "," + editora  + "\n";
                                    arquivo.EscreverArquivo("Emprestimos", str);
                                    System.out.println("Emprestimo realizado");
                                    return true;
                                } else {
                                    System.out.println("Nao disponivel para emprestimo.");
                                    return false;
                                }
                            }
                        }
                    }
                }
                do {
                    c = (char) livros.read();
                }
                while (c != '\n');
            }
        }
        catch (IOException e) {System.out.println("Error");}
        return false;
    }

    public int atraso() {
        String str, data;
        StringTokenizer st, st2;
        int atraso, maior = 0;

        Calendar cal = Calendar.getInstance();

        try {
            is = new FileInputStream("emprestimos.csv");
            isr = new InputStreamReader(is);
            buffer = new BufferedReader(isr);

            str = buffer.readLine();

            while(str != null) {
                st = new StringTokenizer(str, ",");
                if (st.nextToken().equals(email)) {
                    data = st.nextToken();
                    atraso = calculaAtraso(data);

                    if(atraso != 0) {
                        System.out.println("O livro " + st.nextToken() + " esta " + atraso + " dias atrasados");
                    }

                    if(maior < atraso) {
                        maior = atraso;
                    }

                }
                str = buffer.readLine();
            }

            RandomAccessFile usuarios = new RandomAccessFile("usuarios.csv", "rw");
            long byteoffset;

            while (true) {
                byteoffset = usuarios.getFilePointer();
                int i = usuarios.read();
                char[] seq = new char[500];

                //testa fim do arquivo
                if (i == -1) {
                    break;
                }

                char c = (char) i;
                int flag = 0;

                i = 0;
                while (c != '\n') {
                    seq[i++] = c;
                    c = (char) usuarios.read();
                }

                str = new String(seq);
                st = new StringTokenizer(str, ",");

                st.nextToken();
                st.nextToken();

                    if (st.nextToken().equals(email)) {
                        if(maior != 0) {
                            usuarios.seek(byteoffset);
                            usuarios.writeBytes("1"); //-> suspenso
                        }
                        break;
                    }
            }

            if(maior == 0) {
                RandomAccessFile suspensao = new RandomAccessFile("suspensao.csv", "rw");

                while (true) {
                    long b = suspensao.getFilePointer();
                    int i = suspensao.read();
                    char[] seq = new char[500];

                    //testa fim do arquivo
                    if (i == -1) {
                        break;
                    }

                    char c = (char) i;

                    i = 0;
                    while (c != '\n') {
                        seq[i++] = c;
                        c = (char) suspensao.read();
                    }

                    str = new String(seq);
                    st = new StringTokenizer(str, ",");

                    if (st.nextToken().equals(email)) {
                        st2 = new StringTokenizer(st.nextToken(), "/");
                        if(Integer.parseInt(st2.nextToken()) == cal.get(Calendar.DAY_OF_MONTH)){
                            if(Integer.parseInt(st2.nextToken()) == cal.get(Calendar.MONTH)+1){
                                if(Integer.parseInt(st2.nextToken()) == cal.get(Calendar.YEAR)){
                                    suspensao.seek(b);
                                    suspensao.writeBytes("*");
                                    usuarios.seek(byteoffset);
                                    usuarios.writeBytes("0"); //-> suspenso
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e) {System.out.println("Error");}

        return maior;
    }

    private int calculaAtraso(String data) {
        int ano, mes, dia, atraso;

        StringTokenizer st = new StringTokenizer(data, "/");
        dia = Integer.parseInt(st.nextToken());
        mes = Integer.parseInt(st.nextToken());
        ano = Integer.parseInt(st.nextToken());

        Calendar c = Calendar.getInstance();

        if(mes == 1+c.get(Calendar.MONTH) && ano == c.get(Calendar.YEAR)) {
            atraso = c.get(Calendar.DAY_OF_MONTH) - dia;
            if(atraso > 0) {
                return atraso;
            }
        }

        return 0;

    }

    public void suspenso(int s) {
        if(s != 0) {
            System.out.println("Voce esta suspenso por " + s + " dias.");
        }
        else {
            System.out.println("Voce esta em dia com a biblioteca.");
        }
    }

    public boolean devolucao(String titulo, String autor, String editora, int suspenso) {
        try {
            String str, data;
            StringTokenizer st;

            Arquivo arq = new Arquivo();

            RandomAccessFile emprestimos = new RandomAccessFile("emprestimos.csv", "rw");

            while (true) {
                long byteoffset = emprestimos.getFilePointer();
                int i = emprestimos.read();
                char[] seq = new char[500];

                //testa fim do arquivo
                if (i == -1) {
                    break;
                }

                char c = (char) i;

                i = 0;
                while (c != '\n') {
                    seq[i++] = c;
                    c = (char) emprestimos.read();
                }
                seq[i] = ',';
                str = new String(seq);
                st = new StringTokenizer(str, ",");

                if (st.nextToken().equals(email)) {
                    st.nextToken();
                    if (st.nextToken().equals(titulo)) {
                        if (st.nextToken().equals(autor)) {
                            if (st.nextToken().equals(editora)) {
                                emprestimos.seek(byteoffset);
                                emprestimos.writeBytes("*");
                                break;
                            }
                        }
                    }
                }
            }

            //abrir livros -> e marcar como disponivel;
            RandomAccessFile livros = new RandomAccessFile("livros.csv", "rw");

            while (true) {
                int i = livros.read();
                char[] seq = new char[500];

                //testa fim do arquivo
                if (i == -1) {
                    return false;
                }

                char c = (char) i;
                int flag = 0;

                i = 0;
                while (flag != 3) {
                    seq[i++] = c;
                    c = (char) livros.read();
                    if (c == ',') {
                        flag++;
                    }
                }
                seq[i] = c;
                str = new String(seq);

                st = new StringTokenizer(str, ",");

                if (st.nextToken().equals(titulo)) {
                    if (st.nextToken().equals(autor)) {
                        if (st.nextToken().equals(editora)) {
                            livros.seek(livros.getFilePointer() + 2);
                                i = livros.read() - 48;
                                if (i == 0) {
                                    livros.seek(livros.getFilePointer() - 1);
                                    livros.writeBytes("1"); //-> grava 1 == disponivel para emprestimo
                                    System.out.println("Devolucao realizada");
                                    if(suspenso != 0) {
                                        //Arquivo arq = new Arquivo();
                                        data = Sistema.dataSuspensao(suspenso);
                                        arq.escreverSuspensao(email + "," + data + "/" + "\n");
                                    }
                                    return true;
                                } else {
                                    System.out.println("Erro devolucao.");
                                    return false;
                                }
                        }
                    }
                }
                do {
                    c = (char) livros.read();
                }
                while (c != '\n');
            }

        }
        catch (IOException e) {System.out.println("Error");}
        return false;
    }

}

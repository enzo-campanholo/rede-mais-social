package views;

import controllers.CadastroController;
import controllers.EmailController;

import java.util.Scanner;
import java.sql.Date;

public class CandidatoView {
    private CadastroController cadastroController;
    private EmailController emailController;
    private Scanner sc;

    public CandidatoView(CadastroController cadastroController, EmailController emailController) {
        this.cadastroController = cadastroController;
        this.emailController = emailController;
        this.sc = new Scanner(System.in);
    }

    public void inicio() {
        System.out.print("Digite seu email para cadastro: ");
        String email = sc.nextLine();

        int cadastro_result = cadastroController.verificarExistencia(email);

        if (cadastro_result != 0)
            System.out.println("Usuario ja cadastrado!");
        else {
            int option = 0;
            System.out.println("[1] - Pessoa Fisica");
            System.out.println("[2] - Pessoa Juridica");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    formularioPessoaFisica(email);
                    break;
                case 2:
                    formularioPessoaJuridica(email);
                    break;
            }

        }
    }

    public void formularioPessoaJuridica(String email) {
        System.out.println("-------Formulario Pessoa Juridica------");

        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();

        System.out.print("Razao social: ");
        String razao_social = sc.nextLine();

        cadastroController.armazenarPessoaJuridica(email, cnpj, razao_social);

        System.out.println("-------Representantes------");

        boolean add = true;
        int choice;

        String nome;
        String doc_identificacao;

        while (add) {

            System.out.println("[0] - Continuar");
            System.out.println("[1] - Adicionar Representante");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    add = false;
                    break;
                case 1:
                    System.out.print("Nome: ");
                    nome = sc.nextLine();

                    System.out.print("Documento: ");
                    doc_identificacao = sc.nextLine();

                    cadastroController.armazenarRepresentante(nome, email, doc_identificacao);
                    break;
            }

        }

        formularioEndereco(email);
    }

    public void formularioPessoaFisica(String email) {
        System.out.println("-------Formulario Pessoa Fisica------");

        System.out.print("cpf: ");
        String cpf = sc.nextLine();

        System.out.print("nome: ");
        String nome = sc.nextLine();

        System.out.print("sexo: ");
        String sexo = sc.nextLine();

        Date dataNascimento;
        while (true) {
            try {
                System.out.print("data de nascimento (formato = YYYY-MM-DD): ");
                dataNascimento = Date.valueOf(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Data errada!");
            }
        }

        System.out.print("nacionalidade: ");
        String nacionalidade = sc.nextLine();

        System.out.print("profissao: ");
        String profissao = sc.nextLine();

        cadastroController.armazenarPessoaFisica(email, cpf, nome, sexo, dataNascimento, nacionalidade, profissao);
        formularioEndereco(email);
    }

    public void formularioEndereco(String email) {
        System.out.println("-------Formulario Endereço------");

        System.out.print("logradouro: ");
        String logradouro = sc.nextLine();

        int numero;
        while (true) {
            try {
                System.out.print("numero: ");
                numero = sc.nextInt();
                break;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Numero invalido!");
            }
        }

        sc.nextLine();

        System.out.print("complemento: ");
        String complemento = sc.nextLine();

        System.out.print("bairro: ");
        String bairro = sc.nextLine();

        System.out.print("cidade: ");
        String cidade = sc.nextLine();

        System.out.print("uf: ");
        String uf = sc.nextLine();

        System.out.print("cep: ");
        String cep = sc.nextLine();

        cadastroController.armazenarEndereco(email, logradouro, numero, complemento, bairro, cidade, uf, cep);
        formularioPerfil(email);
    }

    public void formularioPerfil(String email) {
        System.out.println("------Formulario Perfil------");

        System.out.print("bibliografia: ");
        String bibliografia = sc.nextLine();

        System.out.print("disponibilidade: ");
        String disponibilidade = sc.nextLine();

        System.out.print("preferencias: ");
        String preferencias = sc.nextLine();

        cadastroController.armazenarPerfil(email, bibliografia, disponibilidade, preferencias);

        boolean insert = true;
        while (insert) {
            System.out.println("------Habilidades e Interesses---- ");
            System.out.println("[0] - Continuar");
            System.out.println("[1] - Adicionar Habilidade");
            System.out.println("[2] - Adicionar Interesse");

            int choice = sc.nextInt();
            sc.nextLine();

            String nome;
            String descricao;

            switch (choice) {
                case 1:
                    System.out.print("nome: ");
                    nome = sc.nextLine();

                    System.out.print("descricao: ");
                    descricao = sc.nextLine();

                    cadastroController.armazenarHabilidade(email, nome, descricao);
                    break;

                case 2:
                    System.out.print("nome: ");
                    nome = sc.nextLine();

                    System.out.print("descricao: ");
                    descricao = sc.nextLine();

                    cadastroController.armazenarInteresse(email, nome, descricao);
                    break;

                case 0:
                    insert = false;
                    break;

            }
        }

        formularioTermo(email);
    }

    public void formularioTermo(String email) {

        String termo = cadastroController.buscarTermo();
        System.out.println(termo);
        System.out.println("Voce concorda com os termos acima?");
        System.out.println("[0] - Nao");
        System.out.println("[1] - Sim");

        int option;

        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 0:
                cadastroController.cadastrarTermo(email, false);
                System.out.println("Voce foi bloqueado!");
                cadastroController.mudarStatus(email, "bloqueado");
                break;
            case 1:
                cadastroController.cadastrarTermo(email, true);
                confirmarEmail(email);
                break;

        }
    }

    public void confirmarEmail(String email) {
        System.out.println("-----Confirmar Email-----");

        int token_correto = emailController.enviarToken(email);
        int token;

        while (true) {
            try {
                System.out.println("Digite o token enviado ao seu email: ");
                token = sc.nextInt();
                break;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Token no formato invalido!");
            }
        }

        while (token != token_correto) {
            System.out.println("Errado! Um novo token foi enviado");
            token_correto = emailController.enviarToken(email);
            while (true) {
                try {
                    System.out.println("Digite o token enviado ao seu email: ");
                    token = sc.nextInt();
                    break;
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Token no formato invalido!");
                }
            }
        }

        System.out.println("Registrado com sucesso! Aguarde recomendaçoes");
        cadastroController.mudarStatus(email, "pendente");
    }

}

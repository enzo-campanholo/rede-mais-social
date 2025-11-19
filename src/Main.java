import database.Database;
import dao.*;
import controllers.CadastroController;
import controllers.EmailController;
import views.CandidatoView;
import models.Termo;
import models.TermoItem;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Inicializar o banco de dados
        Database db = Database.getInstance();
        db.init("./init.sql");
        Connection connection = db.getConnection();

        // Inicializar DAOs
        CandidatoDAO candidatoDAO = new CandidatoDAO(connection);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(connection);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection);
        RepresentanteDAO representanteDAO = new RepresentanteDAO(connection);
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
        PerfilVoluntarioDAO perfilVoluntarioDAO = new PerfilVoluntarioDAO(connection);
        HabilidadeDAO habilidadeDAO = new HabilidadeDAO(connection);
        InteresseDAO interesseDAO = new InteresseDAO(connection);
        TermoDAO termoDAO = new TermoDAO(connection);
        TermoItemDAO termoItemDAO = new TermoItemDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        TokenValidacaoEmailDAO tokenDAO = new TokenValidacaoEmailDAO(connection);

        // Inicializar os controladores
        CadastroController cadastroController = new CadastroController(
            candidatoDAO, pessoaFisicaDAO, pessoaJuridicaDAO, representanteDAO,
            enderecoDAO, perfilVoluntarioDAO, habilidadeDAO, interesseDAO,
            termoDAO, pedidoDAO
        );
        EmailController emailController = new EmailController(candidatoDAO, tokenDAO);

        // Inicializar dados
        init_termo(termoDAO, termoItemDAO);

        // Inicializar a view
        CandidatoView candidatoView = new CandidatoView(cadastroController, emailController);
        candidatoView.inicio();
    }

    public static void init_termo(TermoDAO termoDAO, TermoItemDAO termoItemDAO) {
        String versao = "1.0";
        String conteudo = "Termos... ";

        try {
            if (termoDAO.findByVersao(versao) == null) {
                Termo termo = new Termo(versao, conteudo);
                termoDAO.create(termo);
                
                Termo savedTermo = termoDAO.findByVersao(versao);
                if (savedTermo != null) {
                    int id = savedTermo.getId();
                    String[] condicoes = { "condicao 1", "condicao 2", "condicao 3" };

                    for (String condicao : condicoes) {
                        TermoItem item = new TermoItem(id, condicao);
                        termoItemDAO.create(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

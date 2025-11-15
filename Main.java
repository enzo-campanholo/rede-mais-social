import models.Model;
import models.Termo;
import models.TermoItem;
import views.Candidato;

public class Main {
  public static void main(String[] args) {

    init_db();
    init_termo();

    Candidato.inicio();
  }
 
  public static void init_db() {
    String url = "jdbc:sqlite:database.db";
    String init_sql = "./init.sql";

    Model.init(url, init_sql);
  }

  public static void init_termo() {
    String versao = "1.0";
    String conteudo = "Termos... ";

    if (Termo.busca(versao) == null) {
      Termo.criar(versao, conteudo);
      int id = 1;
      try {
        id = Termo.busca(versao).getInt("id");
      } catch (Exception e) {
        e.printStackTrace();
      }

      String[] condicoes = { "condicao 1", "condicao 2", "condicao 3" };

      for (String condicao : condicoes) {
        TermoItem.criar(id, condicao);
      }
    }

  }
}

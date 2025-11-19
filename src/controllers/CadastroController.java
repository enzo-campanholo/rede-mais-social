package controllers;

import dao.*;
import models.*;
import java.sql.Date;
import java.sql.SQLException;

public class CadastroController {
    private CandidatoDAO candidatoDAO;
    private PessoaFisicaDAO pessoaFisicaDAO;
    private PessoaJuridicaDAO pessoaJuridicaDAO;
    private RepresentanteDAO representanteDAO;
    private EnderecoDAO enderecoDAO;
    private PerfilVoluntarioDAO perfilVoluntarioDAO;
    private HabilidadeDAO habilidadeDAO;
    private InteresseDAO interesseDAO;
    private TermoDAO termoDAO;
    private PedidoDAO pedidoDAO;

    public CadastroController(CandidatoDAO candidatoDAO, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO,
                              RepresentanteDAO representanteDAO, EnderecoDAO enderecoDAO, PerfilVoluntarioDAO perfilVoluntarioDAO,
                              HabilidadeDAO habilidadeDAO, InteresseDAO interesseDAO, TermoDAO termoDAO, PedidoDAO pedidoDAO) {
        this.candidatoDAO = candidatoDAO;
        this.pessoaFisicaDAO = pessoaFisicaDAO;
        this.pessoaJuridicaDAO = pessoaJuridicaDAO;
        this.representanteDAO = representanteDAO;
        this.enderecoDAO = enderecoDAO;
        this.perfilVoluntarioDAO = perfilVoluntarioDAO;
        this.habilidadeDAO = habilidadeDAO;
        this.interesseDAO = interesseDAO;
        this.termoDAO = termoDAO;
        this.pedidoDAO = pedidoDAO;
    }

    public int verificarExistencia(String email) {
        try {
            if (candidatoDAO.findByEmail(email) == null) {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarPessoaFisica(String email, String cpf, String nome, String sexo, Date data_nascimento,
                                     String nacionalidade, String profissao) {
        try {
            Candidato candidato = new Candidato(email, "password", "em_progresso");
            candidatoDAO.create(candidato);
            
            Candidato savedCandidato = candidatoDAO.findByEmail(email);
            if (savedCandidato != null) {
                PessoaFisica pf = new PessoaFisica(savedCandidato.getId(), nome, cpf, sexo, data_nascimento, nacionalidade, profissao);
                pessoaFisicaDAO.create(pf);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarPessoaJuridica(String email, String cnpj, String razao_social) {
        try {
            Candidato candidato = new Candidato(email, "password", "em_progresso");
            candidatoDAO.create(candidato);

            Candidato savedCandidato = candidatoDAO.findByEmail(email);
            if (savedCandidato != null) {
                PessoaJuridica pj = new PessoaJuridica(savedCandidato.getId(), cnpj, razao_social);
                pessoaJuridicaDAO.create(pj);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarRepresentante(String nome, String email, String doc_identificacao) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                PessoaJuridica pj = pessoaJuridicaDAO.findByCandidatoId(candidato.getId());
                if (pj != null) {
                    Representante rep = new Representante(pj.getId(), nome, doc_identificacao);
                    representanteDAO.create(rep);
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarEndereco(String email, String logradouro, int numero, String complemento,
                                 String bairro, String cidade, String uf, String cep) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                Endereco endereco = new Endereco(candidato.getId(), logradouro, numero, complemento, bairro, cidade, uf, cep);
                enderecoDAO.create(endereco);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarPerfil(String email, String bibliografia, String disponibilidade, String preferencias) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                PerfilVoluntario perfil = new PerfilVoluntario(candidato.getId(), bibliografia, disponibilidade, preferencias);
                perfilVoluntarioDAO.create(perfil);
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarHabilidade(String email, String nome, String descricao) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                PerfilVoluntario perfil = perfilVoluntarioDAO.findByCandidatoId(candidato.getId());
                if (perfil != null) {
                    Habilidade habilidade = new Habilidade(perfil.getId(), nome, descricao);
                    habilidadeDAO.create(habilidade);
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int armazenarInteresse(String email, String nome, String descricao) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                PerfilVoluntario perfil = perfilVoluntarioDAO.findByCandidatoId(candidato.getId());
                if (perfil != null) {
                    Interesse interesse = new Interesse(perfil.getId(), nome, descricao);
                    interesseDAO.create(interesse);
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String buscarTermo() {
        try {
            Termo termo = termoDAO.findByVersao("1.0");
            if (termo != null) {
                return termo.getConteudo();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cadastrarTermo(String email, boolean aceito) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            Termo termo = termoDAO.findByVersao("1.0");
            if (candidato != null && termo != null) {
                Date date = Date.valueOf("2000-10-10");
                Pedido pedido = new Pedido(candidato.getId(), termo.getId(), date, aceito);
                pedidoDAO.create(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mudarStatus(String email, String status) {
        try {
            Candidato candidato = candidatoDAO.findByEmail(email);
            if (candidato != null) {
                candidatoDAO.updateEstadoAfiliacao(candidato.getId(), status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

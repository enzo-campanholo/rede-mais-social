CREATE TABLE IF NOT EXISTS Candidato (
  id INT PRIMARY KEY,
  senha TEXT NOT NULL,
  estadoAfiliacao TEXT NOT NULL,
  dataAtualizacao DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS MotivoRejeicao (
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  descricao TEXT NOT NULL,
  dataRejeicao DATETIME NOT NULL,
  tipo TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS NotificacaoEmail (
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  destinatario TEXT NOT NULL,
  assunto TEXT NOT NULL,
  conteudo TEXT NOT NULL,
  tipo TEXT NOT NULL,
  data_envio DATETIME NOT NULL,
  status TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS TokenValidacaoEmail (
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  token TEXT NOT NULL,
  expira_em DATETIME NOT NULL,
  usado BOOL NOT NULL
);

CREATE TABLE IF NOT EXISTS Endereco(
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  logradouro TEXT NOT NULL,
  numero INT NOT NULL,
  complemento TEXT NOT NULL,
  bairro TEXT NOT NULL,
  cidade TEXT NOT NULL,
  uf TEXT NOT NULL,
  cep TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Pedido(
  candidato_id INT REFERENCES Candidato(id),
  termo_id INT REFERENCES Termo(id),
  data_aceite DATETIME NOT NULL,
  aceito BOOL NOT NULL
);

CREATE TABLE IF NOT EXISTS Termo(
  id INT PRIMARY KEY NOT NULL,
  versao TEXT NOT NULL,
  conteudo TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS TermoItem(
  termo_id INT REFERENCES Termo(id),
  id INT PRIMARY KEY,
  condicao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS PerfilVoluntario(
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  bibliografia TEXT NOT NULL,
  disponibilidade TEXT NOT NULL,
  preferencias TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Habilidade(
  id_perfil INT REFERENCES PerfilVoluntario(id),
  id INT PRIMARY KEY,
  nome TEXT NOT NULL,
  descricao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Interesse(
  id_perfil INT REFERENCES PerfilVoluntario(id),
  id INT PRIMARY KEY,
  nome TEXT NOT NULL,
  descricao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS PessoaFisica(
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  email TEXT NOT NULL,
  nome TEXT NOT NULL,
  sexo TEXT NOT NULL,
  data_nascimento DATE NOT NULL,
  nacionalidade TEXT NOT NULL,
  profissao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS PessoaJuridica(
  candidato_id INT REFERENCES Candidato(id),
  id INT PRIMARY KEY,
  cnpj TEXT NOT NULL,
  razao_social TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Certidao(
  candidato_id INT REFERENCES PessoaJuridica(id),
  id INT PRIMARY KEY,
  tipo TEXT NOT NULL,
  nome_arquivo TEXT NOT NULL,
  caminho TEXT NOT NULL,
  data_upload DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS Representante(
  candidato_id INT REFERENCES PessoaJuridica(id),
  id INT PRIMARY KEY,
  nome TEXT NOT NULL,
  email TEXT NOT NULL,
  doc_identificacao TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS Voluntario(
  candidato_id INT REFERENCES Candidato(id),
  ong_id INT REFERENCES Ong(id),
  papel TEXT NOT NULL,
  ativo BOOL NOT NULL
);

CREATE TABLE IF NOT EXISTS Ong(
  auditoria_id INT REFERENCES Auditoria(id),
  id INT PRIMARY KEY,
  nome TEXT NOT NULL,
  cnpj TEXT NOT NULL,
  descricao TEXT NOT NULL,
  localidade TEXT NOT NULL,
  missao TEXT NOT NULL,
  ativa BOOL NOT NULL
);

CREATE TABLE IF NOT EXISTS Vaga(
  ong_id INT REFERENCES Ong(id),
  id INT PRIMARY KEY,
  cargo TEXT NOT NULL,
  periodo TEXT NOT NULL,
  Perfil TEXT NOT NULL
  );

  CREATE TABLE IF NOT EXISTS Credencial(
    ong_id INT REFERENCES Ong(id),
    id INT PRIMARY KEY,
    login TEXT NOT NULL,
    senha_temporaria TEXT NOT NULL,
    data_expiracao DATETIME NOT NULL,
    primeiro_acesso BOOL NOT NULL
  );

  CREATE TABLE IF NOT EXISTS Auditoria(
    id INT PRIMARY KEY,
    usuario TEXT NOT NULL,
    acao TEXT NOT NULL,
    data_hora DATETIME,
    entidade TEXT NOT NULL,
    deatlhes TEXT NOT NULL,
    ip TEXT NOT NULL
  );

    CREATE TABLE IF NOT EXISTS Campanha(
    ong_id INT REFERENCES Ong(id),
    id INT PRIMARY KEY,
    titulo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    local TEXT NOT NULL,
    status TEXT NOT NULL
  );

  CREATE TABLE IF NOT EXISTS Necessidade(
    campanha_id INT REFERENCES Campanha(id),
    id INT PRIMARY KEY,
    descricao TEXT NOT NULL,
    tipo TEXT NOT NULL,
    prioridade TEXT NOT NULL
  );

  CREATE TABLE IF NOT EXISTS Causa(
    campanha_id INT REFERENCES Campanha(id),
    id INT PRIMARY KEY,
    nome TEXT NOT NULL,
    categoria TEXT NOT NULL,
    descricao TEXT NOT NULL
  );


  CREATE TABLE IF NOT EXISTS RepresentanteRs(
    auditoria_id INT REFERENCES Auditoria(id),
    id INT PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    cargo TEXT NOT NULL
  );

  CREATE TABLE IF NOT EXISTS Recomendacao(
    candidato_id INT REFERENCES Candidato(id),
    representante_rs_id INT REFERENCES RepresentanteRs(id),
    data_geracao DATETIME NOT NULL,
    algoritmo TEXT NOT NULL,
    score_aderencia DOUBLE NOT NULL,
    status TEXT NOT NULL

  );

CREATE DATABASE db_portfolio_transportadoras
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;

-- Table: portfolio.tb_transportadora

-- DROP TABLE portfolio.tb_transportadora;

CREATE TABLE portfolio.tb_transportadora
(
  nome character varying(25),
  email character varying(30),
  empresa character varying(30),
  telefone character varying(15),
  celular character varying(15),
  whatsapp character varying(15),
  modal character varying(15),
  cep character varying(8),
  estado character varying(2),
  cidade character varying(30),
  bairro character varying(30),
  ruaavenida character varying(30),
  numero integer,
  id integer NOT NULL DEFAULT nextval('portfolio.transportadora_id_seq'::regclass),
  logo bytea
)
WITH (
  OIDS=FALSE
);
ALTER TABLE portfolio.tb_transportadora
  OWNER TO postgres;

-- Table: portfolio.transportadora

-- DROP TABLE portfolio.transportadora;

CREATE TABLE portfolio.transportadora
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
  id serial NOT NULL,
  logo bytea
)
WITH (
  OIDS=FALSE
);
ALTER TABLE portfolio.transportadora
  OWNER TO postgres;

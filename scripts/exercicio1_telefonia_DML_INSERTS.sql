INSERT INTO ENDERECO (cep, estado, cidade, bairro, numero, rua) VALUES ('43294870','SC','São José','Centro','189','Rua 51');
INSERT INTO ENDERECO (cep, estado, cidade, bairro, numero, rua) VALUES ('38476242','SC','Lages','Centro','515','Rua  79');
INSERT INTO ENDERECO (cep, estado, cidade, bairro, numero, rua) VALUES ('78529663','SC','Florianópolis','Centro','804','Rua  81');
INSERT INTO ENDERECO (cep, estado, cidade, bairro, numero, rua) VALUES ('39152270','SC','Lages','Centro','640','Rua 28');
INSERT INTO ENDERECO (cep, estado, cidade, bairro, numero, rua) VALUES ('78015242','SC','Florianópolis','Centro','126','Rua  6');
INSERT INTO ENDERECO (cep, estado, cidade, bairro, numero, rua) VALUES ('41857751','SC','Chapecó','Centro','125','Rua 66');

INSERT INTO CLIENTE (nome, sobrenome, cpf, idEndereco) VALUES ('Edson','Arantes','11133322211', 2);
INSERT INTO CLIENTE (nome, sobrenome, cpf, idEndereco) VALUES ('Artur','Antunes','22233322211', 4);
INSERT INTO CLIENTE (nome, sobrenome, cpf, idEndereco) VALUES ('Marcos','André','11133322255', 6);

INSERT INTO TELEFONE (codigoPais, ddd, numero, movel, ativo, idCliente) VALUES ('55','48','32323232', '0', '1', 2);
INSERT INTO TELEFONE (codigoPais, ddd, numero, movel, ativo, idCliente) VALUES ('55','11','99888888', '1', '1', 2);
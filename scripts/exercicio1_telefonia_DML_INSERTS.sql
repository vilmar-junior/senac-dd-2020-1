INSERT INTO exemplos.ENDERECO (id, cep, estado, cidade, bairro, numero, rua) VALUES (1, '43294870','SC','São José','Centro','189','Rua 51');
INSERT INTO exemplos.ENDERECO (id,cep, estado, cidade, bairro, numero, rua) VALUES (2,'38476242','SC','Lages','Centro','515','Rua  79');
INSERT INTO exemplos.ENDERECO (id,cep, estado, cidade, bairro, numero, rua) VALUES (3,'78529663','SC','Florianópolis','Centro','804','Rua  81');
INSERT INTO exemplos.ENDERECO (id,cep, estado, cidade, bairro, numero, rua) VALUES (4,'39152270','SC','Lages','Centro','640','Rua 28');
INSERT INTO exemplos.ENDERECO (id,cep, estado, cidade, bairro, numero, rua) VALUES (5,'78015242','SC','Florianópolis','Centro','126','Rua  6');
INSERT INTO exemplos.ENDERECO (id,cep, estado, cidade, bairro, numero, rua) VALUES (6,'41857751','SC','Chapecó','Centro','125','Rua 66');

INSERT INTO exemplos.CLIENTE (id,nome, sobrenome, cpf, idEndereco, dataNascimento) VALUES (1,'Edson','Arantes','11133322211', 2, '1940-08-10');
INSERT INTO exemplos.CLIENTE (id,nome, sobrenome, cpf, idEndereco, dataNascimento) VALUES (2,'Artur','Antunes','22233322211', 4, '1960-05-12');
INSERT INTO exemplos.CLIENTE (id,nome, sobrenome, cpf, idEndereco, dataNascimento) VALUES (3,'Marcos','André','11133322255', 6, '1980-09-21');

INSERT INTO exemplos.TELEFONE (id,codigoPais, ddd, numero, movel, ativo, idCliente) VALUES (1,'55','48','32323232', '0', '1', 2);
INSERT INTO exemplos.TELEFONE (id,codigoPais, ddd, numero, movel, ativo, idCliente) VALUES (2,'55','11','99888888', '1', '1', 2);
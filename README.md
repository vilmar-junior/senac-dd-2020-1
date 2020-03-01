# senac-dd-2020-1
Repositório da disciplina de Desenvolvimento Desktop, semestre 2020/1

# Estrutura do projeto

O projeto está organizado conforme o modelo MVC, com os seguintes pacotes principais

1. model: contém as classes responsáveis por: 
   + **model.dao**: persistência dos dados em tabelas relacionais (Repository/Data Access Objects - DAOs)
   + **model.entity**: modelagem de tabelas relacionais em objetos (Entidades/Value Objects - VOs)
   + **model.bo**: encapsulamento de regras de negócio (Business Objects - BOs)
   + **model.dto**: criação de objetos com dados resumidos ou compostos de tabelas distinas, afim de simplificar o tráfego de dados para as telas (Data Transfer Objects - DTOs)


2. **controller**: compreende as classes que realizam a comunicação entre as chamadas de usuário nas telas e o seu correto processamento no backend na camada de modelo

3. **view**: contém as classes de Interface Gráfica de Usuário (GUI) da aplicação. Neste projeto, todas as classes serão desenvolvidas em Java Swing.

# Organização do conteúdo ao longo da disciplina

Este projeto inicia com todas as classes oriundas do projeto SistemaAviso, criado na disciplina de POO no semestre 2019/2.

Exemplos de código e resoluções de exercícios também serão incluídas no projeto, da seguinte forma (considerando o exercício 1):

+ **model.dao.exercicio1**
+ **model.entity.exercicio1**
+ **model.bo.exercicio1**
+ **controller.exercicio1**
+ **view.exercicio1**

# Contato
+ vilmarcesarpereira@gmail.com
+ vilmar.junior@prof.sc.senac.br

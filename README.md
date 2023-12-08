<h1>carrinhoTeste</h1>

repositório destinado a atividade final da disciplina de Gerência De Configuração e Teste de Software

<h3>Dependências do projeto: </h4>
Java17   
Docker  
Intellij

<h3>Como rodar o Projeto: </h4>

Entrar no diretório do projeto pelo terminal e rodar o docker para subir os containers.(docker-compose up)   
Após isso rodar o arquivo main do projeto.(no intellij usar o RUN)

<h3>Como rodar os Testes: </h4>
Clicar com o botão direito no pacote principal dos testes e usar a opção Run 'Tests'.( no intellij )

<h3>Como verificar a cobertura dos testes:</h3>

Acessar o arquivo localizado em > target/site/jacoco/index.html ( utilizando um broswer ).

O jacoco gera automáticamente um relatório descrevendo a porcentagem da cobertura de testes de cada classe.

para gerar um novo relatório utilizando o jacoco é necessário utilizar o comando **mvn jacoco:report**

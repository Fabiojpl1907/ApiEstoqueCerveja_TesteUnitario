# Estoque de Cerveja - Teste Unitário



Projeto para aprender a testar, unitariamente, uma API REST para o gerenciamento de estoques de cerveja. Teste sempre dá um feedback rápido , seja positivo ou negativo , ai cabe a nós inspecionar e adaptar . 

Construir testes unitários para validar o nosso sistema de gerenciamento de estoques de cerveja desenvolvido em Spring Boot, 

Criar testes unitários com JUnit ,Mockito e Hmcrest,  e  como desenvolver funcionalidades através da prática do TDD.

O codigo base da API foi desenvolvido pelo professor Rodrigo Peleias e se encontra em : github.com/rpeleias/beer_api_digital_innovation_one

`Durante a implantação dos testes são colocados comentários nos códigos para registrar e consolidar os conhecimentos.` 

O projeto roda a partir de  -> http://localhost:8080/api/v1/beers

As seguintes tecnologias  são utilizadas 

​	Java versão 17 / Intellij / Spring Boot / Maven / Lombok / Mapstructure



A seguir temos 

1. Principais conceitos e framework utilizados na arquitetura de teste , 
2. Mapeamento das implantações  de  teste  		

### *Conceitos*  

#### **Teste Unitários** 

O teste unitário, se baseia no conceito que , não devemos esperar ter todo o produto pronto para então testar, isto podeia ser um desatre,  ao invés,  se concentra na verificação da menor unidade do projeto de software. É realizado o teste de uma unidade lógica, com uso de dados suficientes para se testar apenas à lógica da unidade em questão.

Os principais fatores que motivam o uso sistemático da prática de testes unitários:

​			Previne contra o aparecimento de “BUG’S” oriundos de códigos mal escritos;
​					Código testado é mais confiável;
​					Permite alterações sem medo (coragem);
​					Testa situações de sucesso e de falha;
​					Resulta em outras práticas  como: Código coletivo, refatoração, integração contínua;
​					Serve como métrica do projeto (teste ==requisitos);
​					Gera e preserva um “conhecimento” sobre as regras de negócios do projeto.

Quando fazer Teste Unitário?
				No início: Primeiro projetar e escrever as classes de testes, depois as classes com regra de negócios.

​		Diariamente:  Rode testes várias vezes ao dia (é fácil corrigir pequenos problemas do que corrigir um “problemão” somente no final do projeto).

​	

#### **BDD** 

"Give / When / Then" é um estilo de representar testes - ou  especificar  o comportamento de um sistema usando "SpecificationByExample" . É uma abordagem desenvolvida por Daniel Terhorst-North e Chris Matts como parte do Behavior-Driven Development (BDD).  Pode ser considerada  uma reformulação do padrão de teste de quatro fases .

A ideia base é dividir a escrita de um cenário (ou teste) em três seções:

1. Given:  descreve o estado do mundo antes de começar o comportamento que está especificando neste cenário ( when) . Pode pensar como as pré-condições para o teste.
2. When :  é o comportamento sendo especificado.
3.  Then descreve as mudanças esperadas devido ao comportamento especificado.



#### **TDD** 

**TDD** -**Test Driven Development**, ou *Desenvolvimento Orientado a Testes*. 

A ideia do TDD é trabalhar em ciclos,  na seguinte ordem:

- Primeiro, escreva um teste unitário que inicialmente irá falhar, tendo em vista que o código ainda não foi implementado;
- Crie o código que satisfaça esse teste, ou seja: implemente a funcionalidade em questão. Essa primeira implementação deverá satisfazer imediatamente o teste que foi escrito no ciclo anterior;
- Quando o código estiver implementado e o teste satisfeito, refatore o código para melhorar pontos como legibilidade. Logo após, execute o teste novamente. A nova versão do código também deverá passar sem que seja necessário modificar o teste escrito inicialmente.

 TDD não é uma forma de escrever teste: ele é uma metodologia para desenvolvimento e escrita código. 

#### **DTO** 

“Um objeto que transporta dados entre processos para reduzir o número de chamadas de método.” 

Data Transfer Object (DTO) ou Transfer Object é um padrão de projetos ( Projet Partners) para o transporte de dados entre diferentes componentes de um sistema, diferentes instâncias ou processos de um sistema distribuído ou diferentes sistemas via serialização.

A ideia consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.

O DTO é uma forma de desenvolver um aplicação e não é diretamente ligado a teste, porem, uUtilizar este conceito ira facilitar a criações dos testes. 



### ***Os Frameworks de testes utilizados no projeto*** 

#### JUnit  

Framework que facilita o desenvolvimento e execução de testes unitários em código Java.

Os principais motivos que favorecem o uso desse framework são:

​	JUnit pode verificar se cada unidade de código funciona da forma esperada;
​			Facilita a criação, execução automática de testes e a apresentação dos resultados;
​			É Orientado a Objeto;
​			É free e pode ser baixado em: www.junit.org

#### Mockito  

Uma das características fundamentais de um bom teste unitário é ser isolado. Para que ele execute rápido, fornecendo feedback ao desenvolvedor, ele deve ser isolado. Quando digo isolado é que ele deve ser isolado de dependências externas: acesso à rede, ao sistema de arquivos, a banco de dados etc. É aí que os mocks podem nos ajudar e que o Mockito entra em ação.

Mocks são objetos criados para simular, de forma controlada, determinados comportamentos de objetos reais. Por forma controlada entenda: como bem quisermos ou desejarmos.

O Mockito é uma estrutura de teste de código aberto para Java liberada sob a licença MIT. A estrutura permite a criação de objetos duplos de teste em testes de unidade automatizados com o objetivo de desenvolvimento orientado a teste ou desenvolvimento orientado a comportamento

#### Hamcrest  

O Hamcrest é um framework que possibilita a criação de regras de verificação (matchers) de forma declarativa.

Portanto a ideia é que com os matchers Hamcrest as asserções utilizadas expressem melhor a sua intenção, ficando mais legíveis e mais expressivas.

Um matcher Hamcrest é um objeto que

​			reporta se um dado objeto satisfaz um determinado critério;
​					pode descrever este critério; e
​					é capaz de descrever porque um objeto não satisfaz um determinado critério.

Os matchers Hamcrest, na prática, são utilizados com o método assertThat() do JUnit. Esse método utiliza as funcionalidades auto-descritivas dos matchers para deixar claro exatamente o que ocorreu de errado em caso de uma asserção falhar. 



### *Divisão de desenvolvimento e comentários*   

O desenvolvimento e comentários  segue a divisão utilizada nos videos de apresentação do projeto na Dio.  Ver comentários mais direcionados nos proprios códigos. 

### **Parte 1, 2**,3  

Teste - Criar Cerveja - Cadastro tem sucesso ou não - post()

codigo em : 		../test/beerServiceTest.java

Usando padrão BDD ->  given ( dados e entidades )  /   when    /    then 

```
// testando quantidade com hamcrest
// Hamcrest permite verificar se valor é maior que outro
// se um objeto é igual ao outro
// vai alem do equals(), tem diversos tipos de validações 
```

​	

### Parte 4,5  

Teste - controler 

codigo em : 		../test/beerControllerTest.java

trabalha com Postman , como um teste de integração .

Até aqui validamos regras de negócio : Criação de cerveja , Identificar que já esta cadastrada evitando duplicação e se falta algum campo obrigatório de cadastro 



​	

### Parte 6,7,8  

Teste - controler e service 

Busca por nome de Cerveja - get(), e tratar a exceção para quando uma cerveja nao registrada for pesquisada, simular quando encontra retono 400 - not found 

codigo em : 		

​		../test/beerServiceTest.java - teste nivel de serviço 

​		../test/beerControllerTest.java - teste em nível de operação, de retorno de código 200/400, indicando sucesso ou erro 



### **Parte 8**,9,10,11,12   

Testar listagem listAll() -> ou retorna lista ou uma lista vazia 

e exclusão cerveja cujo id seja válido .

codigo em : 		

​		../test/beerServiceTest.java - teste nivel de serviço emite lista com dados ou vazia

​		../test/beerControllerTest.java - teste em nível de operação, de retorno de código 204. 



```
Neste ponto esta construido todos os testes refentes aos metodos  de criar , pesquisar , listar e excluir 

Ao ir para uma Integração contínua , possivelemte ira chamar mvn clean test ( ou a ferramenta do gradle ) e ira verificar se todos os testes passam. 
```



### Parte 12,13, 14,Finalizando  - Extra    

Fazer teste de incremento ou decremento das cervejas, que controla estoque máximo ou mínimo das cervejas , bem como testar que rotina de controle para que a quantiade incrementada não ultrapasse o maximo de estoque previsto . 

Usando TDD , primeiro faz o teste e depois  codificar a funcionalidade que passe no teste

Inicia com o minimo de codigo para passe no teste e depois vai sendo melhorado

codigo em : 	

​		../test/beerServiceTest.java - teste nivel de serviço / regra de negócio 

​		../test/beerControllerTest.java - teste em nível de operação, de retorno de código 200/400, indicando sucesso ou erro 

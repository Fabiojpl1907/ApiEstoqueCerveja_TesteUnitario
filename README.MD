# Estoque de Cerveja - Teste Unitário



Projeto para aprender a testar, unitariamente, uma API REST para o gerenciamento de estoques de cerveja. Teste sempre dá um feedback rápido , seja positivo ou negativo , ai cabe a nós inspecionar e adaptar . 

Construir testes unitários para validar o nosso sistema de gerenciamento de estoques de cerveja desenvolvido em Spring Boot, 

Criar testes unitários com JUnit e Mockito, e  como desenvolver funcionalidades através da prática do TDD.

As seguintes atividades são implantadas ao transcorre do projeto 

- **Entender sobre testes unitários;**
- **Entender os principais frameworks para testes unitários;**
- **Desenvolver testes unitários para validação de funcionalides básicas: criação, listagem, consulta por nome e exclusão;**
- **TDD: apresentação e exemplo prático com 2 funcionaliades importantes.**

O codigo base da API foi desenvolvido pelo professor Rodrigo Peleias e se encontra em : github.com/rpeleias/beer_api_digital_innovation_one

Durante a implantação dos testes são colocados comentários nos códigos para registrar e consolidar os conhecimentos. 

Durante a construção o projeto será colocado no GitHub, em branchs, e a cada fase completada com sucesso , será feito o merge na Branch  main. 

O projeto roda em -> http://localhost:8080/api/v1/beers

As seguintes tecnologias  são utilizadas 

​	Java versão 17
​			Intellij
​			Spring Boot
​			Maven
​			Lombok
​			Mapstructure
​			Frameworks de testes
​				JUnit
​				Mockito
​				Hamcrest



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

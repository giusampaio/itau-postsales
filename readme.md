<p align="center">
    <img width="256" src="assets/itau.png" alt="maestriam/samurai logo">
</p>

<h1 align="center">🏦 giusampaio/itau-postsales</h1>

<p align="center">
  API de aditamento de contrato para o teste de backend Itaú.
</p>
<br>


O **giusampaio/itau-postsales** é uma API que possui duas funcionalidades de aditamento de contratos de Itaú.
Nele é possível realizar alterações no dia de pagamento e na quantidade de parcelas.  

## Requisitos

- Java 11
- Spring Framework 3.1.2

## Mock da API de juros.
Para realizar algumas operações de aditamento, é necessário fazer uma integração com uma API fictícia de 
juros do Itaú via Feign Client. Atualmente, os valores dessa integração são retornados através de um Mock 
diretamente na implementação do sistema.   
No entanto, para realizar um teste mais realista, é possível executar uma API fake e simular uma chamada para a API de juros.

Para prosseguir com o teste, siga os seguintes comandos:
``` bash
// Em um terminal, entre dentro do diretório wiremock...
cd wiremock/    

// Execute o seguinte comando...
java -jar wiremock-jre8-standalone-2.35.0.jar --port 8082

// Comente a linha 24 e descomente a linha 25 FeesApiService
// Exemplo:
// return this.getMockResponse();
return this.feesClient.feeCalculation(request); 
```

## Adendos sobre escopo da API
Na documentação da API de aditamento, alguns exemplos e regras de negócios não ficaram tão claros gerando 
algumas dúvidas. Por esse motivo, abaixo estão algumas esclarecimentos sobre algumas implementações.


- No exemplo da documentação da API de alteração de quantidade de parcelas, há uma mudança do dia de 
  pagamento da parcela(Do dia 23 para o dia 4). Entretanto, não há uma explicação do motivo dessa mudança.  
  Por esse motivo, decidimos manter o valor antigo do dia de pagamento.  
- No exemplo da documentação da API de alteração de quantidade de parcelas, é apresentado um novo valor para a 
  parcela (de 1000.00 para 963.50), mas não há um exemplo de cálculo para chegar a esse valor.   
  Diante dessa lacuna, optamos por retornar o valor antigo da parcela.
- Na documentação da API de alteração de quantidade de parcelas, é informado que devemos retornar o novo 
  valor da porcentagem de juros da API Juros Itau, contudo, no exemplo fornecido, consta o juro antigo.  
  Por essa razão, decidimos retornar o valor atualizado de juros.
- No exemplo da documentação da API de alteração do dia de pagamento, também não é apresentado o novo 
  valor do dia de pagamento no retorno da chamada. Essa omissão gerou dúvidas sobre se a operação deve 
  realmente considerar a chamada ou apenas fazer uma cópia do retorno do endpoint de alteração da 
  quantidade de parcelas.    
  Devido a essa falta de clareza, os dados financeiros são mantidos iguais aos 
  dados da chamada, sendo apenas o dia de pagamento trocado pelo novo dia enviado no aditamento.

<br></br>  
Created by [Giuliano Sampaio](https://github.com/giusampaio) with ❤️ and ☕!
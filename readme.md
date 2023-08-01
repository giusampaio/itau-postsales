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
Para realizar algumas operações de aditamento, é necessário fazer uma integração com a API de juros.  
Para subir a API de juros para seguir os seguintes comandos. 
``` bash
cd wiremock/
java -jar wiremock-jre8-standalone-2.35.0.jar --port 8082
```

<br></br>  
Created by [Giuliano Sampaio](https://github.com/giusampaio) with ❤️ and ☕!
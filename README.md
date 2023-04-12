# Sistema de gerenciamento das minhas séries

    Este projeto foi realizado com o objetivo de aprimorar o conhecimento em 
    Spring na linguagem Java.

## :zap: Descrição

O objetivo deste projeto é desenvolver uma aplicação que permita o gerenciamento de séries, permitindo o cadastro, visualização, adição e remoção de episódios assistidos, bem como a visualização do tempo total gasto na visualização de episódios de todas as séries já assistidas. A ideia é proporcionar ao usuário um maior controle sobre as séries que já assistiu e quanto tempo dedicou a cada uma delas.


### 📌 Listagem dos endpoints (Casos de sucesso)

  🔵 `POST path = "/series":`  **Cadastro da série**
  
  * Corpo da requisição:
  ```json
  {
      "nome":"Doctor Who"
  }
  ```
  
  * Corpo da resposta:
  
  > Statuscode: 200(OK)
  
  ```json
  {
      "id": 1,
      "nome": "Doctor Who",
      "episodios": []
  }
  ```

  🔵 `GET path = "/series":` **Visualizar séries**

  * Corpo da resposta:
  
  > Statuscode: 200(OK)
  
  ```json
  [
      {
          "id": 1,
          "nome": "Doctor Who",
          "episodios": []
      },
      {
          "id": 2,
          "nome": "Friends",
          "episodios": []
      }
  ]
  ```
  
  🔵 `DELETE path = "/series/{serie_id}":`  **Remover séries**
    
  > Statuscode: 200(OK)
  

  🔵 `POST path = "/series/{serie_id}/episodios":` **Adicionar episódios**

  * Corpo da requisição:
  ```json
  {
    "numero": 1,
    "duracaoEmMinutos": 60
  }
  ```
  * Corpo da resposta:
    
  > Statuscode: 200(OK)
  
  ```json
  {
      "id": 1,
      "nome": "Doctor Who",
      "episodios": [
          {
              "id": 2,
              "numero": 1,
              "duracaoEmMinutos": 60
          }
      ]
  }
  ```
  
  🔵 `GET path = "/series/{serie_id}/episodios":` **Visualizar episódios de uma série**

  * Corpo da resposta:
    
  > Statuscode: 200(OK)
  
  ```json
  [
    {
      "id": 2,
      "numero": 1,
      "duracaoEmMinutos": 60
    }
  ]
  ```
  
 🔵 `GET path = "/series/tempo":` **Visualizar tempo gasto**

 * Corpo da resposta:
   
  > Statuscode: 200(OK)
  
  ```json
  {
    "tempoEmMinutos": 600
  }
  ```

### 📌 Casos de Falha
- Cadastro de série com nome existente deve emitir a exceção `SerieExistenteException`
- Tentativas de acesso a uma série que não exista deve emitir a exceção `SerieNaoEncontradaException`
- Adição de episódios com o mesmo número para a mesma série deve emitir a exceção `EpisodioExistenteException`
- Casos de erro não mapeados neste documento devem emitir a exceção `ErroInesperadoException` 


### ✖️ Mapeamento de exception para statusCode

Utilizei um `ControllerAdivice` para realizar o mapeamento das exceções conforme apresentado a seguir:  

  🔴 Exception: SerieExistenteException
  
  > StatusCode: 409
  
  * Corpo da resposta:
  ```json
  {
    "error": "Série Existente"
  }
  ```

  🔴 Excecption: EpisodioExistenteException

> StatusCode: 409

  * Corpo da resposta:
```json
{
  "error": "Episódio Existente"
}
```

  🔴 Exception: SerieNaoEncontradaException
  
> StatusCode: 404

  * Corpo da resposta:
```json
{
  "error": "Série não encontrada"
}
```

  🔴 Exception: ErroInesperadoException
  
> StatusCode: 500

  * Corpo da resposta:
```json
{
  "error": "Erro inesperado"
}
```

### ✖️ Tolerância a falhas:

Adicionei um `CircuitBreaker` utilizando a biblioteca `resilience4j` no endpoint `/series/{serie_id}/episodios`, fazendo com que, caso haja 50% de erro nas requisições ,o serviço retorne:

> StatusCode 503
  * Corpo da resposta:
```json
{
  "error": "Serviço temporariamente indisponível"
}
```


---

## 📌 Instalação do projeto em sua máquina
  1. Clone o repositório
   
     `git@github.com:analuisams99/minhas-series.git`
    
  2. Entre no diretório do repositório que você acabou de clonar:
  
     `cd minhas-series`
   
  3. Instale as dependências necessárias:
   
     `mvn install`
     
  

## 📌 Para testar o projeto
  1. digite o comando no seu terminal
      
      `mvn test`
      
    Os códigos de cobertura de testes, do arquivo src/test/java/com/trybe/acc/java/minhasSeries/CoverageValidationTest.java,
    foram desenvolvidos pela Trybe.
    
---

-- Projeto desenvolvido por Ana Luisa Marques Simões, para fins didáticos. 2023

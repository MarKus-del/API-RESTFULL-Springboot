# API RESTFULL  with Spring Boot

Este repositório contém um projeto de uma API RESTFULL usando o Framework Spring Boot para a linguagem Java

## Referencias

Esta api é um projeto pessoal para treinar meus conhecimentos com a plataforma Spring boot
com os conhecimentos obtidos através dos seguintes canais de aprendizagem:

- [Guias oficiais da plataforma Spring](https://spring.io/guides#topical-guides)
- [Digital Innovation One](https://digitalinnovation.one/)
- [Livro e videos da Michelli britto ](https://www.youtube.com/channel/UC2WbG8UgpPaLcFSNJYwtPow)
- [Documentação do Java no Docker](https://docs.docker.com/language/java/build-images/) 

## Sobre

Essa projeto contém uma API RESTFULL que cadastra produtos utilizando o padrão arquitetural rest
 com até a ultimo nivel de maturidade que é o Hypermedia.

OBS: a api não possui autenticação ou autorização.

Para saber mais sobre a api é possivel ver mais detalhes no endpoint da documentação do Swagger
 quando você rodar localmente através de http://localhost:8080/swagger-ui.html

É para rodar o projeto você pode baixar pelo github é rodar na sua ide favorita porém 
você deve enviar uma variavel de ambiente com o nome "PROFILE_ACTIVE" com os valores 'dev' ou 'prod',
com o perfil de 'dev' a aplicação ira utilizar o h2 database com db e com o perfil de 'prod' e preciso enviar as variaveis
de ambiente relacionadas ao banco postgres que você pretende usar. Porém para facilitar a execução da aplicação
se você estiver com o docker instala basta subir os container com o seguinte comando:
~~~bash
docker compose up -d
~~~
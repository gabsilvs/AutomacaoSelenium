# Automação de Pesquisa de ADAS - Carros

Este projeto realiza uma automação utilizando o Selenium para pesquisar informações sobre o sistema ADAS (Advanced Driver Assistance Systems) de carros específicos. Ele busca automaticamente informações relevantes na web sobre o modelo de carro informado e retorna os dados sobre assistências ao motorista disponíveis para esse modelo.

## Funcionalidades

- **Pesquisa automática no Google**: O programa realiza uma pesquisa no Google usando o nome do modelo do carro e o termo "sistema ADAS".
- **Busca por informações sobre ADAS**: Após os resultados da pesquisa, o programa acessa as páginas para coletar informações relacionadas ao sistema ADAS.
- **Exibição das informações**: O código coleta e exibe os resultados mais relevantes sobre as funcionalidades de assistência ao motorista para o modelo de carro especificado.

## Como Rodar o Projeto

### 1. Pré-requisitos

- **Java 8 ou superior**: O projeto foi desenvolvido usando Java.
- **Maven**: O Maven é utilizado para gerenciar dependências.
- **Selenium WebDriver**: O Selenium é utilizado para automação de navegação e extração de dados da web.
- **ChromeDriver**: O ChromeDriver é necessário para rodar o navegador Chrome. Certifique-se de ter o ChromeDriver na pasta correta (`src\\drive\\chromedriver.exe`).


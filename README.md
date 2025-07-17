# Screen Match 🎬

O Screen Match é uma aplicação de console desenvolvida em Java com Spring Boot. O projeto consome a API do OMDB (Open Movie Database) para buscar informações detalhadas sobre séries de TV, incluindo dados gerais, temporadas e uma lista completa de episódios.

## ✨ Funcionalidades

- Busca de séries por título através do console.
- Exibição de informações detalhadas da série (título, total de temporadas, avaliação IMDb).
- Listagem de todas as temporadas e seus respectivos episódios de forma organizada.
- Interface de linha de comando interativa.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**: Framework principal para a estrutura da aplicação e execução.
- **Maven**: Gerenciador de dependências.
- **Jackson Databind**: Biblioteca para conversão (desserialização) de JSON para objetos Java (Records).
- **OMDB API**: Fonte de dados para filmes e séries.

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior.
- Maven 3.x.
- Uma chave de API da [OMDB](http://www.omdbapi.com/apikey.aspx). É gratuito e rápido de obter.

### Configuração
1.  Clone este repositório para a sua máquina.
2.  Abra o projeto na sua IDE preferida (ex: IntelliJ IDEA).
3.  Navegue até o arquivo `src/main/java/br/com/gilvaneide/screenmatch/principal/Principal.java`.
4.  Localize a constante `APIC_KEY` e substitua o valor `"&apikey=9de8e5d7"` pela sua chave pessoal.
    ```java
    private final String APIC_KEY = "&apikey=SUA_CHAVE_AQUI";
    ```
5.  Execute a aplicação a partir da classe `ScreenmatchApplication.java` (clicando com o botão direito e selecionando "Run").
6.  A aplicação iniciará no seu terminal. Siga as instruções para buscar uma série.

## 🏛️ Estrutura do Projeto

O projeto está organizado em pacotes que separam as responsabilidades:

-   `br.com.gilvaneide.screenmatch`:
    -   `ScreenmatchApplication.java`: A classe de entrada (`@SpringBootApplication`) que inicializa o Spring e chama a classe `Principal`.

-   `principal`:
    -   `Principal.java`: Orquestra a lógica principal da aplicação, lida com a entrada do usuário e exibe os resultados. É aqui que as dependências `ConsumoApi` e `ConverteDados` são instanciadas.

-   `service`: Contém os serviços da aplicação.
    -   `ConsumoApi.java`: Responsável por fazer as chamadas HTTP para a API externa. Possui um método `run` (de `CommandLineRunner`) para testes iniciais.
    -   `IConverteDados.java`: Interface que define o contrato para conversores de dados.
    -   `ConverteDados.java`: Implementação que usa a biblioteca Jackson para converter o JSON recebido em objetos Java.

-   `model`: Contém os `Records` (DTOs - Data Transfer Objects) que modelam os dados recebidos da API.
    -   `DadosSerie.java`: Modela os dados gerais de uma série.
    -   `DadosTemporada.java`: Modela os dados de uma temporada, que contém uma lista de episódios.
    -   `DadosEpisodio.java`: Modela os dados de um único episódio.
    -   `AvaliacaoDeserializada.java`: Um desserializador customizado para o Jackson, usado para tratar o campo de avaliação (`imdbRating`) que pode vir como "N/A" na API.

---

## Contexto do Projeto

O projeto foi desenvolvido durante o curso **"Java: trabalhando com lambdas, streams e Spring Framework"**, que faz parte da especialização Back-End, do **Programa ONE (Oracle Next Education)** em parceria com a **Alura**.

**Instrutoras:** Jacqueline Oliveira e Iasmin Araújo.
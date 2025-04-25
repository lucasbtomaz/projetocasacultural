## Contexto
Trabalho para a disciplina de **Programar aplicativos computacionais com integração de banco de dados para web. (back-end)** do curso técnico no Senac onde é simulado que recebi uma nova demanda de uma casa cultural da cidade que conta com uma sala de cinema. 

Com o **objetivo** de montar um site para o estabelecimento para divulgar as ações, além de ter um espaço em que as pessoas possam registrar os filmes a que assistiram e avaliar cada um deles (fornecer análises). 

**Nota:** Para fins de testes iniciais, o desenvolvimento deve ser feito utilizando apenas armazenamento em memória (sem banco de dados).

## 📁 Estrutura do Projeto

projeto-casa-cultural/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cinema/
│   │   │           └── projetocasacultural/
│   │   │               ├── controller/
│   │   │               │   ├── HomeController.java
│   │   │               │   ├── FilmeController.java
│   │   │               │   └── AnaliseController.java
│   │   │               └── model/
│   │   │                   ├── Filme.java
│   │   │                   └── Analise.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   ├── bootstrap.min.css
│   │       │   │   └── estilo.css
│   │       │   ├── js/
│   │       │   │   └── bootstrap.bundle.min.js
│   │       │   └── image/
│   │       │       ├── casacultural.jpeg
│   │       │       └── rodape.jpeg
│   │       └── templates/
│   │           ├── index.html
│   │           ├── filmes/
│   │           │   ├── cadastro.html
│   │           │   ├── lista.html
│   │           │   └── detalhes.html
│   │           └── analises/
│   │               └── formulario.html
└── pom.xml

## 🌐 Rotas e Componentes Associados

1. Página Inicial

* URL: /inicio
* HTML: index.html
* CSS: estilo.css
* Controller: HomeController.java

**Descrição:** Página de boas-vindas com informações sobre a Casa Cultural e imagens promocionais.

2. Cadastrar Filme

* URL: /filmes/novo
* HTML: filmes/cadastro.html
* CSS: estilo.css
* Controller: FilmeController.java
* Model: Filme.java

**Descrição:** Formulário para adicionar um novo filme ao sistema.

3. Listar Filmes

* URL: /filmes
* HTML: filmes/lista.html
* CSS: estilo.css
* Controller: FilmeController.java
* Model: Filme.java

**Descrição:** Exibe a lista de filmes cadastrados.

4. Detalhes do Filme e Avaliar

* URL: /filmes/{id}
* HTML: filmes/detalhes.html
* CSS: estilo.css
* Controller: FilmeController.java, AnaliseController.java
* Models: Filme.java, Analise.java

**Descrição:** Mostra detalhes do filme selecionado e permite adicionar análises.
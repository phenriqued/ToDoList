![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

<h1> To Do List </h1>
<p> 
  Desenvolvimento de uma aplicação web de To Do List, construída com o backend em Java e Spring Boot. A persistência das tarefas em MySQL. A interface de usuário desenvolvida com HTML, CSS e JavaScript. 
  As funcionalidades incluem:
  <ul>
    <li>Criação de Tarefas</li>
    <li>Edição de Tarefas</li>
    <li>Exclusão de Tarfeas</li>
    <li>Marcar itens como concluídos</li>
    <li>Favoritar Tarefas</li>
  </ul>
  E um sistema inteligente de autoexclusão de tarefas após um período definido pelo usuário, otimizando a organização e evitando o acúmulo de itens desnecessários.
</p>
<br>

<h2>🛠 Tecnologias </h2>
<p>As seguintes ferramentas foram utilizadas para construir o projeto: 
  <ul>
    <li> <a href= https://spring.io/projects/spring-boot>Java Spring</a> </li> 
    <li><a href= https://dev.mysql.com/doc/>MySQL</a> </li>
  </ul>
</p>

<h2> 🚀 Começando. </h2>
<p>
Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.
</p>

<h3> 🛠️ Pré-requisitos </h3>
<p>
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:
</p>
<ul>
    <li><a href="https://git-scm.com/">Git</a> - Para clonar o repositório do projeto.</li>
    <li><a href="https://www.oracle.com/br/java/technologies/javase-downloads.html">Java Development Kit (JDK)</a> - Necessário para executar a aplicação Spring Boot.</li>
    <li><a href="https://maven.apache.org/download.cgi">Maven</a> ou <a href="https://gradle.org/install/">Gradle</a> - Ferramentas de build para gerenciar as dependências do projeto.</li>
    <li>Opcional: <a href="https://www.mysql.com/downloads/">MySQL</a> - Caso deseje utilizar este banco de dados.</li>
</ul>

<h3> 💾 Obtendo o Código </h3>
<p>
Existem duas maneiras principais de obter uma cópia do projeto:
</p>

<h4> 1. Clonando o Repositório (Git Clone) </h4>
<p>
clonando, utilize o seguinte comando no seu terminal:
</p>
<pre><code>git clone https://github.com/phenriqued/ToDoList.git
</code></pre>
<h4> 2. Criando um Fork (Fork) </h4>
<p>
criando um "fork" do repositório :)
</p>

<h3> ⚙️ Configuração do Banco de Dados (MySQL Opcional) </h3>
<p>
As configurações padrão são definidas no arquivo <code>application.properties</code> para um perfil de teste (<code>test</code>). Se você deseja utilizar o MySQL para desenvolvimento local, siga estas etapas:
</p>

<ol>
    <li><strong>Certifique-se de ter o MySQL instalado e em execução na sua máquina.</strong></li>
    <li><strong>Altere o perfil ativo da aplicação para <code>dev</code>.</strong> Modifique a seguinte linha no arquivo <code>application.properties</code>:
    <pre><code>spring.profiles.active=${APP_PROFILE:test}
    </code></pre>
    para:
    <pre><code>spring.profiles.active=${APP_PROFILE:dev}
    </code></pre>
    </li>
    <li><strong>Configure as credenciais do MySQL.</strong> Você tem duas opções para fazer isso:
        <ul>
            <li><strong>Opção 1: Alterando o arquivo <code>application-dev.properties</code>.</strong> edite o arquivo <code>application-dev.properties</code> na pasta de recursos do projeto e adicione as seguintes propriedades:
            <pre><code>
    spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    </code></pre>
            Certifique-se de substituir <code>seu_banco_de_dados</code>, <code>seu_usuario</code> e <code>sua_senha</code> pelas informações corretas. Ajuste a URL conforme a sua configuração do MySQL (porta, nome do banco, etc.).
            </li>
            <li><strong>Opção 2: Utilizando Variáveis de Ambiente no seu Editor
            </li>
        </ul>
    </li>
</ol>

<p>Feel Free :)</p>



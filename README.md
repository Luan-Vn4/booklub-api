# 📚 *Booklub-API*

*Booklub* é uma plataforma social dedicada a conectar leitores em clubes de 
leitura. O objetivo é proporcionar um ambiente colaborativo onde membros possam 
discutir livros, definir leituras mensais, agendar encontros e compartilhar seu 
progresso de leitura.

O *Booklub-API* é a parte backend do sistema *Booklub*, responsável por fornecer 
todos os recursos necessários para o funcionamento do aplicativo *Booklub*.

# ⚙ Tecnologias
Utilizaremos a linguagem Java e o framework Spring Boot para criar uma API 
que fornecerá os dados ao app. Essas tecnologias nos permite projetar para 
gerenciar dados dos usuários, clubes, livros e encontros que fazem parte do
aplicativo *Booklub*.

# ▶ Execução da *API* para Desenvolvimento
Para executar a *API*, basta clonar o repositório utilizando o comando
`git clone https://github.com/Luan-Vn4/booklub-api` e realizar as configurações
locais necessárias. Essas configurações locais do ambiente de desenvolvimento
são fornecidas pelo arquivo `.env` que deve estar localizado na raiz do projeto.
Como exemplo, a raiz do projeto possui o `.env.example` que serve de *template*
para você criar seu próprio `.env`.  

**Obs.:** O arquivo `.env` apenas define as configurações locais do projeto para
os desenvolvedores, portanto, em momento algum ele deve ser rastreado pelo *git*
ou enviado a este repositório.

### Elementos do *.env*
Conforme o desenvolvimento, podem surgir mais configurações de ambiente a serem
definidas. Portanto, como guia para essas configurações, cada elemento do `.env`
será adicionado nesta seção.
- `DB_URL`: *Url* do banco de dados que será utilizado para persistir
  os dados da *API*
- `DB_USER`: Usuário do banco de dados através do qual será feita a conexão
- `DB_PASSWROD`: Senha do banco de dados utilizado pela *API*
- `S3_URL`: Url do *media storage* com padrão *S3* utilizado para armazenar arquivos de mídia da *API*
- `S3_ACCESS_NAME`: Nome de usuário com o qual logar no serviço de *media storage S3* 
- `S3_ACCESS_SECRET`: Senha de acesso ao *media storage S3*

### *Docker Compose*
Para facilitar o desenvolvimento, é fornecido um arquivo `compose.yml` que
define as configurações para subir um banco de dados *Postgresql* localmente.
Para executá-lo, bastar ter o *Docker* instalado em sua máquina e executando.
Então, utilize o seguinte comando para baixar as imagens necessárias e criar o
container: `docker compose up`.

Os volumes criados pelo container, neste projeto, serão guardados em um *bind 
mount* localizado na pasta `/docker-volumes`.

**Obs.:** O *bind mount* não deve ser rastreado pelo *git* nem enviado a este
repositório, pois apenas guarda arquivos dos containers que serão executados
localmente nas máquinas dos desenvolvedores.

# 🏗 Arquitetura
Neste projeto, adotamos uma versão simplificada do *Domain-Driven Design (DDD)*
devido ao escopo inicial do sistema, focando nas funcionalidades essenciais e 
interações entre os componentes do sistema. A simplificação visa permitir uma
rápida evolução do projeto enquanto mantemos uma estrutura clara e bem 
organizada.

A arquitetura será organizada em quatro camadas principais, conforme descrito 
a seguir:

![Diagrama com DDD simplificado](https://i.imgur.com/PvItI7K.png)

### Camada 1: Camada de Apresentação (*Presentation*)
Esta camada é responsável pela interação com o usuário ou com outros sistemas,
como o frontend do aplicativo do *Booklub*. A *API* expõe os endpoints *RESTful*
que permitem a comunicação entre o cliente e o servidor. Cada endpoint 
corresponde a uma ação no domínio (criar clubes, adicionar usuários, etc.) e
recebe ou envia dados no formato *JSON*.

#### Responsabilidades:
- Receber requisições HTTP. 
- Validar dados de entrada.
- Chamar os serviços da camada de Aplicação. 
- Retornar as respostas para o cliente.

### Camada 2: Camada de Aplicação (*App*)
A camada de aplicação orquestra a lógica de negócios (camada de 
negócios) com a lógica de implementação (camada de infraestrutura) para entregar
resultados à camada de apresentação. Ela contém os serviços de aplicação,
responsáveis por processar as requisições recebidas da camada de 
apresentação e interagir com a camada de domínio. Nela também estão os *Data
Transfer Objects (DTO)* que definem diferentes formas como os dados das
entidades do modelo de negócios são expostas à camada de apresentação

#### Responsabilidades:
- Processar os casos de uso (ex.: criar um novo clube, inscrever um usuário 
  em um clube, etc.).
- Validar a lógica de negócios, se necessário.
- Invocar os repositórios da camada de Domínio para persistir ou recuperar dados.

### Camada 3: Camada de Domínio (*Domain*)
A camada de domínio contém o núcleo do sistema, onde reside a lógica de negócios
essencial. Devido ao escopo inicial do projeto, e para manter a simplicidade, 
essa camada apenas conterá as entidades do sistema e interfaces dos repositórios
e *gateways*. Essas interfaces fornecem uma abstração para partes do sistema que
requerem detalhes de implementação como acesso a *API*s e banco de dados que não
têm ligação direta com o domínio de negócios, o que permite desacoplar a lógica 
de negócios das tecnologias utilizadas. O objetivo dessa camada é refletir o
modelo de negócios de forma rica e coesa.

#### Responsabilidades:
- Modelar o domínio do problema (ex.: Clube, Usuário, Livro).
- Definir interfaces para abstrair detalhes de implementação (*repositories* e
  *gateways*)

### Camada 4: Camada de Infraestrutura (*Infra*)
A camada de infraestrutura lida com os aspectos técnicos do sistema que não 
estão diretamente relacionados à lógica de negócios, como persistência de dados, 
comunicação com APIs externas, segurança e envio de e-mails. Ela fornece 
implementações concretas às interfaces definidas na camada de domínio e usadas 
na camada de aplicação.

#### Responsabilidades:
- Implementar a persistência de dados.
- Conectar-se com APIs externas.
- Implementar serviços de autenticação e segurança.

# 📁 Organização do Diretório
Visando seguir a arquitetura definida para o projeto, organizamos o
diretório do projeto da seguinte maneira:
```
📂br.upe.booklub
│
├───📂presentation -> // Código que interage com o mundo externo (controllers 
│   │ 		       // e exception handlers)
│   ├───📂clubs -> // Controllers para clubes
│   ├───📂exceptionhandlers -> // Exception Handlers
│   └───📂users -> // Controllers para usuários
├───📂app -> // Orquestra a lógica do sistema, servindo de conexão entre o
│   │      //infra e domain
│   ├───📂users -> // Lógica ligada a usuários
│   │   ├───📂dtos
│   │   └───📂services
│   └───📂clubs -> // Lógica ligada a clubes
│   	├───📂dtos
│   	└───📂services
├───📂domain -> // Código ligado às regras de negócios e objetos de domínio
│   ├───📂entities -> // Entidades manipuladas
│   └───📂repositories -> // Interfaces dos repositories para desacoplar do
├───📂infra -> // Código de implementação que não está ligado à regra de 
│   │        // negócios (chamada de APIs e banco de dados)
│   ├───📂gateways -> // Código para se comunicar com APIs
└───📂config -> // Código de configuração
	├───📂auth
	├───📂docs
	└───📂rest
```
**Obs.:** O esquema acima não necessariamente representa a real estrutura
do diretório. Alguns subdiretórios não estão sendo mostrados. Esta é apenas
uma visão geral da estrutura.
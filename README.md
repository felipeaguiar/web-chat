Instalação

Instale o JDK 8.
Baixe e instale o Banco de Dados Oracle Express Edition
https://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html

Crie as seguintes variáveis de ambiente no seu SO:

	DB_HOST -> Endereço do banco de dados. Ex.: localhost
	DB_PORT -> Porta do banco de dados.  Ex.: 1521
	DB_USERNAME -> Nome do usuário do banco de dados Ex.: system
	DB_PASSWORD -> Senha do usuário do banco de dados Ex.: 123456

Baixe ou clone o projeto do gitHub no endereço
https://github.com/felipeaguiar/web-chat

Abra o prompt de comando ou o terminal e navegue até a pasta do projeto e digite os comandos abaixo.

mvnw package
java -jar target/web-chat-0.0.1-SNAPSHOT.jar

Abra o seu navegador e digite localhost na barra de endereços

OBS. A aplicação está configurada para usar a porta 80.
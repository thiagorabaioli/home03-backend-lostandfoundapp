# Estágio de Build: Usa uma imagem completa do JDK para compilar o projeto
FROM eclipse-temurin:21-jdk-jammy AS build

# Define o diretório de trabalho dentro do container
WORKDIR /workspace/app

# Copia os ficheiros do Maven e descarrega as dependências (isto acelera builds futuros)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copia o resto do código da aplicação
COPY src src

# Compila e empacota a aplicação, ignorando os testes
RUN ./mvnw package -DskipTests

# Estágio de Produção: Usa uma imagem mais leve, apenas com o necessário para executar
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia o .jar compilado do estágio de build para o estágio de produção
COPY --from=build /workspace/app/target/LostAndFoundAPP-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a sua aplicação usa (definida no application.properties)
EXPOSE 8080

# Comando para iniciar a aplicação quando o container arrancar
ENTRYPOINT ["java", "-jar","app.jar"]
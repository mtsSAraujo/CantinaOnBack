# Usa uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR da aplicação para o contêiner
COPY build/libs/ManuManager-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta em que a aplicação Spring Boot roda
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
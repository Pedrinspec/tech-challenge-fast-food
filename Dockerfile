# Usa imagem base do Java 17
FROM openjdk:17-jdk-slim

# Cria diretório de trabalho
WORKDIR /app

# Copia o JAR da aplicação
COPY build/libs/*.jar app.jar

# Expõe a porta da aplicação (8080)
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

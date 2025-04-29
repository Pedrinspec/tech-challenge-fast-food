# Usa uma imagem base do Java
FROM openjdk:17-jdk-slim
  
  # Define o diretório de trabalho dentro do container
WORKDIR /app
  
  # Copia o build da sua aplicação (o .jar) para dentro do container
COPY build/libs/*.jar app.jar
  
  # Expõe a porta 8080 (opcional, apenas para documentação)
EXPOSE 3000
  
  # Comando para rodar sua aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
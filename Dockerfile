# Etapa 1: build
FROM gradle:8.4-jdk21 AS build
WORKDIR /app

# Copia os arquivos que mudam menos (para aproveitar cache)
COPY gradle ./gradle
COPY gradlew build.gradle settings.gradle ./
RUN chmod +x gradlew

# Resolve dependências
RUN ./gradlew dependencies --no-daemon

# Copia o código-fonte
COPY src ./src

# Gera o JAR
RUN ./gradlew bootJar --no-daemon --stacktrace

# Etapa 2: imagem final
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia apenas o JAR da etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
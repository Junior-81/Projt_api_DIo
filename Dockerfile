# Dockerfile para a Santander Dev Week API

# Estágio 1: Build
FROM gradle:8.5-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Estágio 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Cria usuário não-root para segurança
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copia o JAR construído
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta
EXPOSE 8080

# Configurações de JVM para produção
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando de execução
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=prd -jar app.jar"]

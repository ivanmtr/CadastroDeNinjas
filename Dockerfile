FROM eclipse-temurin:17
LABEL maintainer="Ivan Monteiro"
WORKDIR  /app
COPY target/CadastroDeNinjas-0.0.1-SNAPSHOT.jar /app/cadastro-de-ninjas.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cadastro-de-ninjas.jar"]
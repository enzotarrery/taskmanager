# Étape 1 : compilation de l'application
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copier les fichiers nécessaires à Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# S'assurer que le wrapper Maven est exécutable
RUN chmod +x mvnw

# Télécharger les dépendances
RUN ./mvnw -B dependency:go-offline

# Copier le code source
COPY src/ src/

# Compiler et empaqueter l'application
RUN ./mvnw -B package


# Étape 2 : exécution de l'application
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Exécuter avec un utilisateur non privilégié
RUN useradd --system --uid 10001 appuser

COPY --from=build /app/target/*.jar /app/app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

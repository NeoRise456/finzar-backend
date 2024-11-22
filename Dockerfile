# Stage 1: Build the application
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Set environment variables for Maven
ENV MAVEN_OPTS="-Dmaven.multiModuleProjectDirectory=/app \
                -Djansi.passthrough=true \
                -Dmaven.home=/root/.m2/wrapper/dists/apache-maven-3.9.9-bin/4nf9hui3q3djbarqar9g711ggc/apache-maven-3.9.9 \
                -Dclassworlds.conf=/root/.m2/wrapper/dists/apache-maven-3.9.9-bin/4nf9hui3q3djbarqar9g711ggc/apache-maven-3.9.9/bin/m2.conf \
                -Dmaven.ext.class.path=/root/.m2/wrapper/dists/apache-maven-3.9.9-bin/4nf9hui3q3djbarqar9g711ggc/apache-maven-3.9.9/boot/plexus-classworlds-2.8.0.jar \
                -Dfile.encoding=UTF-8 \
                -Dsun.stdout.encoding=UTF-8 \
                -Dsun.stderr.encoding=UTF-8"

RUN mvn clean install -DskipTests

RUN ls -l /app/target/


# Stage 2: Run the application
FROM openjdk:21
LABEL authors="Neo"
COPY --from=build /app/target/finzar-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
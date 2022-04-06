FROM eclipse-temurin:17-jre-alpine
ADD target/GpbAccount-1.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backend.jar"]
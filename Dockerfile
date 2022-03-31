FROM openjdk:17-alpine
ADD target/GpbAccount-1.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
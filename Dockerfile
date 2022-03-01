FROM openjdk:17
ADD target/GpbAccount-1.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
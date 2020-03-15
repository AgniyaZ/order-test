FROM maven:3.5-jdk-8

RUN mkdir -p /opt/order
WORKDIR /opt/order
COPY ./jar/orders.jar .
RUN chmod +x orders.jar
CMD ["java", "-jar", "./orders.jar"]


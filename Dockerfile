FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/rxjs.jar /rxjs/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/rxjs/app.jar"]

FROM openjdk:21
WORKDIR /SBSendingEmailAsync
COPY target/SBSendingEmailAsyncContainer.jar .
ENTRYPOINT ["java", "-jar", "SBSendingEmailAsyncContainer.jar"]
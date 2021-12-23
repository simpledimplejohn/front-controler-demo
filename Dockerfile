# calling base image, tomcat server compatable with jdk 8
FROM tomcat:8.0-jre8

LABEL maintainer="John Blalock"

# copy .war file to tomcat webapps dir
ADD target/FrontControllerDemo.war /usr/local/tomcat/webapps/

# expose port 8080 for tomcat to run
# so container will listen to this port at runtime
EXPOSE 8080

#CMD are the commands that will be run when the container is run
#runs the shell script, that starts tomcat server
CMD ["catalina.sh", "run"]
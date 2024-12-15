# Use the official WildFly image as the base
FROM jboss/wildfly:latest

# Set the deployment directory
ENV DEPLOY_DIR=/opt/jboss/wildfly/standalone/deployments

# Copy the WAR file to the deployment directory
# COPY target/deploy.war ${DEPLOY_DIR}/deploy.war

# Expose the default HTTP port
EXPOSE 8080
EXPOSE 9990

# Start WildFly in standalone mode
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

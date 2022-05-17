#  Create a Jenkins container which additionally contains the Docker runtime.
#  NOTE: This container requires the DOCKER_GRP argument, which should map the group
#        ID (gid) matching that of the /var/run/docker.sock file of the host system.

FROM jenkins/jenkins:jdk11
ARG DOCKER_GRP

USER root
RUN apt-get update
RUN apt-get -y install apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository "deb https://download.docker.com/linux/debian $(lsb_release -cs) stable"

RUN apt-get update
RUN apt-get -y install docker-ce
RUN usermod -a -G docker jenkins
RUN groupmod -g ${DOCKER_GRP} docker
USER jenkins

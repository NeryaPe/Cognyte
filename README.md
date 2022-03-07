# Cognyte
Home Task

1- run jenkins from:

https://github.com/NeryaPe/devops-jenkins.git
~/devops-jenkins/Environment$ docker-compose up

2- install ansible:
sudo yum install ansible

3- install jenkins ansible plugin

4- update ansible Configuration in Global Tool Configuration

5- 
In /etc/ansible/hosts list the host machines: [localhost] IP address of local host
u should have passwordless connection using ssh-keygen -t rsa which will give u a rsa.pubkey which should be copied on authorization keys of host machine
then u can run the playbook
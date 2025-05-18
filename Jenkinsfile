pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ChandanaLD/Devops.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Transfer WAR to EC2') {
            steps {
                // Make sure the path and file name match the Jenkins build output
                sh '''
                scp -i /var/lib/jenkins/.ssh/Devops.pem http://44.243.14.81:8080/job/BuildandDeployonContainer/ws/target/registration-webapp-1.0-SNAPSHOT.war ec2-user@52.11.221.148:/tmp/
                '''
            }
        }

        stage('Deploy on Docker Tomcat') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'dockerhost',
                        transfers: [
                            sshTransfer(
                                sourceFiles: 'http://44.243.14.81:8080/job/BuildandDeployonContainer/ws/target/registration-webapp-1.0-SNAPSHOT.war',
                                removePrefix: 'http://44.243.14.81:8080/job/BuildandDeployonContainer/ws/target',
                                remoteDirectory: '/tmp'
                            )
                        ],
                        execCommand: '''
                        sudo docker cp /tmp/registration-webapp-1.0-SNAPSHOT.war tomcat-container:/usr/local/tomcat/webapps/
                        sudo docker restart tomcat-container
                        '''
                    )
                ])
            }
        }
    }
}

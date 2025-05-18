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
                sh '''
                scp -i "C:\Users\chand\Documents\Devops.pem" target/registration.war ec2-user@52.11.221.148:/tmp/
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
                                sourceFiles: 'target/registration.war',
                                removePrefix: 'target',
                                remoteDirectory: '/tmp'
                            )
                        ],
                        execCommand: '''
                        docker cp /tmp/registration.war tomcat-container:/usr/local/tomcat/webapps/
                        docker restart tomcat-container
                        '''
                    )
                ])
            }
        }
    }
}

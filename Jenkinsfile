pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ChandanaLD/Devops.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'dockerhost',
                        transfers: [
                            // Copy WAR file from Jenkins workspace or local machine to Docker host /tmp
                            sshTransfer(
                                sourceFiles: 'target/webapps.war',  // relative to Jenkins workspace after build
                                remoteDirectory: '/tmp',
                                removePrefix: 'target'
                            )
                        ],
                        execCommand: '''
                            docker cp /tmp/webapps.war tomcat-container:/usr/local/tomcat/webapps/
                            docker exec tomcat-container catalina.sh stop
                            docker exec tomcat-container catalina.sh start
                        '''
                    )
                ])
            }
        }
    }
}

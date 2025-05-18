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
                // Use SSH steps to copy the WAR to your remote Tomcat server
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'dockerhost', // your SSH config name in Jenkins
                        transfers: [
                            sshTransfer(
                                sourceFiles: 'target/webapps.war',
                                removePrefix: 'target',
                                remoteDirectory: '/path/to/tomcat/webapps',
                                execCommand: 'systemctl restart tomcat'  // or restart your tomcat service
                            )
                        ]
                    )
                ])
            }
        }
    }
}

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
                bat 'copy target\\registration-webapp.war "C:\\path\\to\\tomcat\\webapps\\"'
            }
        }
    }
}

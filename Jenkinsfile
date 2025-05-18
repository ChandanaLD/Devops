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
                scp -i /var/lib/jenkins/.ssh/Devops.pem target/registration-webapp-1.0-SNAPSHOT.war ec2-user@52.11.221.148:/tmp/
                '''
            }
        }

        stage('Deploy on Docker Tomcat') {
            steps {
                stage('Deploy on Docker Tomcat') {
    steps {
        sh '''
        scp -i /var/lib/jenkins/.ssh/Devops.pem target/registration-webapp-1.0-SNAPSHOT.war ec2-user@52.11.221.148:/tmp/

        ssh -i /var/lib/jenkins/.ssh/Devops.pem ec2-user@52.11.221.148 << EOF
            sudo docker cp /tmp/registration-webapp-1.0-SNAPSHOT.war tomcat-container:/usr/local/tomcat/webapps/
            sudo docker exec tomcat-container rm -rf /usr/local/tomcat/webapps/registration-webapp-1.0-SNAPSHOT
            sudo docker restart tomcat-container
        EOF
        '''
    }
}

            }
        }
    }
}

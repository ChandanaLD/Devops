pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_IP = '52.11.221.148'                  // Replace with your EC2 public IP
        PEM_PATH = '/var/lib/jenkins/.ssh/Devops.pem'  // Path to your PEM file on Jenkins server
        WAR_NAME = 'registration-webapp-1.0-SNAPSHOT.war'  // Your WAR file name
        WAR_PATH = "target/${WAR_NAME}"
    }

    stages {
        stage('Build') {
            steps {
                echo "Building the WAR file..."
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying WAR to EC2 Tomcat Docker container..."

                // Copy WAR file to EC2 /tmp
                sh """
                scp -i ${PEM_PATH} ${WAR_PATH} ${EC2_USER}@${EC2_IP}:/tmp/
                """

                // SSH to EC2, move WAR inside tomcat webapps, restart container
                sh """
                ssh -i ${PEM_PATH} ${EC2_USER}@${EC2_IP} << EOF
                sudo mv /tmp/${WAR_NAME} /usr/local/tomcat/webapps/
                sudo docker restart tomcat-container
                EOF
                """
            }
        }
    }
}

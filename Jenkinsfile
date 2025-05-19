pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "chandanald274/regapp"
    DOCKER_HOST = "ec2-user@52.11.221.148"
  }

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

    stage('Build & Push Docker Image') {
  steps {
    withCredentials([
      string(credentialsId: 'DOCKER_USERNAME', variable: 'DOCKER_USERNAME'),
      string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD')
    ]) {
      sh 'docker build -t $DOCKER_IMAGE .'
      sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
      sh 'docker push $DOCKER_IMAGE'
    }
  }
}


    stage('Deploy on Dockerhost') {
      steps {
        sshagent (credentials: ['ec2-ssh-key']) {
          sh """
          ssh -o StrictHostKeyChecking=no $DOCKER_HOST '
            docker pull $DOCKER_IMAGE &&
            docker stop regapp || true &&
            docker rm regapp || true &&
            docker run -d --name regapp -p 8080:8080 $DOCKER_IMAGE
          '
          """
        }
      }
    }
  }
}

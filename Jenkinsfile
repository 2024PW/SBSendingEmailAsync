pipeline {
  environment {
    dockerimagename = "ericli10/sbsendingemailasync-repository"
    dockerImage = ""
  }
  agent any
  stages {
    stage('Checkout Source') {
      steps {
        git 'https://github.com/2024PW/SBSendingEmailAsync.git'
      }
    }
    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }
    stage('Pushing Image') {
      environment {
          registryCredential = 'dockerhub-credentials'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }
    stage('Deploying React.js container to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "jenkins-kuberneetes-deployment/sbsendingemailasync-deployment.yaml",
                                         "jenkins-kuberneetes-deployment/sbsendingemailasync-service.yaml")
        }
      }
    }
  }
}
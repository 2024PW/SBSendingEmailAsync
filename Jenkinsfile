pipeline {
  environment {
    dockerimagename = "ericli10/sbsendingemailasync-repository"
    dockerImage = ""
  }
  agent any
  tools {
    maven 'maven_home' // Reference the Maven installation in Jenkins
  }
  stages {
    stage('Checkout Source') {
      steps {
        git 'https://github.com/2024PW/SBSendingEmailAsync.git'
      }
    }
    stage('Build') {
          steps{
            script {
             bat 'mvn clean package'
            }
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
    stage('Deploying container to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(
            configs: [
                "jenkins-kubernetes-deployment/sbsendingemailasync-deployment.yaml",
                "jenkins-kubernetes-deployment/sbsendingemailasync-service.yaml"
            ]
          )
        }
      }
    }
  }
}
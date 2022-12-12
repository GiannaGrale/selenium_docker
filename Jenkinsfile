pipeline {
    // master executor should be set to 0
    agent {
       docker {
          image 'maven:3-alpine'
          args '-v $HOME/.m2:/root/.m2'
       }
    }
    stages {
        stage('Build Jar') {
            steps {
                //sh
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh
                sh "docker build -t hanna369/selenium-docker ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push hanna369/selenium-docker:latest"
			    }
            }
        }
        stage('Pull Latest Image') {
        	steps {
        			sh "docker pull hanna369/selenium-docker"
        	}
        }
        stage('Start grid'){
              steps{
        			sh "docker-compose up -d hub chrome firefox"
              }
        }
        stage('Run tests'){
                steps{
			     	sh "docker-compose up search-module"
			}
        }
    }
	post{
		always{
			archiveArtifacts artifacts: 'output/**'
			sh "docker-compose down"
		}
	}
}

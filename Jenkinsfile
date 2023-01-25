pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //sh
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //sh
                bat "docker build -t=hanna369/docker-demo ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push hanna369/docker-demo:latest"
			    }
            }
        }
        stage('Pull Latest Image') {
        	steps {
        			bat "docker pull hanna369/docker-demo"
        	}
        }
        stage('Run tests'){
                steps{
			     	bat "docker compose up search-module flight-module"
			}
        }
    }
        stage('Report') {
               steps {
                      publishHTML([reportName  : 'Allure Report', reportDir: 'target/test_results', reportFiles: 'index.html',
                                  reportTitles: '', allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false])
           }
        }
    
	post{
		always{
			bat "docker compose down"
		}
	}
}

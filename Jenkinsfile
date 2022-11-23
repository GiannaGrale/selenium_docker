pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	app = docker.build("hanna369/selenium-docker")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
			        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
			        	app.push("${BUILD_NUMBER}")
			            app.push("latest")
			        }
                }
            }
        }
        stage("Pull Latest Image"){
        			steps{
        				bat "docker pull hanna369/selenium-docker"
        			}
        		}
        		stage("Start Grid"){
        			steps{
        				bat "docker-compose up -d hub chrome firefox"
        			}
        		}
        		stage("Run Test"){
        			steps{
        				bat "docker-compose up search-module book_flight_module"
        			}
        		}
        	}
        	post{
        		always{
        			archiveArtifacts artifacts: 'output/**'
        			bat "docker-compose down"
        			bat "sudo rm -rf output/"
        		}
        	}
    }
}
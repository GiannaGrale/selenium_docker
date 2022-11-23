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
                sh 'mvn clean package -DskipTests'
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
        				sh "docker pull hanna369/selenium-docker"
        			}
        		}
        		stage("Start Grid"){
        			steps{
        				sh "docker-compose up -d hub chrome firefox"
        			}
        		}
        		stage("Run Test"){
        			steps{
        				sh "docker-compose up search-module book_flight_module"
        			}
        		}
        	}
        	post{
        		always{
        			archiveArtifacts artifacts: 'output/**'
        			sh "docker-compose down"
        			sh "sudo rm -rf output/"
        		}
        	}
    }
}
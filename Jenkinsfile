pipeline {
    // master executor should be set to 0
    agent any
        environment {
            REPORT_PATH = "allure-results"
            REPORT_NAME = "REPORT.docx"
        }
    stages {
        stage('Build Jar'){
            steps {
                //sh
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){
            steps {
                //sh
                bat "docker build -t=hanna369/docker-demo ."
            }
        }
        stage('Push Image'){
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
			        bat "docker login --username=${user} --password=${pass}"
			        bat "docker push hanna369/docker-demo:latest"
			    }
            }
        }
        stage('Pull Latest Image'){
        	steps {
        			bat "docker pull hanna369/docker-demo"
        	}
        }
        stage('Run tests'){
                steps {
			     	bat "docker compose up search-module flight-module"
			}
        }

	    stage('Execute') {
	           steps {
                       script {
		/* Execute the test script. On faliure proceed to next step */
              catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
              bat 'mvn test'
              bat 'docker run --network="host" --rm -v ${WORKSPACE}/allure-results:/AllureReports hanna369/docker-demo  .'
              }
         }
     }
}
	post{
		always{
			bat "docker compose down"
			script {
                allure([
                 includeProperties: false,
                 jdk: '',
                 properties: [],
                 reportBuildPolicy: 'ALWAYS',
                 results: [[path: 'target/allure-results']]
                 ])
             }
		}
	}
}

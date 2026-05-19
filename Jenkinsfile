pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'banking-app'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/DEVENDRA9724/banking-finance-app.git'
            }
        }
        
        stage('Compile & Test') {
            steps {
                bat 'mvn clean test'
            }
            post {
                success {
                    publishHTML([
                        reportDir: 'target/surefire-reports',
                        reportFiles: 'index.html',
                        reportName: 'TestNG Reports'
                    ])
                }
            }
        }
        
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                bat 'docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .'
                bat 'docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest'
            }
        }
        
        stage('Deploy Container') {
            steps {
                bat '''
                    docker stop banking-container || exit 0
                    docker rm banking-container || exit 0
                    docker run -d -p 8082:8081 --name banking-container ${DOCKER_IMAGE}:latest
                '''
            }
        }
        
        stage('Verify Deployment') {
            steps {
                bat 'ping 127.0.0.1 -n 6 > nul'
                bat 'curl http://localhost:8082/viewPolicy/1001'
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline SUCCESS! Banking app deployed!'
        }
        failure {
            echo '❌ Pipeline FAILED! Check logs!'
        }
    }
}
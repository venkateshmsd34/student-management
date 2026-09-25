pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Old Application') {
            steps {
                sh '''
                    PID=$(lsof -t -i:8081 || true)

                    if [ -n "$PID" ]; then
                        kill $PID
                        sleep 5
                    fi
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    nohup java -jar target/*.jar > app.log 2>&1 &
                '''
            }
        }
    }
}
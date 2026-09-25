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
                        echo "Stopping old application: PID=$PID"
                        kill $PID
                        sleep 5
                    else
                        echo "No old application is running"
                    fi
                '''
            }
        }

        stage('Start Application') {
            steps {
                sh '''
                    echo "Starting new application..."

                    nohup java -jar target/*.jar > app.log 2>&1 &

                    sleep 10

                    echo "Application started"
                '''
            }
        }
    }
}
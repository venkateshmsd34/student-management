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
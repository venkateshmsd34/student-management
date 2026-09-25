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
                    echo "Checking for old application..."

                    PID=$(lsof -t -i:8081 || true)

                    if [ -n "$PID" ]; then
                        echo "Stopping old application. PID: $PID"
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
			
			            JENKINS_NODE_COOKIE=dontKillMe \
			            nohup java -jar target/*.jar > app.log 2>&1 < /dev/null &
			
			            APP_PID=$!
			
			            echo "Application PID: $APP_PID"
			
			            sleep 15
			
			            if kill -0 $APP_PID 2>/dev/null; then
			                echo "Application is running"
			            else
			                echo "Application failed to start"
			                cat app.log
			                exit 1
			            fi
			        '''
    				}
		}
    }
}
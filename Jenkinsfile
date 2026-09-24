pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/venkateshmsd34/student-management.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Success') 
        { 
			steps 
			{
				
				 echo ' Student Management Build Successful!' 
				 echo ' JAR file generated successfully.'
				 echo ' webhook added.'  
			
				 }
				}

    }
}
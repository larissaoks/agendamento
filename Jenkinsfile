pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                bat '"C:\Program Files\apache-maven-3.9.1\bin\mvn" clean'
            }
        }

        stage('Build') {
            steps {
                bat '"C:\Program Files\apache-maven-3.9.1\bin\mvn" package'
            }
        }
    }
}
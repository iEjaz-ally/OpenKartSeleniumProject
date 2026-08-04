pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Test'){
            steps{
                echo 'Hi from me'
            }
        }
        stage('Deploy'){
            steps{
                echo 'how are you?'
            }
        }
    }
post{

    success{
        echo 'This will run only if successful'
    }
    always{
        emailext body: '', subject: 'Pipeline status', to: 'mohammedejaz7007@gmail.com'
}

}
}

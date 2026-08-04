pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }
    parameters{

        string(name: 'testNG', defaultValue: 'testNG.xml', description: 'enter the testNG.xml file name')
    }
    stages {

        stage('Build') {
          
            steps {
             git branch: 'main',
               url: 'https://github.com/iEjaz-ally/OpenKartSeleniumProject.git'

                bat "mvn clean test -DtestNG=${params.testNG}"
            }

            post {

                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true

                    emailext(
                        body: 'Pipeline execution completed.',
                        subject: 'Pipeline status',
                        to: 'mohammedejaz7007@gmail.com'
                    )
                }
            }
        }
    }
}

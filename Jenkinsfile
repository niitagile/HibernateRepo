pipeline {
    agent { docker { image 'maven:3.3.3' } }
      stages {
        stage('log version info') {
      steps {
        bat "mvn --version"
        bat "mvn clean install"
      }
    }
  }
}
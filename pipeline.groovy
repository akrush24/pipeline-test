#!groovy
// Check ub1 properties
properties([disableConcurrentBuilds()])

pipeline {
    agent { 
        label 'master'
        }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {
        stage("First step [show date]") {
            steps {
                sh 'date'
            }
        }
        stage("Second step [ls workdir]") {
            steps {
                sh ""
            }
        }
        stage("Pring ENVs")
            steps {
               sh "printenv"
            }
    }
}

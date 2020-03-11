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
                sh '''
                ls -R /etc/ > ${date +%H%M%S_%d%m%y}.log
                '''
            }
        }
        stage("Second step [ls workdir]") {
            steps {
                sh "ls -la ."
            }
        }
        stage("Push to Nexus") {
            steps {

            }
    }
}

node('docker') {
    def artifact_name = "${env.JOB_BASE_NAME}_${env.BUILD_NUMBER}.log"
    phase('list files in work directory') {
        sh "ls -la"
    }
    phase('Create file') {
        sh "echo ${BUILD_NUMBER} > ${artifact_name}"
    }
    phase('push to nexus') {
        nexusPublisher file: artifact_name, repo: 'testrail-releases'
    }
    phase('list files in work directory') {
        sh "ls -la"
    }
}
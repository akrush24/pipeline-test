node('docker') {
    phase('list files in work directory') {
        sh "ls -la"
    }
    phase('Create file') {
        sh '''
        echo ${BUILD_NUMBER} > ${BUILD_NUMBER}.log
        '''
    }
    phase('push to nexus') {
        nexusPublisher file: ${BUILD_NUMBER}.log, repo: 'testrail-releases'
    }
    phase('list files in work directory') {
        sh "ls -la"
    }
}
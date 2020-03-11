node('docker') {
    phase('Checkout') {
        gitlab url: 'https://github.com/akrush24/pipeline-test.git',
               branch: 'master'
    }
    phase('list files in work directory') {
        sh "ls -la"
    }
    phase('Create file') {
        sh '''
        ls -R /etc/ > ${date +%H%M%S_%d%m%y}.log
        '''
    }
}
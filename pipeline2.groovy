def nexusPublisher2(Map args=[:]) {
  def filename = args.get('file')
  def repo = args.get('repo')
  def dir = args.get('dir')
  def credentialsId = args.get('credentialsId', 'ucs-registry-rdojenkins')

  def NEXUS_DIR_NAME = '/'
  if (dir) {
    NEXUS_DIR_NAME=dir
  }

  withCredentials([usernameColonPassword(credentialsId: credentialsId, variable: 'NEXUS_CREDENTIALS')]) {
    env.NEXUS_FILENAME = filename
    env.NEXUS_REPO = repo

    sh '''
       set +x
       ARTIFACT_URL=https://mdcnexus.stageoffice.ru/repository/${NEXUS_REPO}/${NEXUS_DIR_NAME}/${NEXUS_FILENAME}
       curl -i --user ${NEXUS_CREDENTIALS} --upload-file ${NEXUS_FILENAME} ${ARTIFACT_URL}

       echo "Successfully uploaded ${NEXUS_FILENAME} to ${ARTIFACT_URL}"
    '''
  }
}

node('docker') {
    def artifact_name = "${env.JOB_BASE_NAME}_${env.BUILD_NUMBER}.log"
    phase('list files in work directory') {
        sh "ls -la"
    }
    phase('Create file') {
        sh "echo ${BUILD_NUMBER} > ${artifact_name}"
    }
    dir = ${env.BUILD_NUMBER}
    phase('push to nexus') {
        nexusPublisher2 file: artifact_name, repo: 'testrail-releases', dir: dir
    }
    phase('list files in work directory') {
        sh "ls -la"
    }
}
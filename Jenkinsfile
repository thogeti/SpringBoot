pipeline {
    agent any

    stages {
        stage('---Clean---') {
            steps {
                echo 'Cleaning..'
            }
        }
        stage('---Test---') {
            steps {
                echo 'Testing..'
            }
        }
        stage('---package--') {
            steps {
                // service = $SERVICE
              //  echo "service"
		    echo "$SERVICE/pom.xml"
                 bat "mvn -f SpringBootRestApiExample/pom.xml -Dmaven.test.failure.ignore=true clean package"
                  echo 'Packaging..'
            }
        }
     stage('---Publish--') {
            steps {
                nexusPublisher nexusInstanceId: 'CDR_Central', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\\Work\\Jenkins\\Jenkinshome\\workspace\\GroovyPipelineSpringBoot\\SpringBootRestApiExample\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_Central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
            }
        }
    }
}

pipeline {
    agent any

    stages {
       // stage('---Clean---') {
       //     steps {
        //        echo 'Cleaning..'
         //   }
       // }
	  //  def service
        stage('---Test---') {
            steps {
                echo 'Testing..'
            }
        }
        stage('---package--') {
            steps {
		  // service = $SERVICE
		    echo "$SERVICE/pom.xml"
                 //bat "mvn -Dmaven.test.failure.ignore=true clean package"
		 //bat "mvn -f pom.xml -Dmaven.test.failure.ignore=true clean package"
		 //bat "mvn -f pom.xml -Dmaven.test.failure.ignore=true clean package"
		  //  bat "mvn -f SpringBootRestApiExample/pom.xml -Dmaven.test.failure.ignore=true clean package"
		      bat "mvn -f $SERVICE/pom.xml -Dmaven.test.failure.ignore=true clean package"
                  echo 'Packaging..'
            }
        }
     stage('---Publish--') {
            steps {
		    echo "${env.WORK_SPACE} ...local WORK_SPACE dir "
		    echo "$WORKDIR ...local WORKDIR dir "
		    echo "$WORKSPACE ...local WORKSPACE dir "
               // nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\\Users\\011391\\.jenkins\\workspace\\GroovyPipelineSpringBoot\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    //nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '$WORK_SPACE+GroovyPipelineSpringBoot\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'war', version: '$FILENAME+$VERSION']]]
		   // nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${env.WORK_SPACE}+GroovyPipelineSpringBoot\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    //nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '$WORKDIR+GroovyPipelineSpringBoot\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    //nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}+GroovyPipelineSpringBoot\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    //nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '$WORKDIR']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    nexusPublisher nexusInstanceId: 'CDR_Central', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\\Work\\Jenkins\\Jenkinshome\\workspace\\GroovyPipelineSpringBoot\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop65', groupId: 'CDR_Central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    //nexusPublisher nexusInstanceId: 'LocalNexus-3', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${env.WORK_SPACE}+$WORKFILE']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
            }
        }
    }
	post { 
	  
        always { 
		echo 'CLEANING WS..'
            cleanWs()
        }
		
    }
} 

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
		  //  echo "$SERVICE/pom.xml"
                 //bat "mvn -f C:\\Work\\Jenkins\\Jenkinshome\\workspace\\GroovyPipelineSpringBoot\\SpringBootRestApiExample\\pom.xml -Dmaven.test.failure.ignore=true clean package"
		   /// bat "mvn -f SpringBootRestApiExample/pom.xml -Dmaven.test.failure.ignore=true clean package"
		    //MavenHelloWorldProject
		   //// bat "mvn -f MavenHelloWorldProject/pom.xml -Dmaven.test.failure.ignore=true clean package"		    
		    bat "mvn -f CDR_Canonical_Model/pom.xml -Dmaven.test.failure.ignore=true -Drelease=Drop65_20210110 clean compile install"		    
		    bat "mvn -f CDR_Exception_Framework/pom.xml -Dmaven.test.failure.ignore=true -Drelease=Drop65_20210110 clean compile install"
		    bat "mvn -f CDR_CommonUtil/pom.xml -Dmaven.test.failure.ignore=true -Drelease=Drop65_20210110 clean compile install"
		    bat "mvn -f CDR_API_Service/pom.xml -Dmaven.test.failure.ignore=true -Drelease=Drop65_20210110 clean compile install"
		    bat "mvn -f PatientService/pom.xml -Dmaven.test.failure.ignore=true -Drelease=Drop65_20210110 clean compile install"
                  echo 'Packaging..'
            }
        }
     stage('---Publish--') {
            steps {
               /// nexusPublisher nexusInstanceId: 'CDR_Central', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\\Work\\Jenkins\\Jenkinshome\\workspace\\GroovyPipelineSpringBoot\\SpringBootRestApiExample\\target\\SpringBootRestApiExample-1.0.0.jar']], mavenCoordinate: [artifactId: 'Drop59', groupId: 'CDR_Central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		//MavenHelloWorldProject
		////    nexusPublisher nexusInstanceId: 'CDR_Central', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'D:\\Git\\MavenHelloWorldProject\\target\\MavenHelloWorldProject-1.0-SNAPSHOT.jar']], mavenCoordinate: [artifactId: 'Drop_60', groupId: 'CDR_Central', packaging: 'jar', version: '$FILENAME+$VERSION']]]
		    
		  //nexusPublisher nexusInstanceId: 'CDR_Central', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\\Work\\Jenkins\\Jenkinshome\\workspace\\CDR_Build-0126\\PatientService\\target\\PatientService-Drop65_20210110.war']], mavenCoordinate: [artifactId: 'Drop65', groupId: 'CDR_central', packaging: 'war', version: '1.0']]]  
		    nexusPublisher nexusInstanceId: 'CDR_Central', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\\Work\\Jenkins\\Jenkinshome\\workspace\\CDR_Build-0126\\PatientService\\target\\PatientService-Drop65_20210110.war']], mavenCoordinate: [artifactId: '$SERVICE+-Drop65_', groupId: 'CDR_central', packaging: 'war', version: '1.0']]] 
            }
        }
    }
}

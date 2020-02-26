node{
	
		stage('---CLEAN--'){
			echo "clean TEst"
			git config --global --unset-all remote.origin.proxy
			git 'https://github.com/thogeti/SpringBoot.git'
		//	sh "mvn clean"
			
		}
		stage('---Packeage--'){	
			echo " Pack TEst"
			//def mvnHome=tool name: 'Maven', type: 'maven'
		//	echo ${mvnHome}
			//sh '${mvnHome}/bin/mvn package'
			
		}
	
	
}

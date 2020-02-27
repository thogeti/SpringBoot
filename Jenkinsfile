import jenkins.model.*
jenkins = Jenkins.instance
node{
	
		stage('---CLEAN--'){
			echo "clean TEst"
			def gitHome=tool name: 'Default', type: 'git'
			echo ${gitHome}
			//git config --global --unset-all remote.origin.proxy
			//git 'https://github.com/thogeti/SpringBoot.git'
			echo " clean TEst 12"
			//def mvnHome=tool name: 'Maven', type: 'maven'
			//echo ${mvnHome}
			//sh '${mvnHome}/bin/mvn clean package'
		//	sh "mvn clean"
			
		}
		stage('---Packeage--'){	
			echo " Pack TEst"
			//def mvnHome=tool name: 'Maven', type: 'maven'
		//	echo ${mvnHome}
			//sh '${mvnHome}/bin/mvn package'
			
		}
	
	
}

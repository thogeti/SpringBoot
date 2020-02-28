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
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
                  echo 'Packaging..'
            }
        }
    }
}
}

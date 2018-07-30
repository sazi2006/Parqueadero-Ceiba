pipeline {
 	//Donde se va a ejecutar el Pipeline
	agent {
 		label 'Slave_Induccion'
 	}
 	
 	//Opciones espec�ficas de Pipeline dentro del Pipeline
 	options {
		//Mantener artefactos y salida de consola para el # espec�fico de ejecuciones recientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '3'))
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds()
 	}
 	
	 //Una secci�n que define las herramientas para autoinstalar y poner en la PATH
	 tools {
	 	jdk 'JDK8_Centos' //Preinstalada en la Configuraci�n del Master
		gradle 'Gradle4.5_Centos' //Preinstalada en la Configuraci�n del Master
	 }
	 
	 //Aqu� comienzan los items del Pipeline
	 stages{
	 	stage('Checkout') {
	 		steps{
				echo "------------>Checkout<------------"
				checkout([$class: 'GitSCM', branches: [[name: '*/master']],
				doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:	'Git_Centos', 
				submoduleCfg: [], userRemoteConfigs: [[credentialsId:	'GitHub_sazi2006', 
				url: 'https://github.com/sazi2006/Parqueadero-Ceiba']]])
	 		}
	 	}
		 
		 stage('Compile') {
			steps{
				echo "------------>Compile<------------"
				sh 'gradle --b ./build.gradle compileJava'
			}
		 }
	 	
		 stage('Unit Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./build.gradle cleanTest test'
				junit '**/jacoco/test-results/*.xml'
			}
		}
		 
		 stage('Integration Tests') {
		 	steps {
		 		echo "------------>Integration Tests<------------"
		 	}
		 }
		 
		 stage('Static Code Analysis') {
			steps{
				echo '------------>An�lisis de c�digo est�tico<------------'
				withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
				}
			}
		}
		 
		 stage('Build') {
			steps {
				echo "------------>Build<------------"
				sh 'gradle --b ./build.gradle build -x test'
			}
		}
		 
		  
	 }
	 
	 post {
	 	always {
	 		echo 'This will always run'
	 	}	
		 success {
		 		echo 'This will run only if successful'
		 		junit '**/jacoco/test-results/*.xml'
		 }
		 failure {
			echo 'This will run only if failed'
			//send notifications about a Pipeline to an email
			mail (to: 'alejandro.zapata@ceiba.com.co',
			      subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
			      body: "Something is wrong with ${env.BUILD_URL}")
		}
		 unstable {
		 	echo "run unstable"
		 }
		 changed {
		 	echo "Pipeline state has changed"
		 }
	 }
}

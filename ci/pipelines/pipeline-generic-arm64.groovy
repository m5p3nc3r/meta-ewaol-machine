pipeline {
	agent {
		docker {
			image 'ghcr.io/siemens/kas/kas:latest'
			args '--entrypoint=""'
		}
	}
	stages {
		stage('Checkout') {
			steps {
				sh 'git clone "https://github.com/m5p3nc3r/meta-ewaol-machine.git" --branch kirkstone-dev'
			}
		}
		stage('Build Baremetal') {
			steps {
				dir('meta-ewaol-machine') {
					sh 'kas build kas/ewaol/baremetal.yml:kas/machine/generic-arm64.yml'
					archiveArtifacts artifacts: 'build/tmp_baremetal/deploy/**/*.wic*'
				}
			}
		}
	}
}

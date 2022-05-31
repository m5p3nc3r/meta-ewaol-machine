// Provides the standard structure for a Jenkins EWAOL (declarative) pipeline

def checkoutMachines(Map config) {
	sh "git clone \"https://github.com/m5p3nc3r/meta-ewaol-machine.git\" --branch ${config.branch}"
}

def build(Map config) {
        dir('meta-ewaol-machine') {
		sh "kas build kas/ewaol/${config.image}.yml:kas/machine/${config.machine}.yml"
		if (config.artifactTypes.contains("wic")) {
                	archiveArtifacts artifacts: "build/tmp_${config.image}/deploy/**/*.wic",
                        	excludes: "build/tmp_${config.image}/deploy/**/*.rootfs.wic"
		}
        }	
}

return this;

// Provides the standard structure for a Jenkins EWAOL (declarative) pipeline

def checkoutMachines(Map config) {
	sh "git clone \"https://github.com/m5p3nc3r/meta-ewaol-machine.git\" --branch ${config.branch}"
}

def build(Map config) {
	def kasfileMachineName
	def sdk = ""

	// If a kas filename is specified, use that. Otherwise, use the machine name.
	if (config.containsKey("kasfile")) {
		kasfileMachineName = config.kasfile
	} else {
		kasfileMachineName = config.machine
	}
	
	// If sdk image type specified, this affects the yml file composition, but not the TMPDIR
	if (config.get("sdk", false)) {
		sdk = "-sdk"
	}

        dir('meta-ewaol-machine') {
		sh "kas build kas/ewaol/${config.image}${sdk}.yml:kas/machine/${kasfileMachineName}.yml"
		if (config.artifactTypes.contains("wic")) {
                	archiveArtifacts artifacts: "build/tmp_${config.image}/deploy/**/*.wic",
                        	excludes: "build/tmp_${config.image}/deploy/**/*.rootfs.wic"
		}
        }	
}

return this;

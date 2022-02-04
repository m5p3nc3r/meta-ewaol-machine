SUMMARY = "Nvidia's Container Toolkit, including supporting libraries, for allowing creation of GPU accelerated Docker containers"
HOMEPAGE = "https://docs.nvidia.com/datacenter/cloud-native/container-toolkit/overview.html"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
SECTION = "base"

ASSUME_PROVIDED = "\
    virtual/docker-native \
"

CLEANBROKEN = "1"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "gitsm://github.com/NVIDIA/nvidia-container-toolkit;branch=master;protocol=https \
           file://0001-Add-GOARCH-customization.patch \
           file://0002-TEMPORARY-force-aarch64-rpm.patch"

SRCREV = "f10f533fb21ee4cd7c298681fc61cc5a1aa09551"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake -- --centos8-aarch64 'CC=${CC}' 'ARCH=aarch64'
}	

do_package_write_rpm() {
	# note that due to "sandbox escape" in using Docker, this RPM ends up with a different owner. Let's hope that's ok...	
	mv ${S}/dist/centos8/aarch64/nvidia-container-toolkit-1.7.0-1.aarch64.rpm ${DEPLOY_DIR_RPM}
}

# at the moment, this recipe will only work for aarch64 targets
python () {
    if not d.getVar("TARGET_ARCH").startswith('aarch64'):
        fatal("nvidia-container-toolkit recipe currently only supports aarch64 target arch.")
}

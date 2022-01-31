SUMMARY = "Nvidia's Container Toolkit, including supporting libraries, for allowing creation of GPU accelerated Docker containers"
HOMEPAGE = "https://docs.nvidia.com/datacenter/cloud-native/container-toolkit/overview.html"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
SECTION = "base"
DEPENDS = "libnvidia-container go-native"

CLEANBROKEN = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "git://github.com/NVIDIA/nvidia-container-toolkit;branch=master;protocol=https \
           file://0001-Add-GOARCH-customization.patch \
           file://0002-TEMPORARY-force-aarch64-rpm.patch"

SRCREV = "f10f533fb21ee4cd7c298681fc61cc5a1aa09551"

S = "${WORKDIR}/git"

inherit goarch

do_compile() {
	mkdir -p ${B}${bindir}
	oe_runmake cmds PREFIX="${B}${bindir}" GOOS="${TARGET_GOOS}"
}	

FILES_${PN} += "${datadir}/containers/oci/hooks.d/oci-nvidia-hook.json"

NCT_PKG_VERS = "1.7.0" 
NCT_PKG_REV = "1" 

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/nvidia-container-runtime
	install -d ${D}${libexecdir}/oci/hooks.d
	install -d ${D}${datadir}/containers/oci/hooks.d

	install -m 755 ${B}${bindir}/nvidia-container-runtime ${D}${bindir}
	install -m 755 ${B}${bindir}/nvidia-container-toolkit ${D}${bindir} 

	install -m 644 -T ${S}/config/config.toml.centos ${D}${sysconfdir}/nvidia-container-runtime/config.toml
	install -m 755 ${S}/oci-nvidia-hook ${D}${libexecdir}/oci/hooks.d
	install -m 644 ${S}/oci-nvidia-hook.json ${D}${datadir}/containers/oci/hooks.d 
	
	ln -sf -r ${D}${bindir}/nvidia-container-toolkit ${D}${bindir}/nvidia-container-runtime-hook
}

INSANE_SKIP_${PN} += "already-stripped"

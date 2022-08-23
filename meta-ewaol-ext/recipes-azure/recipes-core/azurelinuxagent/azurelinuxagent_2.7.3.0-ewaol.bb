SUMMARY = "Microsoft Azure Linux Agent"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cce6fd055830ca30ff78fdf077e870d6"

inherit systemd setuptools3_legacy

SRC_URI = " \
	git://github.com/benmordaunt/WALinuxAgent.git;branch=master;protocol=https \
	file://0001-no-distro.patch \
"
SRCREV = "6fc4d8d052d268c7f4e246ce35ce82af867180ff"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SETUPTOOLS_INSTALL_ARGS += " \
	--lnx-distro=${DISTRO} \
        --lnx-distro-version=${DISTRO_VERSION} \
        --lnx-distro-fullname='${DISTRO_NAME}' \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "waagent.service"

#  Do not use setuptools3.bbclass, as we need to install,
#  not gen a bdist_wheel

S = "${WORKDIR}/git"

DEPENDS += "python3-distro-native"
RDEPENDS:${PN} += " \
	bash \
	python3-pyasn1 \
	sudo \
	openssl \
	openssh \
	parted \
	e2fsprogs-mke2fs \
"

do_install:append () {
	# cd ${S}
	# ${STAGING_BINDIR_NATIVE}/python3-native/python3 setup.py \
	#	install --verbose ${SETUPTOOLS_BUILD_ARGS}

	# Remove empty directory
	rm -rf ${D}/usr/share

	# Configure waagent for cloud-init
	sed -i 's/Provisioning.Agent=auto/Provisioning.Agent=cloud-init/g' ${D}/etc/waagent.conf
	sed -i 's/ResourceDisk.Format=y/ResourceDisk.Format=n/g' ${D}/etc/waagent.conf
	sed -i 's/ResourceDisk.EnableSwap=y/ResourceDisk.EnableSwap=n/g' ${D}/etc/waagent.conf

	# Ensure that the service calls upon python3 instead of python
	sed -i 's/\/usr\/bin\/python/\/usr\/bin\/python3/g' ${D}/lib/systemd/system/waagent.service

}

FILES:${PN} += " \
	${base_libdir}/systemd/system/azure.slice \
	${base_libdir}/systemd/system/waagent.service \
	${base_libdir}/systemd/system/azure-vmextensions.slice \
"

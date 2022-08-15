SUMMARY = "Microsoft Azure Linux Agent"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cce6fd055830ca30ff78fdf077e870d6"
inherit systemd python3-dir

SRC_URI = " \
	git://github.com/benmordaunt/WALinuxAgent.git;branch=master;protocol=https \
	file://0001-no-distro.patch \
"
SRCREV = "6fc4d8d052d268c7f4e246ce35ce82af867180ff"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SETUPTOOLS_BUILD_ARGS = " \
	--lnx-distro=${DISTRO} \
        --lnx-distro-version=${DISTRO_VERSION} \
        --lnx-distro-fullname='${DISTRO_NAME}' \
	--install-base='${D}' \
	--install-lib='${D}/${PYTHON_SITEPACKAGES_DIR}' \
	--install-headers='${D}/include/python' \
	--install-scripts='${D}/bin' \
	--install-data='${D}' \
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

do_install() {
	cd ${S}
	${STAGING_BINDIR_NATIVE}/python3-native/python3 setup.py \
		install --verbose ${SETUPTOOLS_BUILD_ARGS}
}

FILES:${PN} += " \
	${base_libdir}/systemd/system/azure.slice \
	${base_libdir}/systemd/system/waagent.service \
	${base_libdir}/systemd/system/azure-vmextensions.slice \
	${PYTHON_SITEPACKAGES_DIR}/azurelinuxagent \
"

SUMMARY = "Microsoft Azure Linux Agent"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cce6fd055830ca30ff78fdf077e870d6"

SRC_URI = " \
	git://github.com/benmordaunt/WALinuxAgent.git;branch=master;protocol=https \
	file://0001-no-distro.patch \
"
SRCREV = "f99590f87713ab14bf927e1d6afc47900d9b87e8"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SETUPTOOLS_BUILD_ARGS = " \
	--lnx-distro=${DISTRO} \
        --lnx-distro-version=${DISTRO_VERSION} \
        --lnx-distro-fullname='${DISTRO_NAME}' \
        --sysroot='${D}' \
"

#  Do not use setuptools3.bbclass, as we need to install,
#  not gen a bdist_wheel

S = "${WORKDIR}/git"

DEPENDS += "python3-distro-native"
RDEPENDS_${PN} += " \
	bash \
	python3-pyasn1 \
	ip-route \
	sudo \
	openssl \
	openssh \
	parted \
	sfdisk \
	fdisk \
	mkfs \
"

do_install() {
	cd ${S}
	${STAGING_BINDIR_NATIVE}/python3-native/python3 setup.py \
		install --verbose ${SETUPTOOLS_BUILD_ARGS}
}

SUMMARY = "Library and CLI utility to configure GNU/Linux containers using Nvidia hardware"
HOMEPAGE = "https://github.com/NVIDIA/libnvidia-container"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "elfutils libtirpc libcap libseccomp"

EXTRAFILESPATHS_prepend = "${THISDIR}/${BPN}:"

SRC_URI = "git://github.com/NVIDIA/libnvidia-container.git;protocol=https;destsuffix=libnvidia;rev=f37bb387ad05f6e501069d99e4135a97289faf1f \
	git://github.com/NVIDIA/nvidia-modprobe.git;protocol=https;destsuffix=nvmodprobe;rev=292409904a5d18163fc7d1fbc11f98627324b82a;branch=main \
	file://0001-no-modprobe-fetch.patch \
	file://0002-disable-libtirpc-build.patch \
	file://0003-use-local-libtirpc.patch"

SRC_URI[sha256sum] = "f37bb387ad05f6e501069d99e4135a97289faf1f"

S = "${WORKDIR}/libnvidia"

inherit pkgconfig

# Use the tirpc rpc implementation
EXTRA_OEMAKE = 'CFLAGS="${CFLAGS} -I${STAGING_INCDIR}/tirpc"'

export OBJCPY="${OBJCOPY}"

do_compile() {
	oe_runmake WITH_LIBELF=yes WITH_TIRPC=yes BB_LIBDIR=${STAGING_LIBDIR}
}

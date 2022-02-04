FILESEXTRAPATHS:prepend:mx8 := "${THISDIR}:${THISDIR}/files:"
COMPATIBLE_MACHINE:mx8 = "mx8"

SRCBRANCH:mx8 = "lf-5.10.y"
SRCREV_machine:mx8 = "ef3f2cfc6010c13feb40cfb7fd7490832cf86f45"
#SRCREV_meta:mx8 = "ea88d1fbe10d60ebfd453bc0aec42a628d4e820f"
SRCREV_meta:mx8 = "de35f8006d0f932924752ddda94dd24e2da67fbc"

# SRCREV_meta = "2327ef4bf45040c46d701278c6bd8d98727e9d0a"

KMACHINE:mx8 = "nxp-imx8"

LINUX_VERSION:mx8 = "5.10.35"

SRC_URI:mx8 = " \
    git://source.codeaurora.org/external/imx/linux-imx;name=machine;protocol=https;branch=${SRCBRANCH} \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA} \
    file://imx-kmeta;type=kmeta;destsuffix=imx-kmeta \
    file://allow_kconfig_patch.patch \
"

# There is a partial implementation of the mx8-standard upstream in yocto-kernel-cache
# So just include the additional features we need here until they are put upstream
KERNEL_FEATURES:append:mx8 = " bsp/mx8.scc"

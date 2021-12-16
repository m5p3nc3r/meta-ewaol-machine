FILESEXTRAPATHS_prepend_mx8 := "${THISDIR}:${THISDIR}/files:"
COMPATIBLE_MACHINE_mx8 = "mx8"

SRCBRANCH_mx8 = "lf-5.10.y"
SRCREV_machine_mx8 = "ef3f2cfc6010c13feb40cfb7fd7490832cf86f45"
SRCREV_meta_mx8 = "ea88d1fbe10d60ebfd453bc0aec42a628d4e820f"
# SRCREV_meta = "2327ef4bf45040c46d701278c6bd8d98727e9d0a"

KMACHINE_mx8 = "nxp-imx8"

LINUX_VERSION_mx8 = "5.10.35"

SRC_URI_mx8 = " \
    git://source.codeaurora.org/external/imx/linux-imx;name=machine;protocol=https;branch=${SRCBRANCH} \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA} \
    file://imx-kmeta;type=kmeta;destsuffix=imx-kmeta \
    file://allow_kconfig_patch.patch \
"

# There is a partial implementation of the mx8-standard upstream in yocto-kernel-cache
# So just include the additional features we need here until they are put upstream
KERNEL_FEATURES_append_mx8 = " bsp/mx8.scc"

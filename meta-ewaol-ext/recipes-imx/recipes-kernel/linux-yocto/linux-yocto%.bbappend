FILESEXTRAPATHS:prepend:imx8mp-lpddr4-evk := "${THISDIR}:${THISDIR}/files:"
COMPATIBLE_MACHINE:imx8mp-lpddr4-evk = "imx8mp-lpddr4-evk"

SRCBRANCH:imx8mp-lpddr4-evk = "lf-5.15.y"
SRCREV_machine:imx8mp-lpddr4-evk = "c1084c2773fc1005ed140db625399d5334d94a28"

KMACHINE:imx8mp-lpddr4-evk = "nxp-imx8-ewaol"

LINUX_VERSION:imx8mp-lpddr4-evk = "5.15.5"

# This recipe piggybacks upstream linux-yocto, but we need ro remove the kernel source
# to replace it with the downstream kernel
SRC_URI:remove:imx8mp-lpddr4-evk = "git://git.yoctoproject.org/linux-yocto.git;name=machine;branch=${KBRANCH};"

# We don't need to include yocto-kernel-cache as it is defined in upstream linux-yocto
# Just add our machine specific fragments
# For details of how imx-kmeta was created, check the readme.md
SRC_URI:append:imx8mp-lpddr4-evk = " \
    git://source.codeaurora.org/external/imx/linux-imx;name=machine;protocol=https;branch=${SRCBRANCH} \
    file://imx-kmeta;type=kmeta;destsuffix=imx-kmeta \
"
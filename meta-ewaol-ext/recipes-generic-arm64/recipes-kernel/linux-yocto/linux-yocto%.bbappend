FILESEXTRAPATHS:prepend:generic-arm64 := "${THISDIR}/kmeta:"
SRC_URI:append:generic-arm64 = " file://generic-kernel_rpi4.cfg "
COMPATIBLE_MACHINE_generic-arm64 = "generic-arm64"

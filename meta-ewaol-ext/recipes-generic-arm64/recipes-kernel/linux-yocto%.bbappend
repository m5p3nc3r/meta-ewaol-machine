FILESEXTRAPATHS_prepend:= "${THISDIR}/kmeta:"
SRC_URI_append = " file://generic-kernel_rpi4.cfg "

COMPATIBLE_MACHINE = "generic-arm64"

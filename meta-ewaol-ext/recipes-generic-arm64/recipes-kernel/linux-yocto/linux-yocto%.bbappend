FILESEXTRAPATHS:prepend:generic-arm64 := "${THISDIR}/kmeta:"
SRC_URI:append:generic-arm64 = " file://generic-kernel_rpi4.cfg"

FILESEXTRAPATHS:prepend:azure-vm-arm64 := "${THISDIR}/kmeta:"
SRC_URI:append:azure-vm-arm64 = " file://azure-vm.cfg"

COMPATIBLE_MACHINE:generic-arm64 = "generic-arm64"
COMPATIBLE_MACHINE:azure-vm-arm64 = "azure-vm-arm64"

FILESEXTRAPATHS:prepend:generic-arm64 := "${THISDIR}/kmeta:"
SRC_URI:append:generic-arm64 = " file://generic-kernel_rpi4.cfg"

FILESEXTRAPATHS:prepend:azure-vm := "${THISDIR}/kmeta:"
SRC_URI:append:azure-vm = " file://azure-vm.cfg"

COMPATIBLE_MACHINE:generic-arm64 = "generic-arm64"
COMPATIBLE_MACHINE:azure-vm = "azure-vm"

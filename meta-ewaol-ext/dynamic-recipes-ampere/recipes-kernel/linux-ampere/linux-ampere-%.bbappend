SUMMARY = "lttng-modules required kprobes"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://lttng-modules.cfg"
COMPATIBLE_MACHINE_comhpc = "comhpc"


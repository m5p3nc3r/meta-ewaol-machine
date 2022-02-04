SUMMARY = "Give the ewaol user sudo rights"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001_ewaol"

FILES:${PN} += "${sysconfdir}/sudoers.d/0001_ewaol"

do_install:append () {
    install -m 0755 ${WORKDIR}/0001_ewaol ${D}/${sysconfdir}/sudoers.d
}
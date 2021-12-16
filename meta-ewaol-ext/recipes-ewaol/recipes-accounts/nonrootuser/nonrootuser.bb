SUMMARY = "Add a non-root user to the ewaol root filesystem"
LICENSE = "MIT"

inherit useradd

FILES_${PN} = "${datadir}/ewaol"

USERADD_PACKAGES = "${PN}"

# Create a used 'ewaol' with the password 'soafee'
USERADD_PARAM_${PN} = "-s /bin/sh -u 1000 -U -p LvrmxvVtfPBcU ewaol"

do_install () {
    install -d -m 755 -o ewaol ${D}${datadir}/ewaol
}
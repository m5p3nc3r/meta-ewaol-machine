#  Configure EWAOL cloud-init configuration for using Azure control plane

do_install:append:azure-vm () {
	sed -i 's/Provisioning.Agent=auto/Provisioning.Agent=cloud-init/g' ${D}/etc/waagent.conf
	sed -i 's/ResourceDisk.Format=y/ResourceDisk.Format=n/g' ${D}/etc/waagent.conf
	sed -i 's/ResourceDisk.EnableSwap=y/ResourceDisk.EnableSwap=n/g' ${D}/etc/waagent.conf

	echo "Adding mounts and disk_setup to init stage"
	sed -i '/ - mounts/d' ${D}/etc/cloud/cloud.cfg
	sed -i '/ - disk_setup/d' ${D}/etc/cloud/cloud.cfg
	sed -i '/cloud_init_modules/a\\ - mounts' ${D}/etc/cloud/cloud.cfg
	sed -i '/cloud_init_modules/a\\ - disk_setup' ${D}/etc/cloud/cloud.cfg

	echo "Allow only Azure datasource, disable fetching networking setting via IMDS"
	cat > ${D}/etc/cloud/cloud.cfg.d/91-azure_datasource.cfg <<EOF
datasource_list: [ Azure ]
datasource:
  Azure:
    apply_network_config: False
EOF
	if [[ -f ${D}/mnt/resource/swapfile ]]; then
	echo "Removing swapfile"
	swapoff ${D}/mnt/resource/swapfile
	rm ${D}/mnt/resource/swapfile -f
	fi
}

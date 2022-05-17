# EWAOL on IMX8 Documentation

## Status

The baremetal image builds, but is currently missing kernel configuration to enable k3s to operate properly.  We have created a local downstream kmeta based recipe that derives from the upstream meta-freescale.  Information on this process is captured in the [recipes-imx/README.md](../meta-ewaol-ext/recipes-imx/README.md).  A question has been [raised upstream](https://github.com/Freescale/meta-freescale/issues/1087) to see if we can get this adopted, but for now, this is functinoal.

You currently get a lot of warnings during the kernel build, this is because of configuration conflicts in ther kernel.  This is not a problem for the resulting build, I don't plan on fixing the warnings because they don't currently impact the functionality.

The virtualization image is not currently functional.

## Building

```bash
./build.sh imx8 baremetal
```

## Installing

Assuming your SD card is at ```/dev/mmcblk0```.

```bash
sudo bmaptool copy build/imx8/tmp_baremetal/deploy/images/imx8mp-lpddr4-evk/ewaol-baremetal-image-imx8mp-lpddr4-evk.wic.gz /dev/mmcblk0

```

## Serial Console

Configuration 115200 8N1, no flow control.

The console is available on the third UART interface, so if they are enumerated from 0, you want to connect to ```/dev/ttyUSB2```.

## Known issues

- virtualization image not tested
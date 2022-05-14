# EWAOL on IMX8 Documentation

## Status

The baremetal image builds, but is currently missing kernel configuration to enable k3s to operate properly.  A question has been [raised upstream](https://github.com/Freescale/meta-freescale/issues/1087) on the best way to resolve this issue.

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

- k3s kernel configuration not applying
- virtualization image not tested
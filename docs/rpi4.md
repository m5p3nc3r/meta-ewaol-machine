# EWAOL on RPi4 Documentation

## Status

The baremetal image is working, there is work needed to get the virtualization support functional

## Building

```bash
./build.sh rpi4 baremetal
```

## Installing

Assuming your SD card is at ```/dev/mmcblk0```.

```bash
sudo bmaptool copy build/rpi4/tmp_baremetal/deploy/images/raspberrypi4-64/ewaol-baremetal-image-raspberrypi4-64.wic.bz2 /dev/mmcblk0

```

## Serial Console

Configuration 115200 8N1, no flow control.

Details for attaching a USB UART cable can be found [here](https://www.jeffgeerling.com/blog/2021/attaching-raspberry-pis-serial-console-uart-debugging)

## Known issues

- virtualization image not tested
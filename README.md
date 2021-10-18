# Extra machines for [EWAOL](https://gitlab.arm.com/ewaol/meta-ewaol)

This project contains [kas](https://kas.readthedocs.io/en/latest/index.html)
configuration to download meta-ewaol and all required layers for multiple
machines not supported by the upstream project.
[EWAOL](https://gitlab.arm.com/ewaol/meta-ewaol) is the reference implementation
of the [SOAFEE](http://soafee.io) project.

_At this point in time, this is very much a 'pet project', so please accept the
code as you find it, and please don't expect it to always be working._

## Build

Install the kas tool by following the instructions
[here](https://kas.readthedocs.io/en/latest/userguide.html)

```bash
# Building for the imx8mp target
kas build machines/ewaol-imx8.yml
```

Or, if you are building for multiple targets, it can be useful to share download
assets, build caches and you will have to specify a different output directory.

```bash
# Building for the imx8mp target
KAS_BUILD_DIR=$PWD/build-imx8 \
SSTATE_DIR=$HOME/yocto/sstate-cache \
DL_DIR=$HOME/yocto/downloads \
kas build machines/ewaol-imx8.yml
```

## Install

```bash
# Assuming your installing to /dev/mmcblk0
# If you are installing elsewhere, please change this location
sudo bmaptool copy build-imx8/tmp/deploy/images/imx8mpevk/ewaol-image-docker-imx8mpevk.wic.bz2 /dev/mmcblk0
```

## Status

| Machine | EWAOL Version |Status | Notes |
|---------|---------------|-------|-------|
| RPi4    | v0.1.1 | Ok | Booting, able to ssh to target and run a container |
| imx8mp  | v0.1.1 | Ok | Booting, able to ssh to target and run a container |
| vim3    | v0.1.1 | Compiles | Not yet tested

## Machine specific notes

### iMX8

The project achieves its goals by adding linux-yocto recipes to the default
meta-imx recipes, with patches added to the upstream to enable it to apply
properly.

The linux-yocto recipes have only been tested on the imx8mplus platform, but it
may work on others.  The aim would be to have the linux-yocto recipes adopted
by the upstream projects (either meta-imx or meta-freescale).

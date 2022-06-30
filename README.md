# Extra machines for [EWAOL](https://gitlab.arm.com/ewaol/meta-ewaol)


__NOTE, EWAOL Kirkstone has not yet been released, this will not work for you yet__

This project contains [kas](https://kas.readthedocs.io/en/latest/index.html)
configuration to download meta-ewaol and all required layers for multiple
machines not supported by the upstream project.
[EWAOL](https://gitlab.arm.com/ewaol/meta-ewaol) is the reference implementation
of the [SOAFEE](http://soafee.io) project.

_At this point in time, this is very much a 'pet project', so please accept the
code as you find it, and please don't expect it to always be working._

## Build

Install the kas tool, ensuring you have a version 3.0.2 or greater, by following the instructions
[here](https://kas.readthedocs.io/en/latest/userguide.html).

To build ewaol, use the helper script ```build.sh``` which gives a consistent way of building for the target platforms.

```bash
# Building for the rpi4 target
./build.sh rpi4 baremetal
```

## Install

Installation instructions for each platform is outlined in the documentation for that platform.  Check out the table in the status section below.

## Status

| Machine | EWAOL Version |Status | Documentation |
|---------|---------------|-------|-------|
| RPi4    | v1.0 | Ok | [RPi4 Documentation](docs/rpi4.md) |
| imx8mp  | v1.0 | Ok | [IMX8 Documentation](docs/imx8.md) |
| vim3    | v1.0 | Compiles | [VIM3 Documentation](docs/vim3.md) |
| avadp   | v1.0 | Ok | [AVA Documentation](docs/ava.md) |  
| generic-arm64 | v1.0 | Compiles | [Generic arm64 Documentation](docs/generic-arm64.md) |

## Machine specific notes

__TODO: MOVE THESE NOTES INTO THE PLATFORM DOCS__

### iMX8

The project achieves its goals by adding linux-yocto recipes to the default
meta-imx recipes, with patches added to the upstream to enable it to apply
properly.

The linux-yocto recipes have only been tested on the imx8mplus platform, but it
may work on others.  The aim would be to have the linux-yocto recipes adopted
by the upstream projects (either meta-imx or meta-freescale).

### generic-arm64

This has only been tested on the RPi4 using [SystemReady compliant UEFI firmware](https://github.com/pftf/RPi4).

Put UEFI firmware on the sdcard and the EWAOL image on a usb drive.

## Recipe specific notes

### nvidia-container-toolkit

This recipe is in beta. It should work relatively well, with perhaps a few rough edges.
Specifically, take note of the following:

- nvidia-modprobe is fetched and built as part of libnvidia-container
- libnvidia-container technically depends on a specific version of libtirpc, but we're fetching Poky's standard version
  from recipes-extended. If this causes trouble, this will need to be pinned to the lib's desired version.

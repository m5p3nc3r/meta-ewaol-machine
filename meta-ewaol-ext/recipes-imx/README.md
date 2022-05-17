# i.mx8 linux-yocto recipe

These recipes derive from the upstream linux-yocto as much as possible.  All
that should be needed is adding:

- The downstream kernel to SRC_URI
- A kmeta compatible configuration for platform

The downstream kernel SRC_URI is taken directly from the meta-freescale recipes.

The kmeta is a little more complicated, but makes used of the awesome yocto
tooling to help create.

- https://elinux.org/images/d/d7/Yps2021.11-bsp.pdf
- https://www.youtube.com/watch?v=tZ-JDdm5yOo

The base configuration was created with

```bash
kgit-config create --bsp nxp-imx8-ewaol \
  --ksrc <kernel source> \
  --defconfig <original configuration> \
  --kmeta <linux-yocto-cache> \
  -o .
```

It doesn't work in its default form, you need to remove CONFIG_DMA, and there
are plenty of warnings at the moment.  But the baremetal EWAOL system works.
#!/bin/sh

TARGET=${1:-rpi4}
TYPE=${2:-baremetal}

echo Building EWAOL $TYPE for $TARGET

if [ -z $KAS_BUILD_DIR ]; then
    KAS_BUILD_DIR=$PWD/build/$TARGET
fi
if [ -z $DL_DIR ]; then
    DL_DIR=$HOME/yocto_cache/download
fi
if [ -z $SSTATE_DIR ]; then
    SSTATE_DIR=$HOME/yocto_cache/sstate
fi

KAS_BUILD_DIR=$KAS_BUILD_DIR SSTATE_DIR=$SSTATE_DIR DL_DIR=$DL_DIR kas build kas/ewaol/$TYPE.yml:kas/machine/$TARGET.yml

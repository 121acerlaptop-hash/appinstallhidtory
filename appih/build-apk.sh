#!/bin/sh
set -e
gradle assembleDebug
printf '\nAPK created at: app/build/outputs/apk/debug/app-debug.apk\n'

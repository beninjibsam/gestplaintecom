#!/usr/bin/env bash
# Build APK via Docker (no local JDK/Android SDK needed).
set -euo pipefail

cd "$(dirname "$0")"

IMAGE="${IMAGE:-cimg/android:2024.04.1-node}"
BUILD_TYPE="${1:-debug}"        # debug | release
GRADLE_TASK="assemble$(echo "${BUILD_TYPE}" | sed 's/.*/\u&/')"

echo ">> Building APK (${BUILD_TYPE}) using ${IMAGE}"

# Ensure gradle cache + workspace are writable by current user
mkdir -p .gradle

docker run --rm \
    -v "$(pwd):/workspace" \
    -w /workspace \
    -u "$(id -u):$(id -g)" \
    -e HOME=/tmp \
    -e GRADLE_USER_HOME=/workspace/.gradle \
    "${IMAGE}" \
    bash -c "gradle ${GRADLE_TASK} --no-daemon --stacktrace"

APK_PATH=$(find app/build/outputs/apk -name "*.apk" | head -1 || true)
if [[ -n "${APK_PATH}" ]]; then
    cp "${APK_PATH}" "./PlaintesCommerciaux-${BUILD_TYPE}.apk"
    echo ""
    echo "==============================================="
    echo " APK généré : $(pwd)/PlaintesCommerciaux-${BUILD_TYPE}.apk"
    echo " Taille    : $(du -h "PlaintesCommerciaux-${BUILD_TYPE}.apk" | cut -f1)"
    echo "==============================================="
else
    echo "ERREUR : APK introuvable" >&2
    exit 1
fi

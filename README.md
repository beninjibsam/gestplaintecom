# Plaintes Commerciaux — Android Wrapper

Application Android (WebView) avec splashscreen animé qui ouvre
`http://41.223.234.74:5173/` en plein écran.

## Compilation de l'APK (sans installer Android Studio)

Prérequis : **Docker**.

```bash
chmod +x build-apk.sh
./build-apk.sh             # APK debug -> PlaintesCommerciaux-debug.apk
./build-apk.sh release     # APK release (signé avec la clé debug)
```

Installer sur un téléphone :

```bash
adb install -r PlaintesCommerciaux-debug.apk
# ou : transférer le .apk sur le téléphone et l'ouvrir
```

## Structure

| Fichier | Rôle |
|---|---|
| `app/src/main/java/.../SplashActivity.java` | Splashscreen animé (logo + texte) |
| `app/src/main/java/.../MainActivity.java`  | WebView plein écran sur l'URL |
| `app/src/main/res/drawable/logo_emblem.xml` | Logo vectoriel (bouclier + document) |
| `app/src/main/res/layout/activity_splash.xml` | UI du splash |
| `app/src/main/res/anim/*.xml` | Animations (logo, texte, dots) |

## Configurer l'URL cible

Modifier `target_url` dans
`app/src/main/res/values/strings.xml`.

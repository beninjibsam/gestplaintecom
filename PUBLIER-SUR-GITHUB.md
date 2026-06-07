# Comment obtenir l'APK via GitHub Actions

Le workflow `.github/workflows/build.yml` compile l'APK automatiquement
sur les serveurs de GitHub. Aucun Docker, JDK ou Android SDK requis localement.

## 1. Créer un dépôt GitHub vide

Sur https://github.com → **New repository** → nom au choix
(ex. `plaintes-mobile`) → **Private** ou **Public** → **Create repository**
(sans cocher README, .gitignore, license).

## 2. Pousser le code (depuis WSL Ubuntu)

```bash
cd ~/plaintes-mobile

git init -b main
git add .
git commit -m "Initial commit: Plaintes Commerciaux Android app"

# Remplacer URL par celle de TON dépôt
git remote add origin https://github.com/<TON-USER>/plaintes-mobile.git
git push -u origin main
```

> Si Git demande une authentification, utilise un **Personal Access Token**
> (https://github.com/settings/tokens) comme mot de passe.

## 3. Récupérer l'APK

1. Va sur `https://github.com/<TON-USER>/plaintes-mobile/actions`
2. Attends ~3 min que le workflow **Build Android APK** passe au vert ✅
3. Clique sur le run → en bas, section **Artifacts** →
   télécharge `PlaintesCommerciaux-debug-apk.zip`
4. Dézippe → tu obtiens `PlaintesCommerciaux-debug.apk`

## 4. Installer sur ton téléphone Android

- Transfère le `.apk` (USB, email, Drive, etc.)
- Sur le téléphone : ouvrir le fichier
- Autoriser **"Installer depuis cette source"** si demandé
- Installer

L'app affichera le splashscreen animé puis ouvrira
http://41.223.234.74:5173/ en plein écran.

## Build APK signé pour release (plus tard)

Pour distribuer hors développement, il faut signer avec une clé prod.
Voir Android docs : https://developer.android.com/studio/publish/app-signing

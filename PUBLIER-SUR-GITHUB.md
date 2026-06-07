# Compiler & installer l'APK

Le workflow GitHub Actions produit **2 APKs** à chaque push :

| APK | Usage | Signature |
|---|---|---|
| `CorisPlaintes-release.apk` | **Pour distribution** (utilisateurs finaux) | Signé clé release (stable) |
| `CorisPlaintes-debug.apk` | Test développeur (logs, debug) | Signé clé debug |

## 1. Récupérer l'APK release

1. Push tes modifs sur GitHub (`git push`)
2. https://github.com/beninjibsam/gestplaintecom/actions
3. Attends le ✅ (~3 min)
4. Clique sur le run → en bas, section **Artifacts**
5. Télécharge **`CorisPlaintes-release-apk`** → dézippe → tu obtiens `CorisPlaintes-release.apk`

## 2. Installer sur Android

### Le premier utilisateur sur le téléphone

1. Ouvrir le `.apk` reçu (USB / mail / Drive / WhatsApp)
2. Android demande : **« Autoriser les installations depuis cette source »** → activer
   (Paramètres → Applis → Accès spécial → Installation d'apps inconnues)
3. **Installer**
4. Lancer **Coris Plaintes** depuis le tiroir d'applis

### Les mises à jour (push suivants)

- Tu pousses du code → nouvel APK généré
- L'utilisateur ouvre le nouveau `.apk` → **Android l'installe par dessus l'ancien sans rien redemander**
  (parce que la signature est la même : clé release stable)

## 3. Pour un install sans aucun avertissement

Seule solution : passer par le **Google Play Store** (compte développeur 25 $ unique).

- Créer un compte : https://play.google.com/console
- Suivre `app/build.gradle` → builder un `bundleRelease` (.aab) au lieu de `assembleRelease`
- Uploader le `.aab` sur Play Console → piste *Production* ou *Test interne*
- Les utilisateurs installent depuis le Play Store → zéro avertissement, Play Protect validé

Pour distribution interne entreprise (sans Play Store public) :
- Piste **« Test interne »** dans Play Console (max 100 testeurs, lien direct)
- ou MDM (Mobile Device Management) si flotte d'entreprise

## Identifiants signature (à conserver)

- Keystore : `release.keystore` (dans le repo, format PKCS12)
- Mot de passe store/key : `CorisPlaintes2026`
- Alias : `plaintes`
- Validité : 100 ans

⚠️ **Ne jamais perdre ce fichier** : sans lui tu ne pourras plus publier de mise à jour signée par la même clé.

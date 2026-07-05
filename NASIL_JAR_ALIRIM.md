# Bilgisayarina hicbir sey kurmadan hazir .jar dosyasini almak (GitHub Actions)

Bu klasore otomatik derleme dosyasi (`.github/workflows/build.yml`) eklendi.
Asagidaki adimlari izleyerek jar dosyasini kod yazmadan/Gradle kurmadan alabilirsin.

## Adimlar

1. https://github.com adresine git, yoksa ucretsiz bir hesap ac.
2. Sag ustten **"+"** > **"New repository"** ile yeni, **public** bir repo olustur
   (ismi onemli degil, orn: `gorsel-ayar-mod`). "Add a README" kutucugunu isaretleme.
3. Repo acildiktan sonra **"uploading an existing file"** linkine tikla
   (ya da Code sekmesinde "Add file" > "Upload files").
4. Bu zip'in **icindeki tum dosya ve klasorleri** (build.gradle, gradle.properties,
   settings.gradle, src/, .github/ klasoru dahil hepsini) surukle-birak ile yukle.
   - Onemli: zip'i degil, zip'in ICINDEKI dosyalari yukle.
   - `.github` gibi gizli/nokta ile baslayan klasorlerin de gorunup surukleneceginden
     emin ol; gorunmuyorsa dosya gezgininde "gizli dosyalari goster" secenegini ac.
5. Altta "Commit changes" butonuna bas.
6. Repo sayfasinda ustteki **"Actions"** sekmesine git. Birkac dakika icinde
   otomatik olarak bir derleme (workflow run) baslayacak (yesil tik = basarili).
7. Basarili calisma satirina tikla, en altta **"Artifacts"** bolumunde
   `gorsel-ayar-mod-jar` adinda bir dosya gorunecek, tikla indir.
   Bu indirdigin sey bir zip olacak, icinde asil `.jar` dosyan var.
8. O `.jar` dosyasini `.minecraft/mods` klasorune at.

## Ayrica gerekenler (oyunda calismasi icin)

- Fabric Loader 1.21.11 icin kurulu olmali (fabricmc.net/use).
- Fabric API modu (modrinth.com/mod/fabric-api, 1.21.11 surumu) de ayni
  `mods` klasorune atilmali.
- Sonra Fabric profiliyle oyunu ac; C / V / X tuslari calisir durumda olacak.

## Eger Actions sekmesinde hicbir calisma gorunmuyorsa

- Repo'nun ayarlarindan Actions'in acik oldugunu kontrol et:
  Settings > Actions > General > "Allow all actions".
- "workflow_dispatch" ile de manuel tetikleyebilirsin: Actions sekmesi >
  soldaki "Build Mod" > sag ust "Run workflow".

# Gorsel Ayar Modu (Fabric - Minecraft 1.21.11)

Bu mod, oyun icindeyken 3 tusla gorsel (video) ayarlarini aninda degistirmeni saglar.
**Hem tek kisilik dunyalarda hem de her turlu sunucuda (multiplayer) calisir**, cunku
tamamen istemci taraflidir; bu ayarlar zaten sadece kendi ekranini/render'ini etkiler,
sunucunun bir sey yapmasi gerekmez. `fabric.mod.json` icinde `"environment": "client"`
olarak isaretlendigi icin sunucunun bu modu bilmesine bile gerek yok - vanilla veya
Fabric fark etmeksizin herhangi bir sunucuya baglanabilirsin.

## Tus atamalari

| Tus | Etki |
|-----|------|
| **C** | Tum gorsel ayarlarini **EN DUSUK** seviyeye ceker (performans modu) |
| **V** | Tum gorsel ayarlarini **EN YUKSEK** seviyeye ceker (kalite modu) |
| **X** | Ozel profil uygular: **Chunk (gorus mesafesi) = 32**, **Parlaklik = Full (%100)**, **Bulutlar = Kapali**, **Parcaciklar = En Az**, **Golgeler = Kapali**, **Kare hizi = Sinirsiz**. Bu 5 ayar disindaki her sey Minecraft'in **varsayilan (fabrika) degerine** doner. |

Bir tusa bastiginda ekranin sol ustunde hangi profilin uygulandigini gosteren kisa bir mesaj cikar.

## Bu proje ne icermiyor?

Bu klasor mod'un **kaynak kodudur** (Java + Gradle projesi). Guvenlik/derleme
kisitlamalari nedeniyle burada calisir bir `.jar` dosyasi derlenmemistir; bunu
kendi bilgisayarinda (internet baglantisi olan) tek bir komutla derleyebilirsin.

## Nasil derlenir (jar haline getirilir)?

Gereksinimler: **JDK 21** (Minecraft 1.21.x icin zorunlu) ve internet baglantisi
(Gradle bagimliliklarini indirmek icin).

1. Zip dosyasini bir klasore cikar.
2. Terminal / CMD ac, o klasore gir.
3. Su komutu calistir:
   - Windows: `gradlew.bat build`
   - Mac/Linux: `./gradlew build`
4. Derleme bitince jar dosyan burada olusur:
   `build/libs/gorsel-ayar-mod-1.0.0.jar`

> Not: `gradlew` / `gradlew.bat` sarmalayici (wrapper) dosyalari bu zip'te yok.
> Eger `gradlew` komutu calismazsa, bilgisayarinda kurulu Gradle 8.10+ ile
> `gradle wrapper` komutunu bir kere calistirip wrapper dosyalarini olustur,
> sonra tekrar `./gradlew build` dene. Alternatif olarak IntelliJ IDEA'da
> projeyi acip Gradle panelinden "build" gorevini calistirabilirsin.

## Nasil kurulur (oyunda kullanilir)?

1. Fabric Loader'i (fabricmc.net/use) Minecraft **1.21.11** icin kur
   (guncel surum 0.18.1).
2. Fabric API modunu (modrinth.com/mod/fabric-api) **1.21.11** icin
   indirip `.minecraft/mods` klasorune at (bu mod, Fabric API'ye ihtiyac duyar).
3. Yukarida derledigin `gorsel-ayar-mod-1.0.0.jar` dosyasini da ayni
   `.minecraft/mods` klasorune kopyala.
4. Fabric profiliyle oyunu baslat. Tuslar varsayilan olarak C / V / X'tir;
   istersen Secenekler > Kontroller > Gorsel Ayar Modu bolumunden degistirebilirsin.
5. Herhangi bir sunucuya (vanilla, Fabric, Paper, vs.) bagladiginda tuslar
   aynen calismaya devam eder; sunucu tarafinda hicbir kurulum gerekmez.

## Onemli notlar (surum uyumu)

- Kod, Minecraft **1.21.11** ve Yarn mappings **1.21.11+build.4** referans
  alinarak yazilmistir. 1.21.11, Fabric'in Yarn mapping kullandigi **son**
  surumdur; sonraki Minecraft surumlerinde Fabric dogrudan Mojang mapping'lerine
  geciyor, dolayisiyla ileriki bir surume gecersen build script'i ve bazi
  metot isimlerini yeniden uyarlaman gerekebilir.
- 1.21.9 ile birlikte tus baglama (KeyBinding) kategorileri artik metin yerine
  `KeyBinding.Category` nesnesiyle tanimlaniyor; bu proje zaten bu yeni API'yi
  kullanacak sekilde yazildi.
- Eger derleme sirasinda `getViewDistance()`, `getGamma()` gibi bir metot
  bulunamiyor hatasi alirsan, IDE'nin otomatik tamamlamasiyla (Ctrl+Space)
  `client.options.` yazdiktan sonra dogru metot adini kontrol edebilirsin;
  bu tur GameOptions getter'lari surumden surume nadiren de olsa degisebiliyor.

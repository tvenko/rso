# Računalniške storitve v oblaku

Za pravilno delovanje mora biti vsak direktorij svoj repozitorij, saj je samostojna mikrostoritev. Zbral sem jih v en repozitorij, da imam vse na enem mestu

## Cilj

Pri predmetu smo razvijali demo aplikacijo sestavljeno iz mikrostoritev, 
ki je skladna s koncepti cloud-native arhitekture. Aplikacija je zgrajena iz samostojnih mikrostoritev,
pakiranih v vsebnikih, pri čemer smo izkoristili prednosti cloud-native arhitekture, kot so elastičnost,
prilagodljivost in odpornosti na napake. Pri tem smo se seznanili z množico naprednih vidikov kot so
konfiguriranje mikrostoritev z uporabo konfiguracijskega strežnika, dinamično odkrivanje storitev s porazdeljevanjem obremenitve,
najboljše prakse logiranja mikrostoritev, uporaba API prehodov,
nadzor delovanja mikrostoritev in načini zbiranja metrik ter preverjanja zdravja in ostalih naprednih tem,
kot so prekinjevalci toka, dogodkovne platforme in ostalo.

## Koncepti

* zvezna integracija s Travis (vse spremembme na master branchu git huba, prevede, zapakira, pozene unit tetse in deploya na docker hub)
* API za preverjanje zdravja - [KumuluzEE Health](https://github.com/kumuluz/kumuluzee-health)
* končna točka, na kateri so izpostavljene nekatere metrike - [KumuluzEE Metrics](https://github.com/kumuluz/kumuluzee-metrics)
* Kubernetes logs
* centralni beleženje dnevnikov - [KumuluzEE Logs](https://github.com/kumuluz/kumuluzee-logs)
* izolacija in toleranca napak - [KumuluzEE Fault Tolerance](https://github.com/kumuluz/kumuluzee-fault-tolerance)

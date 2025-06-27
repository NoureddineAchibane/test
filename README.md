# Projet Java — Gestion Bancaire & Réservation d’Hôtel

Ce projet Maven multi-modules comprend :
- Un **module bancaire** pour la gestion des comptes (dépôts, retraits, relevés)
- Un **module réservation d’hôtel** pour la gestion de chambres, utilisateurs et réservations

L’objectif est de fournir une base logicielle robuste, claire et facilement extensible, respectant les meilleures pratiques de l’ingénierie logicielle.

---

## Arborescence du projet

```plaintext
.
├── pom.xml                       # POM parent
├── banking-service/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/skypay/bank/
│       │     ├── AccountService.java
│       │     ├── Account.java
│       │     └── Main.java
│       └── test/java/com/skypay/bank/
│             └── AccountTest.java
└── hotel-reservation/
    ├── pom.xml
    └── src/
        ├── main/java/com/skypay/hotel/
        │     ├── RoomType.java
        │     ├── Room.java
        │     ├── User.java
        │     ├── Booking.java
        │     ├── Service.java
        │     └── Main.java
        └── test/java/com/skypay/hotel/
              └── ServiceTest.java

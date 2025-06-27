# Projet Java — Gestion Bancaire & Réservation d’Hôtel

## Design – Questions Bonus

**1. Est-il recommandé de centraliser toutes les fonctions dans un même service ?**  
*Réponse :*  
Non, cela viole le Single Responsibility Principle (**SRP**). Il est préférable de séparer les responsabilités dans des services dédiés afin de garantir la maintenabilité, l’évolutivité et la testabilité de la solution.

**2. Comment garantir que la modification d’une chambre n’impacte pas les réservations déjà créées ?**  
*Réponse :*  
Pour préserver l’historique, j’applique le **Snapshot Pattern** : chaque réservation (`Booking`) capture et conserve le type et le prix de la chambre au moment de la réservation. Ainsi, toute modification ultérieure de la chambre n’a aucun effet sur les réservations existantes.

---

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

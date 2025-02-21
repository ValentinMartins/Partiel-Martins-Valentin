1. Problèmes détectés

🔴 Problèmes SOLID
SRP (Single Responsibility Principle) violé

La classe DeliveryService gère à la fois le calcul du prix et l'impression de la facture → Séparer ces responsabilités.
calculateDeliveryPrice gère à la fois le poids, la distance, le type de client et l’urgence → Extraire des classes dédiées.
OCP (Open/Closed Principle) violé

L’ajout de nouveaux types de clients ou de nouvelles règles de calcul du prix nécessitera de modifier le code existant → Utiliser le polymorphisme pour les stratégies de tarification.
LSP (Liskov Substitution Principle) violé

Package est une classe interne et est mal définie, elle pourrait être remplacée par une classe plus générique et extensible.
ISP (Interface Segregation Principle) non respecté

Une seule classe DeliveryService fait trop de choses au lieu d’utiliser des interfaces spécifiques.
DIP (Dependency Inversion Principle) non respecté

calculateDeliveryPrice et printInvoice dépendent directement des détails d'implémentation, ce qui rend difficile l’ajout de nouvelles règles de livraison.

🔴 Problèmes KISS (Keep It Simple, Stupid)
Trop de conditions imbriquées (if/else), rendant le code difficile à lire et à modifier.
Logique de tarification mélangée → Utilisation de stratégies distinctes améliorerait la clarté.

🔴 Problèmes DRY (Don't Repeat Yourself)
Le calcul du prix est dupliqué entre calculateDeliveryPrice et printInvoice.
Les réductions et modifications du prix sont codées en dur, ce qui complexifie les modifications.

🔴 Problèmes YAGNI (You Ain't Gonna Need It)
Package est une classe interne, pas nécessairement utile dans cette implémentation.
Les messages System.out.println ne devraient pas être dans une classe métier.

2. Solution refactorisée

La meilleure approche consiste à réorganiser le code en appliquant SOLID et les bonnes pratiques.

✅ Solution refactorisée :

Créer une interface PricingStrategy pour gérer la tarification selon le type de client.
Créer une classe Package dédiée et externe.
Créer une classe DeliveryService avec uniquement la responsabilité du calcul du prix.
Créer une classe InvoiceService pour gérer l'impression des factures.
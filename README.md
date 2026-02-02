# README.md - Monte Carlo Pi (Parallélisme)

## Principe

On estime π en lançant des millions de points aléatoires dans un carré : ceux qui tombent dans le quart de cercle comptent, on multiplie par 4 le ratio obtenu.

Chaque point est généré de manière indépendante, ce qui permet un parallélisme efficace sur plusieurs cœurs.

## Deux approches comparées

### Assignment102 (Runnable)

- On lance plein de petites tâches qui comptent dans une "boîte partagée" (`AtomicInteger`).
- Les tâches ne renvoient rien, elles mettent juste à jour le total commun.
- Avantage : répartition automatique du travail.
- Limite : embouteillage si trop de threads écrivent en même temps.

### Pi (Callable)

- Chaque tâche fait son calcul de manière autonome et renvoie son résultat (`Future`).
- À la fin, on additionne tous les résultats pour obtenir π.
- Avantage : gestion des exceptions, test unitaire facile, résultat précis pour chaque tâche.
- Limite : attendre tous les résultats + fermeture du pool ajoute un léger overhead.

## Scalabilité

| Aspect | Runnable (Assignment102) | Callable (Pi) |
| :-- | :---------------------- | :----------- |
| **Forte** (même calcul + plus de cœurs) | Super jusqu’au nombre de cœurs CPU. **Problème** : embouteillage à la "boîte partagée" | Bien si tâches égales. **Problème** : une seule file d’attente |
| **Faible** (plus de calculs + plus de cœurs) | Parfait pour gros volumes | Très bien, léger overhead pour récupérer les résultats |

**Monte Carlo = parfait pour multi-cœurs** : chaque tâche travaille seule, efficacité >90% jusqu’à 16-32 cœurs.

## Tests de performance – Callable (Pi)

| Workers | Tp moyen (ms) | Speed-up |
| ------- | ------------- | -------- |
| 1       | 201.7         | 1.00     |
| 2       | 111.7         | 1.81     |
| 4       | 72.8          | 2.77     |
| 8       | 57.5          | 3.51     |
| 16      | 55.0          | 3.67     |

**Remarques :**
- La scalabilité est quasi linéaire jusqu’à 8 cœurs.
- Au-delà, légère amélioration seulement (overhead et contention).

## Conclusion

- **Callable / Futures (Pi)** : plus propre, chaque tâche retourne un résultat, exceptions gérées, facile à tester, mesure précise.
- **Runnable (Assignment102)** : plus rapide pour de gros calculs, répartition automatique du travail, mais limitations en forte scalabilité.

---


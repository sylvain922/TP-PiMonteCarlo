# README.md - Monte Carlo Pi (Parallélisme)

## Principe

On estime π en lançant des millions de points aléatoires dans un carré : ceux qui tombent dans le quart de cercle comptent, on multiplie par 4 le ratio obtenu.

## Deux approches comparées

### Assignment102 (Runnable)

- On lance plein de petites tâches qui comptent dans une "boîte partagée" (AtomicInteger).
- Les tâches ne renvoient rien, elles mettent juste à jour le total commun.


### Pi (Callable)

- Chaque tâche fait son calcul toute seule et renvoie son résultat.
- À la fin, on additionne tous les résultats.


## Scalabilité

| Aspect | Runnable (Assignment102)                                                                    | Callable (Pi)                                                  |
| :-- |:--------------------------------------------------------------------------------------------|:---------------------------------------------------------------|
| **Forte** (même calcul + plus cœurs) | Super bien jusqu'au nombre de cœurs CPU. **Problème** : embouteillage à la "boîte partagée" | Bien si tâches égales. **Problème** : une seule file d'attente |
| **Faible** (plus calculs + plus cœurs) | Parfait pour gros volumes                                                                   | Très bien, petit temps en plus pour résultats                  |

**Monte Carlo = parfait pour multi-cœurs** : chaque tâche indépendante, efficacité >90% jusqu'à 16-32 cœurs.

## Tests de vitesse

```
Callable (Pi)     : 34ms
Runnable (Assign) : 33ms
→ Performances égales
```

## Conclusion

**Callable/Futures (Pi)** pour la pédagogie/qualité : retourne résultats, exceptions gérées, test unitaire facile, mesure précise.

**Runnable (Assignment102)** pour la vitesse pure (gros calculs). Il répartit mieux le travail automatiquement.





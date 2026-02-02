# Nombre de workers
workers <- c(1, 2, 4, 8, 16)

# Temps d'exécution (Tp) pour chaque worker
tp_list <- list(
  w1  = c(200,205,204,204,188,204),
  w2  = c(112,109,106,117,129,97),
  w4  = c(77,66,73,66,73,81),
  w8  = c(58,79,58,45,68,47),
  w16 = c(47,57,61,45,65,57)
)

# Calcul des moyennes Tp
tp_mean <- sapply(tp_list, mean)
Tp <- tp_mean

# Calcul du speed-up Sp
T1 <- Tp[1] # Temps pour 1 worker
Sp <- T1 / Tp

# --------------------------
# Graphique 1 : TpS
# --------------------------
plot(
  workers, Tp,
  type = "b",
  pch = 19,
  col = "blue",
  lwd = 2,
  xlab = "Nombre de workers",
  ylab = "Temps d'exécution moyen (Tp)",
  main = "Scalabilité forte – Temps d'exécution (Tp)"
)
grid()

# --------------------------
# Graphique 2 : Speed-up
# --------------------------
plot(
  workers, Sp,
  type = "b",
  pch = 17,
  col = "red",
  lwd = 2,
  xlab = "Nombre de workers",
  ylab = "Speed-up (Sp)",
  main = "Scalabilité forte – Speed-up"
)
grid()

# S_DES implémentation de l'algorithme S-DES (Simplified-Data Encryption Standard)


implémentation de l'algorithme S-DES (Simplified-Data Encryption Standard)

L’algorithme d’encryptage du SDES travaille sur la représentation ASKII du texte. Chaque caractère étant codé par 8 bits, ce sont ces 8 bits qui seront modifiés par l’algorithme de cryptage.
Pour utiliser la méthode SDES, on utilisera également une clé de 10 bits appelée clé initiale ou CI (par exemple : 1011110101). Cette clé servira au cryptage et au décryptage du texte. La méthode de cryptage comprend les méthodes : 
— Une  permutation initiale des bits (IP)  nommée PermutationInitiale().
 — Une fonction complexe appelée FonctionF() qui comprend des permutation, des substitutions et qui utilise la clé de cryptage.
  — Une fonction de permutation EchangeGD() qui échange les 4 premiers bits avec les 4 suivants .
   — Une nouvelle application de fonction fonctionFk().
— Une permutation qui est l’inverse de la permutation initiale (IP −1 ) nommée InverserPermutationInitiale().
La partie complexe de l’algorithme qui utilise la clé de cryptage utilise en fait deux clés de cryptage de 8 bits appelées K1 et K2.
Nous allons maintenant détailler les différentes parties de l’algorithme. 

Générations des clés

Obtention de K1

Dans une première étape, la clé de 10 bits est permutée. Si les 10 bits de la clé sont respectivement désignées par k1,k2,k3,k4,k5,k6,k7,k8,k9 et k10, alors le résultat de la permutation appelée P10 est d´définie de la manière suivante :
P10(k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) = (k3,k5,k2,k7,k4,k10,k1,k9,k8,k6) Par exemple, P10(1010000010) = 1000001100.
Ensuite, on réalise un décalage circulaire à gauche (ou rotation gauche) de 1 bit de manière séparée sur chacun des 5 premiers bits et chacun des 5 derniers bits. Un d´décalage d’un bit à gauche consiste à décaler d’une position vers la gauche chacun des bits.
Le d´décalage `a gauche sur la clé P10 précédemment utilisée permet d’obtenir (00001 11000).
Nous réalisons une dernière permutation P8 qui réalise une permutation sur 8 des 10 bits de la manière suivante :
P8(k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) = (k6,k3,k7,k4,k8,k5,k10,k9)

Obtention de K2

Nous repartons des deux suites de 5 bits obtenues après la première rotation et nous réalisons `a nouveau deux d´décalages gauche supplémentaires sur chacune des deux suites (3 rotations auront été faites en tout donc). Dans l’exemple utilise, les valeurs (00001 11000) deviennent (00100 00011).
On applique une nouvelle fois la permutation P8 pour obtenir la clé K2.
En partant de CI, vous devriez obtenir la clé K2 (01000011).

Cryptage
Nous allons maintenant nous intéresser à l’algorithme de cryptographie

Permutations initiale 
Une permutation dans l’algorithme prend en entrée un caractère correspondant `à 8 bits. La permutation initiale (IP) est la fonction suivante : IP(n1,n2,n3,n4,n5,n6,n7,n8) = (n2,n6,n3,n1,n4,n8,n5,n7)
On donne également :
IP−1(n1,n2,n3,n4,n5,n6,n7,n8) = (n4,n1,n3,n5,n7,n2,n8,n6)
Il est facile de voir que la composition de IP avec IP−1 donne la valeur initiale (IP−1(IP(X)) = X).

La fonction fonctionFk
C’est la partie la plus complexe de l’algorithme SDES, elle consiste en une combinaison de permutations et de substitutions. Le principe général de fonctionFk peut être expliqué de la manière suivante. Soit G les 4 premiers bits de gauche et D les 4 bits de droite en entrée de fonctionFk, et F une fonction associant 4 bits `a 4 autres bits, alors :
fonctionFk(G,D) = (G ⊕ F(D,SK),D) avec SK une sous-clé, et ⊕ est l’operateur ou exclusif ( XOR ). Si à la sortie de la permutation IP, l’octet a la valeur (10111101) et F(1101,SK) = (1100) pour la clé SK, alors fonctionFk(10111101) = (01011101) car (1011) ⊕ (1110) = (0101).

La fonction FonctionF
L’entrée de la fonction F comporte 4 bits (n1, n2, n3, n4). La première opération est une opération d’expansion-permutation permettant d’obtenir un groupe de 8 bits .
(n1,n2,n3,n4) = (n4,n1,n2,n3,n2,n3,n4,n1)
La clé K1 (k1,1,k1,2,k1,3,k1,4,k1,5,k1,6,k1,7,k1,8) est ajoutée par un ou exclusif de la manière suivante :
Les 4 premiers bits sont utilisés comme entrée de la matrice S0 afin de produire 2 bits en sortie. Les 4 bits restant sont utilisés afin de produire une sortie de 2 bits supplémentaires grâce à la matrice S1.
 Le principe de fonctionnement des Sbox est le suivant : le premier et le 4eme bit sont traités comme un nombre entier de 2 bits qui d´désigne la ligne de la Sbox, le 2eme et le 3eme bit d´désignent la colonne de la Sbox; la cellule correspondant est transformée en base 2 afin de produire une sortie sur 2 bits qui sera la sortie de la Sbox.
Prenons un exemple, si on a en entrée 0110 de S0, alors la sortie sera 3 en d´décimal donc (11) en base 2.
On obtient donc en sortie de ces Sbox 4 bits, ces 4 bits subissent une dernière permutation appelée P4 qui est la sortie de la fonction F. P4(n1,n2,n3,n4) = (n2,n4,n3,n1)
Le calcul de fonctionFk se fait ensuite simplement en réalisant un ou exclusif avec les 4 bits les plus `a gauche des 8 bits d’entrée (sortie de IP).

La fonction de permutation EchangeGD
Comme la fonction fonctionFk ne modifie que les 4 bits les plus à gauche de l’entrée. La fonction SW échange les 4 bits les plus à gauche avec les 4 bits les plus à droite et on réalise une deuxième application de la fonction fonctionFk pour ces 4 nouveaux bits.
Dans cette deuxième application de la fonction fonctionFk, tout est identique, sauf que la clé K2 est utilisée.

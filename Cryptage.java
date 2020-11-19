
/** la méthode de cryptage de S-DES comprend les méthodes cités ci-dessous
— Une  permutation initiale des bits (IP)  nommée PermutationInitiale().
 — Une fonction complexe appelée FonctionF() qui comprend des permutation, des substitutions et qui utilise la clé de cryptage.
  — Une fonction de permutation EchangeGD() qui échange les 4 premiers bits avec les 4 suivants .
   — Une nouvelle application de fonctionFk().
— Une permutation qui est l’inverse de la permutation initiale (IP −1 ) nommée InverserPermutationInitiale(). */
class Cryptage
{

  private int[] K1 = new int[8]; // variable qui stocke la sous clé K1
  private int[] K2 = new int[8]; // variable qui stocke la sous clé K1
  private int[] pt = new int[8]; // variable qui stocke le plaintexte
  // la fonction de cryptage prend un message en clair de 8 bits, dans cette méthode on s'assure bien que le le Plaintext est bien en binaire.
  void SaisiePlaintext (String plaintext , int[] k1, int[] k2)
  {
	int[] pt = new int[8]; // declaration de la variable qui stockera le plaintexte on spécifiant une taille de 8bits
	
			
	char c1;
	String ts ;
	
	try
	{
		// parcourir le plaintext caréactère par caractère ( bit par bit)
	for(int i=0;i<8;i++)
    {
		c1 = plaintext.charAt(i);// on recupère caractère  par caractère la chaine du plaintext
       ts = Character.toString(c1); // on l'oblige à être un carctère (the casting opartor)
       pt[i] = Integer.parseInt(ts);// on converti le caractère en entier puis on le met dans notre tabelau 
	   
	   /* TESTONS SI NOTRE i EST UN BINAIRE(1 ou 0) */   
	   if(pt[i] !=0 && pt[i]!=1)
	   {
		 System.out.print("\n .. Votre texte doit être en binaire ..");
		System.exit(0);
		return ;
	   }
    }
	}
	catch(Exception e)
	{
	// erreur exception
		 System.out.print("\n .. Exception Plaintext non valide .. ");
		System.exit(0);
		return ;
		
	}
	
    this.pt = pt;
    // affichage du plaintexte
      System.out.print("votre Plaintext est: ===========================>");
     Affichage.tableau(this.pt,8);
	  System.out.print("\n");
	
    this.K1 = k1;
    this.K2 = k2;
    
    
  }
  
  /** La permutation initiale (IP ) est la fonction suivante IP(1,2,3,4,5,6,7,8,9,10)= [2 6 3 1 4 8 5 7] **/
  void PermutationInitiale ()
  {
    int[] temp = new int[8]; //  variable temporaire
    // permutation grace à l'accès aux indices du tableau
    temp[0] = pt[1]; 
    temp[1] = pt[5];
    temp[2] = pt[2];
    temp[3] = pt[0];
    temp[4] = pt[3];
    temp[5] = pt[7];
    temp[6] = pt[4];
    temp[7] = pt[6];
    
    pt = temp;
	// affichage de notre plaintexte après la permutation initiale
	  System.out.print("Permutaion initiale(IP) : ======================>");
     Affichage.tableau(this.pt,8);
	  System.out.print("\n");
	
  } 
  /* On donne également : (IP −1 ) qui est l’inverse de la permutation initiale*/
  void InverserPermutationInitiale()
  {
    int[] temp = new int[8]; //  variable temporaire
    // permutation grace à l'accès aux indices du tableau
    temp[0] = pt[3];
    temp[1] = pt[0];
    temp[2] = pt[2];
    temp[3] = pt[4];
    temp[4] = pt[6];
    temp[5] = pt[1];
    temp[6] = pt[7];
    temp[7] = pt[5];
    
    pt = temp;
	
	
  }
  
  /** FonctionF . Prend en argument les 4-bit de droite du plaintext et 8-bit d'une sous clé  **/ 
  int[] FonctionF(int[] R, int[] SK)
  {
    int[] temp = new int[8]; //  variable temporaire
    
    // EXPANSION/PERMUTATION [4 1 2 3 2 3 4 1] 
    temp[0]  = R[3];
    temp[1]  = R[0];
    temp[2]  = R[1];
    temp[3]  = R[2];
    temp[4]  = R[1];
    temp[5]  = R[2];
    temp[6]  = R[3];
    temp[7]  = R[0];
    
	  System.out.print("EXPANSION/PERMUTATION sur RH : =================>");
     Affichage.tableau(temp,8);
	  System.out.print("\n");
	 
    // Ou-exclusif de bit par bit avec la sous clé
    temp[0] = temp[0] ^ SK[0];
    temp[1] = temp[1] ^ SK[1];
    temp[2] = temp[2] ^ SK[2];
    temp[3] = temp[3] ^ SK[3];
    temp[4] = temp[4] ^ SK[4];
    temp[5] = temp[5] ^ SK[5];
    temp[6] = temp[6] ^ SK[6];
    temp[7] = temp[7] ^ SK[7];
    
	  System.out.print("Xor avec la clé :===============================>");
     Affichage.tableau(temp,8);
	  System.out.print("\n");
	 
    // S-Boxes
/*	
		1 0 3 2
S0 =	3 2 1 0
		0 2 1 3
		3 1 3 2

 	 	0 1 2 3
S1 =	2 0 1 3
		3 0 1 0
		2 1 0 3
*/
    final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} } ; // initialisation de la S-box S0
    final int[][] S1 = { {0,1,2,3},  {2,0,1,3}, {3,0,1,0}, {2,1,0,3}} ; // initialisation de la S-box S1
    
   
      int d11 = temp[0]; //  premier  bit  de la première moitié du plaintext
      int d14 = temp[3]; // quatrième  bit de la première moitié du plaintext
      
	  int ligne1 = Binaire.ConversionEnDecimale(d11,d14); // pour mettre dans la s-box S0
      
	  
      int d12 = temp[1]; // deuxieme  bit de la première moitié du plaintext
	  int d13 = temp[2]; // troisieme  bit  de la première moitié du plaintext    
	   
      int col1 = Binaire.ConversionEnDecimale(d12,d13); // pour mettre dans la s-box S0
      
	  /* il prendra l'element à la position ligne=ligne1 et colonne=col1 de la matrice S0 et la sortie sera en decimal
	   graçe à la fontion de ConversionEnDecimale implementée dans la Classe Binaire .*/
	  int o1 = S0[ligne1][col1]; 
	  
	// Ensuite nous convertissons ce decimal en binaire   
	  int[] sortie1 = Binaire.ConversionEnBinaire(o1);

	 // Puis on affiche les deux chiffres binaires
	  System.out.print("S-Box S0: ======================================>");
     Affichage.tableau(sortie1,2);
	  System.out.print("\n");
	  
	 //=============================================================================//

	 int d21 = temp[4]; // première  bit  de la deuxième moitié du plaintext
      int d24 = temp[7]; // quatrième  bit  de la deuxième moitié du plaintext
      int ligne2 = Binaire.ConversionEnDecimale(d21,d24);
	  
	  int d22 = temp[5]; // deuxième  bit  de la deuxième moitié du plaintext
	  int d23 = temp[6]; // troisième  bit  de la deuxième moitié du plaintext
	  int col2 = Binaire.ConversionEnDecimale(d22,d23);
	   // il prendra l'element à la position ligne=ligne2 et colonne=col2 de la matrice S1 et la sortie sera un decimal
	  int o2 = S1[ligne2][col2];
	  	 
	  int[] sortie2 = Binaire.ConversionEnBinaire(o2); 

	  System.out.print("S-Box S1: ======================================>");
     Affichage.tableau(sortie2,2);
	  System.out.print("\n");
		
      //4 bits sorties des 2 s-boxes soit 2 bits pour une S-Box
	  
	  int[] sortie = new int[4]; // variable qui stocke la sortie  des S-box
	  sortie[0] = sortie1[0]; // stocke le bit de le S-Box 0
      sortie[1] = sortie1[1]; // stocke bit de le S-Box 0
	  sortie[2] = sortie2[0]; // stocke bit de le S-Box 1
	  sortie[3] = sortie2[1]; // stocke bit de le S-Box 1
	  
	 /**ces 4 bits subissent une
		dernière permutation appelée P4 qui est la sortie de la fonction FonctionF.
		P4(n1, n2, n3, n4) = (n2, n4, n3, n1)
 		*/
	  
	  int [] sortieTemp = new int[4]; // variable temportaire
	  sortieTemp[0] = sortie[1];
	  sortieTemp[1] = sortie[3];
	  sortieTemp[2] = sortie[2];
      sortieTemp[3] = sortie[0];
	  
      System.out.print("ce que contient FonctionF est : ================>");
     Affichage.tableau(sortieTemp,4);
	  System.out.print("\n");  
	 
	 return sortieTemp;
  }
  
  /** fK(L, R, SK) = (L (XOR) FonctionF(R, SK), R) .. returne 8-bit **/
  int[] fonctionFk(int[] L, int[] R,int[] SK)
  {	
	int[] temp = new int[4];
	int[] sortie = new int[8];
	
	
	temp = FonctionF(R,SK);
	
	
	//XOR de la partie gauche avec le resultat de FonctionF 
	sortie[0] = L[0] ^ temp[0];
	sortie[1] = L[1] ^ temp[1];
	sortie[2] = L[2] ^ temp[2];
	sortie[3] = L[3] ^ temp[3];
	
	// recupération des 4 bits de droite en entrée de la fonction fonctionFk
	sortie[4] = R[0]; 
	sortie[5] = R[1];
	sortie[6] = R[2];
	sortie[7] = R[3];
	
	
	return sortie;
	
	
  }
  
  /*
  	La fonction EchangeGD échange les 4 bits les plus à gauche avec les 4 bits les plus à droite  */ 
int[] EchangeGD(int[] in)
  {
	
	int[] temp = new int[8]; // variable temporaire

	// échange des 4 bits à droite vers la gauche
	temp[0] = in[4];
	temp[1] = in[5];
	temp[2] = in[6];
	temp[3] = in[7];
  
  	// échange des 4 bits à gauche  vers la droite

    temp[4] = in[0];
	temp[5] = in[1];
	temp[6] = in[2];
	temp[7] = in[3];	
	
	return temp;
  }
/* ============================================================= */
//				LA FONCTION DE CRYPTAGE
/* ============================================================= */


  int[] chiffrement(String plaintext , int[] LK, int[] RK)
  {
	
		
	SaisiePlaintext (plaintext,LK,RK);
	
	 System.out.print("\n*********************************************************************\n");
	PermutationInitiale ();
	 System.out.print("\n*********************************************************************\n");
	// diviser le plaintext en 2 donc 4 bits de gauche et 4 autres de droite
	int[] LH = new int[4];
	int[] RH = new int[4];
	// Gauche
	LH[0] = pt[0];
	LH[1] = pt[1];
	LH[2] = pt[2];
	LH[3] = pt[3];
	
	// Droite
	RH[0] = pt[4];
	RH[1] = pt[5];
	RH[2] = pt[6];
	RH[3] = pt[7];
	
	
	  System.out.print("premier Round LH: ==============================>");
     Affichage.tableau(LH,4);
	  System.out.print("\n");
	 
	  System.out.print("premier Round RH: ==============================>");
     Affichage.tableau(RH,4);
	  System.out.print("\n");
	 
	// premier Round avec  K1
	int[] r1 = new int[8];
	r1 = fonctionFk(LH,RH,K1);
	
	  System.out.print("Après le premier Round avec K1: ================>");
     Affichage.tableau(r1,8);
	  System.out.print("\n");
	 System.out.print("\n*********************************************************************\n");
	// Echange la moitié gauche et la moitié droite du plaintext
	int[] temp = new int[8];
	temp = EchangeGD(r1);
	
	  System.out.print("Après la fonction Echange: =====================>");
     Affichage.tableau(temp,8);
	  System.out.print("\n");
	  System.out.print("\n*********************************************************************\n");
	// diviser encore le plaintext en 2 donc 4 bits de gauche et 4 autres de droite pour le deuxieme Round
	LH[0] = temp[0];
	LH[1] = temp[1];
	LH[2] = temp[2];
	LH[3] = temp[3];
	
	RH[0] = temp[4];
	RH[1] = temp[5];
	RH[2] = temp[6];
	RH[3] = temp[7];

	
	  System.out.print("Deuxième Round LH: =============================>");
     Affichage.tableau(LH,4);
	  System.out.print("\n");
	 
	  System.out.print("Deuxième Round RH: =============================>");
     Affichage.tableau(RH,4);
	  System.out.print("\n");
	 
	 
	// Deuxième Round avec K2
	int[] r2 = new int[8];
	r2 = fonctionFk(LH,RH,K2);
	
	pt = r2;
	
	  System.out.print("Après deuxième  Round: =========================>");
     Affichage.tableau(this.pt,8);
	  System.out.print("\n");
	  System.out.print("\n*********************************************************************\n");
	 
	InverserPermutationInitiale();
	
	  System.out.print("Après l'inverse IP (Result): ===================>");
     Affichage.tableau(this.pt,8);
	  System.out.print("\n");
	 
	//Le chiffrement est fini... retourner 8-bit  .
	return pt; 	// retourner le plaintext

	
	
	
	
  }
 
}




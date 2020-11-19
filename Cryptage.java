
class Cryptage
{

  private int[] K1 = new int[8];
  private int[] K2 = new int[8];
  private int[] pt = new int[8];
  //
  void SaisiePlaintext (String plaintext , int[] k1, int[] k2)
  {
	int[] pt = new int[8];
	
			
	char c1;
	String ts ;
	
	try
	{
	for(int i=0;i<8;i++)
    {
		c1 = plaintext.charAt(i);// on reccupère le premier caractère de la chaine plaintext
       ts = Character.toString(c1); // on l'oblige à être un carctère (the casting opartor)
       pt[i] = Integer.parseInt(ts);// on converti le caractère en entier puis on le met dans notre tabelau key[i]
	   
	   /* TESTONS SI NOTRE i EST UN BINAIRE(1 ou 0) */   
	   if(pt[i] !=0 && pt[i]!=1)
	   {
		Affichage.message("\n .. Votre texte doit être en binaire ..");
		System.exit(0);
		return ;
	   }
    }
	}
	catch(Exception e)
	{
		Affichage.message("\n .. Exception Plaintext non valide .. ");
		System.exit(0);
		return ;
		
	}
	
    this.pt = pt;
    
     Affichage.message("votre Plaintext est: ===========================>");
     Affichage.tableau(this.pt,8);
	 Affichage.message("\n");
	
    this.K1 = k1;
    this.K2 = k2;
    
    
  }
  
  /** La permutation initiale (IP ) est la fonction suivante IP(1,2,3,4,5,6,7,8,9,10)= [2 6 3 1 4 8 5 7] **/
  void PermutationInitiale ()
  {
    int[] temp = new int[8];
    
    temp[0] = pt[1];
    temp[1] = pt[5];
    temp[2] = pt[2];
    temp[3] = pt[0];
    temp[4] = pt[3];
    temp[5] = pt[7];
    temp[6] = pt[4];
    temp[7] = pt[6];
    
    pt = temp;
	
	 Affichage.message("Permutaion initiale(IP) : ======================>");
     Affichage.tableau(this.pt,8);
	 Affichage.message("\n");
	
  } 
  /* On donne également : (IP −1 )*/
  void InverserPermutationInitiale()
  {
    int[] temp = new int[8];
    
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
    int[] temp = new int[8];
    
    // EXPANSION/PERMUTATION [4 1 2 3 2 3 4 1] 
    temp[0]  = R[3];
    temp[1]  = R[0];
    temp[2]  = R[1];
    temp[3]  = R[2];
    temp[4]  = R[1];
    temp[5]  = R[2];
    temp[6]  = R[3];
    temp[7]  = R[0];
    
	 Affichage.message("EXPANSION/PERMUTATION sur RH : =================>");
     Affichage.tableau(temp,8);
	 Affichage.message("\n");
	 
    // Ou-exclusif de bit par bit avec la sous clé
    temp[0] = temp[0] ^ SK[0];
    temp[1] = temp[1] ^ SK[1];
    temp[2] = temp[2] ^ SK[2];
    temp[3] = temp[3] ^ SK[3];
    temp[4] = temp[4] ^ SK[4];
    temp[5] = temp[5] ^ SK[5];
    temp[6] = temp[6] ^ SK[6];
    temp[7] = temp[7] ^ SK[7];
    
	 Affichage.message("Xor avec la clé :===============================>");
     Affichage.tableau(temp,8);
	 Affichage.message("\n");
	 
    // S-Boxes
    final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} } ;
    final int[][] S1 = { {0,1,2,3},  {2,0,1,3}, {3,0,1,0}, {2,1,0,3}} ;
    
   
      int d11 = temp[0]; //  premier  bit  de la première moitié du plaintext
      int d14 = temp[3]; // quatrième  bit de la première moitié du plaintext
      
	  int row1 = Binaire.ConversionEnDecimale(d11,d14); // pour mettre dans la s-box S0
      
	  
      int d12 = temp[1]; // deuxieme  bit de la première moitié du plaintext
	  int d13 = temp[2]; // troisieme  bit  de la première moitié du plaintext    
	   
      int col1 = Binaire.ConversionEnDecimale(d12,d13); // pour mettre dans la s-box S0
      
	  // il prendra l'element à la position ligne=row1 et colonne=col1 de la matrice S0 et la sortie sera un decimal
	  int o1 = S0[row1][col1]; 
	  
	// Ensuite nous convertissons ce decimal en binaire   
	  int[] sortie1 = Binaire.ConversionEnBinaire(o1);

	 // Puis on affiche les deux chiffres binaires
	 Affichage.message("S-Box S0: ======================================>");
     Affichage.tableau(sortie1,2);
	 Affichage.message("\n");
	  
	 //=============================================================================//

	 int d21 = temp[4]; // première  bit  de la deuxième moitié du plaintext
      int d24 = temp[7]; // quatrième  bit  de la deuxième moitié du plaintext
      int row2 = Binaire.ConversionEnDecimale(d21,d24);
	  
	  int d22 = temp[5]; // deuxième  bit  de la deuxième moitié du plaintext
	  int d23 = temp[6]; // troisième  bit  de la deuxième moitié du plaintext
	  int col2 = Binaire.ConversionEnDecimale(d22,d23);
	   // il prendra l'element à la position ligne=row2 et colonne=col2 de la matrice S1 et la sortie sera un decimal
	  int o2 = S1[row2][col2];
	  	 
	  int[] sortie2 = Binaire.ConversionEnBinaire(o2); 

	 Affichage.message("S-Box S1: ======================================>");
     Affichage.tableau(sortie2,2);
	 Affichage.message("\n");
	//0110	
      //4 sortieput bits from 2 s-boxes
	  int[] sortie = new int[4];
	  sortie[0] = sortie1[0];
      sortie[1] = sortie1[1];
	  sortie[2] = sortie2[0];
	  sortie[3] = sortie2[1];
	  
	  //permutation P4 [2 4 3 1]
	  
	  int [] sortieTemp = new int[4];
	  sortieTemp[0] = sortie[1];
	  sortieTemp[1] = sortie[3];
	  sortieTemp[2] = sortie[2];
      sortieTemp[3] = sortie[0];
	  
     Affichage.message("ce que contient FonctionF est : ================>");
     Affichage.tableau(sortieTemp,4);
	 Affichage.message("\n");  
	 
	 return sortieTemp;
  }
  
  /** fK(L, R, SK) = (L (XOR) FonctionF(R, SK), R) .. returne 8-bit **/
  int[] fonctionFk(int[] L, int[] R,int[] SK)
  {	
	int[] temp = new int[4];
	int[] sortie = new int[8];
	
	
	temp = FonctionF(R,SK);
	
	
	//XOR de la partie droite avec le resultat de FonctionF 
	sortie[0] = L[0] ^ temp[0];
	sortie[1] = L[1] ^ temp[1];
	sortie[2] = L[2] ^ temp[2];
	sortie[3] = L[3] ^ temp[3];
	
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
	
	int[] temp = new int[8];
	
	temp[0] = in[4];
	temp[1] = in[5];
	temp[2] = in[6];
	temp[3] = in[7];
  
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
	
	Affichage.message("\n*********************************************************************\n");
	PermutationInitiale ();
	Affichage.message("\n*********************************************************************\n");
	// Partager le plaintext en 2 donc 4 bits de gauche et 4 autres de droite
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
	
	
	 Affichage.message("premier Round LH: ==============================>");
     Affichage.tableau(LH,4);
	 Affichage.message("\n");
	 
	 Affichage.message("premier Round RH: ==============================>");
     Affichage.tableau(RH,4);
	 Affichage.message("\n");
	 
	// premier Round avec  K1
	int[] r1 = new int[8];
	r1 = fonctionFk(LH,RH,K1);
	
	 Affichage.message("Après le premier Round avec K1: ================>");
     Affichage.tableau(r1,8);
	 Affichage.message("\n");
	Affichage.message("\n*********************************************************************\n");
	// Echange la moitié gauche et la moitié droite du plaintext
	int[] temp = new int[8];
	temp = EchangeGD(r1);
	
	 Affichage.message("Après la fonction Echange: =====================>");
     Affichage.tableau(temp,8);
	 Affichage.message("\n");
	 Affichage.message("\n*********************************************************************\n");
	// Partager encore le plaintext en 2 donc 4 bits de gauche et 4 autres de droite pour le deuxieme Round
	LH[0] = temp[0];
	LH[1] = temp[1];
	LH[2] = temp[2];
	LH[3] = temp[3];
	
	RH[0] = temp[4];
	RH[1] = temp[5];
	RH[2] = temp[6];
	RH[3] = temp[7];

	
	 Affichage.message("Deuxième Round LH: =============================>");
     Affichage.tableau(LH,4);
	 Affichage.message("\n");
	 
	 Affichage.message("Deuxième Round RH: =============================>");
     Affichage.tableau(RH,4);
	 Affichage.message("\n");
	 
	 
	// Deuxième Round avec K2
	int[] r2 = new int[8];
	r2 = fonctionFk(LH,RH,K2);
	
	pt = r2;
	
	 Affichage.message("Après deuxième  Round: =========================>");
     Affichage.tableau(this.pt,8);
	 Affichage.message("\n");
	 Affichage.message("\n*********************************************************************\n");
	 
	InverserPermutationInitiale();
	
	 Affichage.message("Après l'inverse IP (Result): ===================>");
     Affichage.tableau(this.pt,8);
	 Affichage.message("\n");
	 
	//Le cryptage est fini... retourne 8-bit  .
	return pt;
	
	
	
	
  }
 
}




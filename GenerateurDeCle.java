/**

SDES Algorithme
Tinehinene BELHOCINE
Ousmane SOW
 
*/



class GenerateurDeCle
{/** declaration des variables en même temps on leurs donne la taille correspondant à notre besoin */
  private int[] cle = new int[10]; // clé de taille 10 bits
  private int[] k1 = new int[8]; // sous-clé K1 de taille 8 bits
  private int[] k2 = new int[8]; // sous-clé K2 de taille 8 bits
  private boolean drapeau = false;
  /* constructeur qui ne prend rien et ne renvoie rien */
  GenerateurDeCle()
  {
    
  }

  /** permutation P10 sur la clé de  10 bits  
   application de la methdoe permutationP10(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10) = (k3, k5, k2, k7, k4, k10, k1, k9, k8, k6)
  **/
   
  private void permutationP10()
  {
    int[] temp = new int[10]; // variable temporaire
    // permutation
    temp[0] = cle[2];
    temp[1] = cle[4];
    temp[2] = cle[1];
    temp[3] = cle[6];
    temp[4] = cle[3];
    temp[5] = cle[9];
    temp[6] = cle[0];
    temp[7] = cle[8];
    temp[8] = cle[7];
    temp[9] = cle[5];
    
    
    cle = temp;
        
  }
  
  /** on réalise un décalage circulaire à gauche (ou rotation gauche) de 1 bit de manière séparée sur
   *  chacun des 5 premiers bits et chacun des 5 derniers bits(LS-1). **/

  private void DecalageGauche1()
  {
    int[] temp = new int[10]; // variable temporaire
     // permutation
    temp[0] = cle[1];
    temp[1] = cle[2];
    temp[2] = cle[3];
    temp[3] = cle[4];
    temp[4] = cle[0];
    
    temp[5] = cle[6];
    temp[6] = cle[7];
    temp[7] = cle[8];
    temp[8] = cle[9];
    temp[9] = cle[5];
    
    cle = temp;
    
  }
  
  /** Nous réalisons une dernière permutation P 8 qui réalise une permutation sur 8 des 10 bits de 
   * la manière suivante  P 8(k1,k2,k3,k4,k5,k6,k7,k8,k9,k10 ) = (k6,k3,k7,k4,k8,k5,k10,k9 ) **/
  private int[] permutationP8()
  {
    int[] temp = new int[8]; // variable temporaire
    // permutation
  
    temp[0] = cle[5];
    temp[1] = cle[2];
    temp[2] = cle[6];
    temp[3] = cle[3];
    temp[4] = cle[7];
    temp[5] = cle[4];
    temp[6] = cle[9];
    temp[7] = cle[8];
    
    return temp;
        
  }
  
  /** on réalise deux décalages circulaires à gauche (ou rotation gauche) de 1 bit de manière séparée sur
   *  chacun des 5 premiers bits(resutltant du premier decalage) et chacun des 5 derniers bits(LS-2). **/
  private void DecalageGauche2()
  {
    int[] temp = new int[10]; // variable temporaire
  // permutation
    
    temp[0] = cle[2];
    temp[1] = cle[3];
    temp[2] = cle[4];
    temp[3] = cle[0];
    temp[4] = cle[1];
    
    temp[5] = cle[7];
    temp[6] = cle[8];
    temp[7] = cle[9];
    temp[8] = cle[5];
    temp[9] = cle[6];
    
    cle = temp;
    
  }
  /* constructeur qui prend en argument une clé et ne renvoie rien c*/
  void GenererCle (String cleInti )
  {
    
	/* DECLARATIONS DES VARIABLES LOCALES*/
  int[] cle = new int[10];// un tableau de 10 bits 
	char c1; // une variable pour le parcours de notre clé prisse comme entrée
	String ts ; //
	/* parcourons notre tableau de clé appelé cleInti */
	try
	{
	for(int i=0;i<10;i++)
    {
       c1 = cleInti.charAt(i);// on recupère le premier caractère de la chaine cleInti
       ts = Character.toString(c1); // on l'oblige à être un caractère (the casting opartor)
       cle[i] = Integer.parseInt(ts);// on converti le caractère en entier puis on le met dans notre tabelau cle[i]
	   /* TESTONS SI NOTRE i EST UN BINAIRE(1 ou 0) */
      if(cle[i] !=0 && cle[i]!=1)
      {
           System.out.print("\n .. votre clé doit être en binaire ..");
          System.exit(0);
          return ;/* s'il n'est pas binaire on quitte le programme */
      } //sortie du if
    } // sortie de la boucle for
	} // sortie du try avec une clé 
	catch(Exception e)
	{
		 System.out.print("\n .. Exception  clé non valide .. ");
		System.exit(0);
		return ;
		
  }
  // affectation de la nouvelle clé à notre variable clé
    this.cle = cle;

    // affichage du tableau de la clé de 10 bits
       System.out.print("Votre tableau est ==============================>");
      Affichage.tableau(this.cle,10);
       System.out.print("\n");

    // premiere permutation avec la méthode permutationP10()
      permutationP10();

    // affichage du tableau après permutation
       System.out.print("Tableau après permutation10 :===================>");
      Affichage.tableau(this.cle,10);
       System.out.print("\n");
    // Décalage à gauche  avec la méthode DecalageGauche1()
      DecalageGauche1();
    
       System.out.print("Après décalage à gauche de la clé LS-1 : =======>");
      Affichage.tableau(this.cle,10);
       System.out.print("\n");
    
    /* Deuxieme permutation avec la fonction permutationP8(), generation de la première clé et affichage */
      this.k1 = permutationP8(); // appliquer la méthode permutationP8() à la clé sous clé K1

      System.out.print("Votre première clé k1===========================>");
     Affichage.tableau(this.k1,8);
      System.out.print("\n");
    // Réalise 2 décalage à gauche  avec la méthode DecalageGauche2()
      DecalageGauche2();
  
      System.out.print("Après deux décalage à gauche de la clé LS-2 : ==>");
     Affichage.tableau(this.cle,10);
	    System.out.print("\n");

    /* troisième permutation avec permutationP8(), generation de la deuxième clé et affichage */
     this.k2 = permutationP8(); // appliquer la méthode permutationP8() à la clé sous clé K2
      System.out.print("Votre deuxième clé K2===========================>");
     Affichage.tableau(this.k2,8);
      System.out.print("\n"); 
    // Notre drapeau devient vrai car sinon on serai déjà sorti de la boucle et du test if ou dans try-catch
    drapeau = true;

}
  

/* une methode  public qui renvoie K1 et ne prend rien en argument 
*  test si les sous clés  K1 et K2 sont bien generées sinon elle affiche un message d'erreur 
*/
public int[] getK1()
{
  if(!drapeau)
    {
       System.out.print("\n La sous clé K1 n'est pas generée ");
      return null;
    }
    return k1; // retourner la sous clé k1
}

/* une methode qui public qui renvoie la sous clé K2 et ne prend rien en argument 
*  test si les sous clés K1 et K2 sont bien generées sinon elle affiche un message d'erreur 
*/
public int[] getK2()
{
  if(!drapeau)
    {
       System.out.print("\n La sous clé K2 n'est pas generée  ");
      return null;
    }
    return k2; // retourner la sous clé k2
}  

}


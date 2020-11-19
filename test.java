
import java.util.*;
public class test {
    

  public static void main(String[] args)
  {
    // instanciation de la classe GenerateurDeCle
    GenerateurDeCle KG = new GenerateurDeCle();

	// instanciation de la classe Cryptage
    Cryptage enc  = new Cryptage();

	// Donner à saisir à l'utilisateur
    Scanner sc = new Scanner(System.in);
    
	String pt ;
	String cle;
	int[] ct = new int[8];
    
    try
    {

	
	
	System.out.print("Entrez  le texte à chiffrer de 8 bits  : ");
	// Donner à l'utilisateur de saisir le texte en clair de 8 bits
	pt = sc.next();
	
	
	System.out.println(" \n ");
				
    System.out.print("Entrez une clé de 10 bits  : ");
	// Donner à l'utilisateur de saisir la clé de 10 bits
    cle = sc.next();
    
	
	System.out.println(" \n ");
	System.out.println("\n\t \t \t   CHIFFREMENT \n");
	 System.out.print("\t \t \t   Generation  de la clé \n \n");
	KG.GenererCle(cle);
	 System.out.print("\n*********************************************************************\n");
	ct = enc.chiffrement( pt ,KG.getK1(),KG.getK2());
	
	 System.out.print("\n*********************************************************************\n");
	System.out.println("\n\t \t \t   DECHIFFREMENT \n\n");
	

	System.out.print("Entrez le texte chiffré à déchiffrer de 8 bits  : ");
	// Donner à l'utilisateur de saisir le texte chiffré à dechifferer CIPHERTEXT 8bits
	pt = sc.next();
	
	
	System.out.println(" \n ");
	

    System.out.print("Entrez une clé de 10 bits : ");
	// Donner à l'utilisateur de saisir la clé de 10 bits
    cle = sc.next();
    
	
	System.out.println(" \n ");
	
	 System.out.print("\t \t \t Generation  de la clé \n\n");
	 // l'appel de la méthode GenererCle() implementé dans la classe GenerateurDeCle
	KG.GenererCle(cle);
	 System.out.print("\n*********************************************************************\n");
	// l'appel de la méthode chiffrement() implementé dans la classe Cryptage
	ct = enc.chiffrement( pt ,KG.getK2(),KG.getK1());
	
	 System.out.print("\n*********************************************************************\n");
	
	 System.out.print("\n******************************FIN************************************\n");

	 System.out.print("\n*********************************************************************\n");
	
	
	  
	
	
    }
    catch(InputMismatchException e)
    {
	// erreur exception
      System.out.println(" Exception ");
    }
    catch(Exception e)
    {
	// erreur exception
      System.out.println(" Exception "+e);
    }
    
  }
  

 
}

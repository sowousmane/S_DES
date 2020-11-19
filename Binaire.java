
class Binaire
{
  /** Cette methode prend en paramettre des chiffres binaires d'une taille d'un octet donc 8 bits et 
   * renvoie le nombre decimal correspondant **/ 
  static int ConversionEnDecimale(int...bits)
  {
     int temp=0;
     int base = 1;
     int i;
     
     for( i=bits.length-1 ; i>=0;i--)
     {
        temp = temp + (bits[i]*base);
        base = base * 2 ;
     }
      
      return temp;
  }
  
  /** Cette methode prend en parametre un nombre decimal  et 
   * renvoie le codage binaire correspondant au chiffre  **/ 
  static int[] ConversionEnBinaire(int nb)
  {
         int i;
         int j;

      if(nb==0)
      {
        int[] zero = new int[2];
        zero[0] = 0;
        zero[1] = 0;
        return zero;	
      }
        int[] temp = new int[10] ;
      
        
      int compteur = 0 ;
        for(i= 0 ; nb!= 0 ; i++)
        {
          temp[i] = nb % 2;
          nb = nb/2;
        compteur++;
        }
        
      
      int[] temp2 = new int[compteur];
      
      
      for(i=compteur-1, j=0;i>=0 && j<compteur;i--,j++)
      {
        temp2[j] = temp[i];
      }
      
      /*  Obliger à renvoyer 2 bits pour la sortie la S Box */
        if(compteur<2)
      {
        temp = new int[2];
        temp[0] = 0;
        temp[1] = temp2[0];
        return temp;
      }
      
      return temp2;
  }
}




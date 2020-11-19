
class Binaire
{
  /** Cette methode prend en paramettre des chiffres binaires d'une taille d'un octet donc 8 bits et 
   * renvoie le nombre decimal correspondant **/ 
  static int ConversionEnDecimale(int...bits)
  {
  
         
     int temp=0;
     int base = 1;
     for(int i=bits.length-1 ; i>=0;i--)
     {
        temp = temp + (bits[i]*base);
        base = base * 2 ;
     }
      
      return temp;
  }
  
  /** Cette methode prend en paramettre un nombre decimal  et 
   * renvoie le codage binaire correspondant au chiffre  **/ 
  static int[] ConversionEnBinaire(int no)
  {
    
      if(no==0)
      {
        int[] zero = new int[2];
        zero[0] = 0;
        zero[1] = 0;
        return zero;	
      }
        int[] temp = new int[10] ;
      
        
      int compteur = 0 ;
        for(int i= 0 ; no!= 0 ; i++)
        {
          temp[i] = no % 2;
          no = no/2;
        compteur++;
        }
        
      
      int[] temp2 = new int[compteur];
      
      
      for(int i=compteur-1, j=0;i>=0 && j<compteur;i--,j++)
      {
        temp2[j] = temp[i];
      }
      
      /*  Obliger à renvoyer 2 bits */
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




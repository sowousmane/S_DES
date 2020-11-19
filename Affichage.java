
// cette classe nous permet d'effectuer l'affichage du tableau on le met dans classe pour eviter
// la repetition de la methode dans toute les classe où on a besoin de la methode tableau

class Affichage
{
  
  static void tableau (int[] arr,int len)
  {
    System.out.print("   ");
   
    for(int i=0;i<len;i++)
    {
      System.out.print(arr[i] + " ");
    }
  }
  
}


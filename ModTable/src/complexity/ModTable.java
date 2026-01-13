ypackage complexity;
public class ModTable
{
   // properties
   private long time;
   
   // methods
   public static void main(String[] argv)
   {
      int size;
      
      try
      {
        size=Integer.parseInt(argv[0]);
        ModTable table = new ModTable(size);
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
        System.out.println("usage: ModTable size");
      }
        
   }
   
   public ModTable(int size)
   {
      int row,
          col,
          value;
      
      System.gc();
      time = System.currentTimeMillis();
      
      for(row=0; row<size; row++)
      {
         for(col=0; col<size; col++)
         {
            value = (row+1)%(col+1);
         }
      }
      System.out.println(System.currentTimeMillis() - time);
   }
}
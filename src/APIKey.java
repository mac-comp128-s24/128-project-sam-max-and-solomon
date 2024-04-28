import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class APIKey {
   public static void APIKey(String[] args) throws Exception {
        //Creating the File object
      File file = new File("Key.txt");
      try{
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
         String i = sc.nextLine();
         System.out.println(i);
         }
         sc.close();
      }
      catch (FileNotFoundException e) {
         e.printStackTrace();
      }
        
      //Creating a Scanner object
      Scanner sc = new Scanner(file);
      //StringBuffer to store the contents
      StringBuffer sb = new StringBuffer();
      //Appending each line to the buffer
      while(sc.hasNext()) {
         sb.append(" "+sc.nextLine());
      }
      System.out.println(sb);
   }
}


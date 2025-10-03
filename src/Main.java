import java.util.*;
import java.io.*;
public class Main
{
    public static void main (String [] args)
    {
        try(
                Scanner in = new Scanner (new File("C:\\Users\\Moustafa Abdulhammed\\Desktop\\in.txt"));
            PrintWriter write = new PrintWriter("C:\\Users\\Moustafa Abdulhammed\\Desktop\\out.txt")
         )
        {
            while(in.hasNext())
            {
                String str = in.next();
                for(int i = 0 ; i < 10; i++)
                {
                    write.println(str);
                }
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }



    }
}
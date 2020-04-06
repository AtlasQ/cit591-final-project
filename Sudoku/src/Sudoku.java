/**
 * Sudoku
 */
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
public class Sudoku {
    
   /**
    * Game StartTime
    * @return long value
    */
   public long startTime() {
       long startTime = System.currentTimeMillis();
       return startTime;
   }
   /**
    * Game Timer : End Time minus Start Time
    * @param time
    * @return long value 
    */
   public long Timer(long time) {
       long Timer = System.currentTimeMillis()- time;
       return Timer;

       
   }
   /**
    * Convert timer (long value) to Seconds
    * @param Unit
    * @param start
    * @return seconds passed
    */
   public long time(TimeUnit Unit, long start) {
       return Unit.convert(Timer(start), TimeUnit.MILLISECONDS);
   }
   public static void main (String[] args) {
       
      Sudoku J = new Sudoku();
      long K = J.startTime();
      
      System.out.println(J.startTime());
     
     System.out.println(J.time((TimeUnit.SECONDS),K));
   }
}
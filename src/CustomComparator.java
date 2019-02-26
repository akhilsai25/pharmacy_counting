
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class CustomComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		 Double d1=  (Double) ((Map.Entry) o1).getValue();
		 Double d2= (Double) ((Map.Entry) o2).getValue();
		 
		 //Comparing the prices
	      if(d1>d2)
	    	  return -1;
	      
	      else if(d1<d2)
	    	  return 1;
	      
	      //Comparing the names in case the prices are equal
	      else
	      {
	    	  String sa=(String)  ((Map.Entry) o1).getKey();
	 		 String sb=(String)  ((Map.Entry) o2).getKey();
	 		 return sa.compareTo(sb);
	      }
	}
}

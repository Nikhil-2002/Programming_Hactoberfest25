import java.util.*;

class Activity{
	int a,b;
	Activity(int start,int end){
		a=start;
		b=end;
	}
}
public class ActivitySelection {

	public static void main(String[] args) {
		int[] start = {1, 3, 0, 5, 8, 5};
		int[] end =   {2, 4, 6, 7, 9, 9};
		
		System.out.println(solution(start,end));

	}

	private static int solution(int[] start, int[] end) {
		int n = start.length;
		int output = 1;
		
		List<Activity> activityList = new ArrayList<Activity>();
		
		for(int i = 0; i<n;i++) {
			Activity activity = new Activity(start[i], end[i]);
			activityList.add(activity);
		}
		
		activityList.sort(Comparator.comparingInt(a->a.b));
		
		Activity initial = activityList.get(0);
		
		for(int i=1;i<n;i++) {
			if(initial.a<activityList.get(i).a) {
				output++;
				initial = activityList.get(i);
				
			}
				
				
		}
			
		return output;
		
	}

}

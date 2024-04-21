package Sample;

public class decrease {
	public static void MinDecrease(int[] min, int time) {
		for(int i = 0; i < min.length; i++) {
			if(min[i] == time) {
				min[i] += 1;
				break;
			}
		}
		
	}
	
	public static void MaxDecrease(int[] max, int time) {
		for(int i = 0; i < max.length; i++) {
			if(max[i] == time) {
				max[i] -= 1;
				break;
			}
		}
		
	}

}

package Sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//未完成

public class Data {// 上がりが早い人のシフトを優先して削る

	private int number;// 登録番号
	private int time;// 上がりの時間

	public Data(int number, int time) {
		this.number = number;
		this.time = time;
	}

	public int getTime() {
		return this.time;
	}

	public int getNumber() {
		return this.number;
	}

	public void MaxDecrease(int[] min, int[] max, int time) {// jは時間
		List<Data> aaa = new ArrayList<>();
		for (int i = 0; i < max.length; i++) {
			if (min[i] <= time && time <= max[i]) {
				aaa.add(new Data(i, max[i]));
			}
         
		}
		Collections.sort(aaa, new numberComparator());
		aaa.set(0, new Data(number, time));
		max[number] = time;
	}
	
	public void MinDecrease(int[] min, int[] max, int time) {
		List<Data> aaa = new ArrayList<>();
		for (int i = 0; i < max.length; i++) {
			if (min[i] <= time && time <= max[i]) {
				aaa.add(new Data(i, min[i]));
			}
}
		Collections.sort(aaa, new numberComparator());
		aaa.set(0, new Data(number, time + 1));
		min[number] = time + 1;

	}

}

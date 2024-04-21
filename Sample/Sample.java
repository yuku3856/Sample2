package Sample;

public class Sample {
	public static void main(String[] args) {
		// 8～20
		String[] time = { "8～20", "18～20", null, "11～15", "14～21", "10～18", "11～16", "17～20", "10～20"};
//１１～１４　二人　～１７まで一人　～２０まで二人
		String[][] shift = { { "11～14", "5" }, { "14～17", "4" }, { "17～21", "2" } };
//一時間単位	

//nullデータに例外データを格納
		for (int i = 0; i < time.length; i++) {
			if (time[i] == null) {
				time[i] = "-1～-1";
			}
		}

		int[] min = new int[time.length];
		int[] finalMax = new int[time.length];
		int[] max = new int[time.length];

//シフトをminとmaxに格納
		for (int i = 0; i < time.length; i++) {
			String[] data = time[i].split("～");
			min[i] = Integer.parseInt(data[0]);
			max[i] = Integer.parseInt(data[1]);
			finalMax[i] = Integer.parseInt(data[1]);
		}
		// 理想人数を格納
		int[] shiftMin = new int[shift.length];
		int[] shiftMax = new int[shift.length];
		for (int i = 0; i < shift.length; i++) {
			String[] data = shift[i][0].split("～");
			shiftMin[i] = Integer.parseInt(data[0]);
			shiftMax[i] = Integer.parseInt(data[1]);
		}

//シフト作成
		for (int k = 0; k < shiftMin.length; k++) {
			for (int j = shiftMin[k]; j < shiftMax[k]; j++) {// 理想シフトの時間当たりの人数
				int count = 0;
				for (int i = 0; i < min.length; i++) {
					if (min[i] <= j && j <= max[i]) {// 実際の人数
						count++;
					}
					if (count > Integer.parseInt(shift[k][1])) {
						Data data = new Data(i, max[i]);
						data.MaxDecrease(min, max, j);
					}
					if(count < Integer.parseInt(shift[k][1])) {//decreaseで減らして足りない場合は戻す
						for(int l = 0; l < max.length; l++) {
							if(max[l] < finalMax[l]) {
								max[l] = finalMax[l];
							}
						}
					}
				}

				if (count >= Integer.parseInt(shift[k][1])) {
					System.out.println(count + "人/" + shift[k][1]);
				} else {
					int husoku = Integer.parseInt(shift[k][1]) - count;
					System.out.println(count + "人/" + shift[k][1] + "|" + husoku + "人足りません");
				}
				
			}
		}
		for(int i = 0; i < time.length; i++) {
			System.out.println(max[i]);}

	}

}

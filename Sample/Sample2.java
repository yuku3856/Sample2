package Sample;

public class Sample2 {

	public static void main(String[] args) {
		String[] time = { "8～20", "18～20", null, "11～15", "14～21", "10～18", "11～16", "17～20", "10～20" };
		// １１～１４ 二人 ～１７まで一人 ～２０まで二人
		String[][] shift = { { "10～11", "2" }, { "11～14", "3" }, { "14～18", "4" }, { "18～20", "3" } };
		// 一時間単位 < 36

		// nullデータに例外データを格納
		for (int i = 0; i < time.length; i++) {
			if (time[i] == null) {
				time[i] = "-1～-1";
			}
		}

		int[] min = new int[time.length];
		int[] originalMin = new int[time.length];
		int[] max = new int[time.length];
		int[] originalMax = new int[time.length];

		// シフトをminとmaxに格納
		for (int i = 0; i < time.length; i++) {
			String[] data = time[i].split("～");
			min[i] = Integer.parseInt(data[0]);
			originalMin[i] = Integer.parseInt(data[0]);
			max[i] = Integer.parseInt(data[1]);
			originalMax[i] = Integer.parseInt(data[1]);
		}
	

		// 理想シフトの[i][0]をminとmaxで格納
		int[] shiftMin = new int[shift.length];
		int[] shiftMax = new int[shift.length];
		for (int i = 0; i < shift.length; i++) {
			String[] data = shift[i][0].split("～");
			shiftMin[i] = Integer.parseInt(data[0]);
			shiftMax[i] = Integer.parseInt(data[1]);
		}

		for (int i = 0; i < min.length; i++) {// シフト対象外の時間を整理
			if (min[i] < shiftMin[0] && min[i] != -1) {
				min[i] = shiftMin[0];
			}
		}
		for (int i = 0; i < max.length; i++) {
			if (max[i] > shiftMax[shiftMax.length - 1] && max[i] != -1) {// シフト対象外の時間を整理
				max[i] = shiftMax[shiftMax.length - 1];
			}
		}

		int count = 0;// 実際の人数
		for (int i = shiftMin[0]; i < shiftMin[0] + 3; i++) {//営業開始３hの人数調整
			for (int j = 0; j < min.length; j++) {
				// その時間帯の人数
				if (min[j] <= i && i < max[j]) {
					count++;
				}
			}

			for (int k = 0; k < shiftMin.length; k++) {
				if (shiftMin[k] <= i && i < shiftMax[k]) {
					if (count > Integer.parseInt(shift[k][1])) {
						for (int l = 0; l < count - Integer.parseInt(shift[k][1]); l++) {
							decrease.MinDecrease(min, i);
						}
					}
				}

			}
			count = 0;
		}
		
		
		
		
		//営業終了までの３hの人数調整
		for (int i = shiftMax[shiftMax.length - 1] - 1; i > shiftMax[shiftMax.length - 1] - 4; i--) {
			for (int j = 0; j < max.length; j++) {
				// その時間帯の人数
				if (min[j] <= i && i < max[j]) {
					count++;
				}
			}
			for (int k = 0; k < shiftMax.length; k++) {
				if (shiftMin[k] <= i && i < shiftMax[k]) {
					if (count > Integer.parseInt(shift[k][1])) {
						for (int l = 0; l < count - Integer.parseInt(shift[k][1]); l++) {
							decrease.MaxDecrease(max, i + 1);
						}
					}
				}

			}
			count = 0;
		}

		for(int i = 0; i < min.length; i++) {//3時間未満のシフト削除
			if(max[i] - min[i] < 3 && originalMax[i] - originalMin[i] > 3) {
				int a = min[i];//上書きする前の値
				int b = max[i];//次のfor文で利用し、条件次第で値を戻すために使用
				min[i] = -1;
				max[i] = -1;
				for(int j = a; a < b; a++ ) {
					
				}
			}
		}
		
		
		int[] decreaseShift = new int[min.length];//シフト減らした回数を保存
		for(int i = 0; i < min.length; i++) {
			if(min[i] != originalMin[i] || max[i] != originalMax[i]) {
				decreaseShift[i]++;
			}
		}
		for(int i = 0; i < min.length; i++) {
			System.out.println(decreaseShift[i]);
			}
		
		
		
		
		
		
		
	}

}

//営業の始まりと終わり（±３h）で削る（同じ日は同じ人を削る）
//→削って３ｈ未満の人は削除（できれば初期値で３ｈ未満の人は除く）

//→足りなくなったら元に戻して他の人を削る
//→全員やってうまくいかない場合は消さずに放置
//→これを一週間分やる
//→休憩時間と人数の余りを比べる
//→一番多いところから週の出勤回数がオーバーしてる人を削る
//→休憩分は残して、余った部分を削る（シフトの真ん中から削る）
//
//→削って３ｈ未満の人は削除☆
//→足りなくなったら元に戻して他の人を削る☆
//
//※削った時間はカウントして、カウントが少ない人を優先して削る

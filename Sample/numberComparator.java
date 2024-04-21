package Sample;

import java.util.Comparator;

public class numberComparator implements Comparator<Data> {
//Dataクラスのリストをtimeが小さい順に並び替え
	
	
	public int compare(Data a, Data b) {
        int no1 = a.getTime();
        int no2 = b.getTime();

        //昇順でソート
        if (no1 > no2) {
            return 1;

        } else if (no1 == no2) {
            return 0;

        } else {
            return -1;
        }
	}
}

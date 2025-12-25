import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static List<List<Integer>> dp = new ArrayList<>();
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        for(int i = 0; i< 3 ;i++) {
            dp.add(new ArrayList<>());
            for(int j = 0; j< 3; j++) {

                    dp.get(i).add(0);
            }
        }

        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        int[] input = {1,2,3,4,5,6,7,8};
        int strike = 0;
        for(int i = 0; i < 8 ; i++) {
            strike += fillTheSearchedIndexInDP(mat,input[i]);
        }

        System.out.println(strike);

    }

    public static int findNumberOfStrike(int i ,int j ) {
        boolean strike = true;
        int result = 0;
        for( int k = 0; k < 3 ; k++) {
            if(dp.get(i).get(k) == -1) {
                continue;
            } else {
                strike = false;
                break;
            }
        }
        if(strike) {
            result++;
        }
        for(int c = 0 ; c < 3 ; c++) {
            if(dp.get(c).get(j) == -1) {
                strike = true;
                continue;
            } else {
                strike = false;
                break;
            }
        }

        if(strike) {
            result++;
        }

        for(int r = 0 , col = 0 ; r < 3; r++,col++) {
            if(dp.get(r).get(col) == -1) {
                strike = true;
                continue;
            } else {
                strike = false;
                break;
            }
        }
        if(strike) {
            result++;
        }

        for(int r = 2 , col = 0 ; r >= 0; r--,col++) {
            if(dp.get(r).get(col) == -1) {
                strike = true;
                continue;
            } else {
                strike = false;
                break;
            }
        }
        if(strike) {
            result++;
        }

        return result;

    }

    private static int fillTheSearchedIndexInDP(int[][] arr, int input) {
        int strike = 0;
        for(int i = 0; i< 3 ;i++) {
            for(int j = 0; j< 3; j++) {
                if(arr[i][j] == input && dp.get(i).get(j) != -1) {
                    dp.get(i).set(j,-1);
                    strike += findNumberOfStrike(i ,j);
                }
            }
        }
        return strike;
    }
}
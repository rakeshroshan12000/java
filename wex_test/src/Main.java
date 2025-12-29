// Online Java Compiler
// Use this editor to write, compile and run your Java code online
// Given an array of array and array contains meeting start and end time
//[[8,10],[9,10],[13,15],[4,5]]
//Example
//intervals = [[1, 5], [4, 6], [7, 8]] -> 2
//intervals = [[6, 15], [13, 20], [1, 10], [10, 15]] -> 3
//intervals = [[1, 2], [2, 3], [3, 4]] -> 1
//intervals = [[1, 10], [2, 11], [3, 12]] -> 3
//intervals = [[1,10],[2,3],[3,4],[1,5],[1,3]]
//[0,0,0,0, .... ,0]
//[1,1,1,1,1,....,0]
//[1,1,2,2,2,1....]   = o(n),O(24)
// room1 :- []
//room2 :-[4,6]
class Main {
    public static void main(String[] args) {
//        int[][] time = {{6, 15}, {13, 20}, {1, 10}, {10, 15}};
//        int[][] time = {{1, 5}, {4, 6}, {7, 8}};
//        int[][] time = {{1,10},{2,3},{3,4},{1,5},{1,3}};
//        int[][] time = {{8,10},{9,10},{13,15},{4,5}};
        int[][] time = {{1,2},{2,3},{3,4}};
        System.out.println(minNoRoom(time));

    }

    private static int minNoRoom(int[][] input) {
        int[] result =new int[24];
        for(int i = 0 ; i< input.length ;i++) {
            for(int j = input[i][0];j < input[i][1] ; j++) {
                result[j]++;
            }
        }
        int room = 0;
        for(int k = 0; k< result.length ; k++) {
            if(room < result[k]) {
                room = result[k];
            }
        }
        return room;
    }
}
package codingTest;

import java.util.*;

public class CodingTest {
    //	첫번째
//	 public static String reverse(String nums){
//	        StringBuilder sb = new StringBuilder(nums).reverse();
//
//	        return sb.toString();
//	    }
//	두번째
//    public static String reverse2(String str, int[][] arr){
//        StringBuilder sb = new StringBuilder(str);
//        StringBuilder sb2 = new StringBuilder();
//        for(int i=0;i<arr.length;i++) {
//            for(int j=0;j<arr[i].length;j++) {
////        		배열의 모든값 1씩 감
//                arr[i][j]--;
//            }
//        }
//        for(int j = 0; j < arr.length; j++) {
//            for(int i = arr[j][0]; i < arr[j][1]; i++) {
//                sb2.append(sb.charAt(i));
//            }
//
//            sb2.reverse();
//            sb.replace(arr[j][0], arr[j][1], sb2.toString()); // 문자열 대체로 수정
//            sb2.setLength(0); // sb2 초기화
//        }
//
//        return sb.toString();
//    }

//    소인수분해 문제
//    public static int[] solution(int n) {
//
//        TreeSet<Integer> nums = new TreeSet<>();
//
//        if(n == 0|| n == 1) {
//            nums.add(n);
//        };
//        for (int i = 2; i <= n; i++) {
//            while (n % i == 0) {
//                nums.add(i);
//                n /= i;
//            }
//        }
//        return Arrays.stream(nums.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
//
//    }

//    public static boolean isPrime(int n) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            if(n%i==0) {
//                list.add(i);
//            }
//        }
//        if(list.size()>2) {
//            return false;
//        }
//        return true;
//    }


    public static void main(String[] args) {
//		 첫번째
//	        String[] nums = {"1232311", "1232311", "1232311", "1232311", "1232311", "1232311", "1232311"};
//	        int answer = 0;
//	        for(int i=0; i<nums.length; i++){
//	            if(reverse(nums[i]).charAt(0) == nums[i].charAt(0)){
//	                answer++;
//	            }
//
//	        }
//	        System.out.println(answer);
//		 두번째
//		 int[][] intervals = {{1,2},{2,3},{2,4}};
//		String str= "일이삼사오";
//		System.out.println(reverse2(str,intervals));
//        소인수분해 문제용
//        int n = (int)Math.floor(Math.random()*100);
//        System.out.println(n);
//        System.out.println(Arrays.toString(solution(n)));




    }

}


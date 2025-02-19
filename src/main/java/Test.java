import java.util.HashSet;

public class Test {

	public static void main(String[] args) {
		HashSet<Integer> list = new HashSet<>(); //HashSet 이라는 배열 선언 배열의 이름은 list
		//hashSet의 특징 : 중복값은 제거됨
		for(int i=1;i<6;i++) {//첫번째 주사위 (i) , i<6 (6면체 주사위) i++(i가 1씩 증가)
			for(int k=1;k<6;k++) { //두번째 주사위(k) 
				list.add(i*k);//배열에 i*k의 값을 추가 이 떄 중복된 값은 제거됨
			}
		}
		System.out.println(list.toString()); //list라는 배열에 있는 모든 값을 출력(toString)
		System.out.println(list.size()); //list안에 들어있는 값의 갯수를 출력(size());
		

	}

}

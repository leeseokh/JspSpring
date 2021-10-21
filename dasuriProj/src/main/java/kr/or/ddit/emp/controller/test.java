package kr.or.ddit.emp.controller;

public class test {

	public static void main(String[] args) {
		String addr = "33355 대전 은행로112";
		//스페이스바로써 split하여 배열로 만듦
		String[] addrArr = addr.split(" ");
		//배열의 크기만큼 결과 배열을 만듦
		String[] result = new String[2];
		result[1] = "";
		
		for(int i=0;i<addrArr.length;i++) {
			if(i==0) {
				result[i] = addrArr[i];//우편번호
			}else {
				if(addrArr[i]!=null) {
					result[1] += addrArr[i] + " ";
				}
			}
		}
		System.out.println(result[0]);//우편번호
		System.out.println(result[1]);//나머지주소

	}

}

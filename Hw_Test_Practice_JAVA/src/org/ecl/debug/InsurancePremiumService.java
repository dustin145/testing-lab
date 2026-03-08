package org.ecl.debug;

import java.util.Scanner;

/**
 * 보험료 계산 프로그램 - 메인 클래스
 * 사용자로부터 입력을 받아 보험료를 계산하고 결과를 출력
 * 디버깅 연습용
 */
public class InsurancePremiumService {
	
	private InsurancePremiumCalculator calculator;
	private Scanner scanner;
	
	public InsurancePremiumService() {
		this.calculator = new InsurancePremiumCalculator();
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * 프로그램 시작
	 */
	public void start() {
		System.out.println("========================================");
		System.out.println("   보험료 계산 시스템");
		System.out.println("========================================\n");
		
		boolean continueProgram = true;
		
		while (continueProgram) {
			try {
				// 1. 보험상품 입력
				String product = inputProduct();
				
				// 2. 성별 입력
				String gender = inputGender();
				
				// 3. 나이 입력
				int age = inputAge();
				
				// 4. 보험료 계산
				double premium = calculator.calculatePremium(product, gender, age);
				
				// 5. 결과 출력
				displayResult(product, gender, age, premium);
				
			} catch (IllegalArgumentException e) {
				System.out.println("\n❌ 오류: " + e.getMessage());
			}
			
			// 계속 진행 여부 확인
			System.out.println("\n계속하시겠습니까? (Y/N): ");
			String response = scanner.nextLine().trim().toUpperCase();
			continueProgram = response.equals("Y");
		}
		
		System.out.println("\n프로그램을 종료합니다.");
		scanner.close();
	}
	
	/**
	 * 보험상품 입력
	 */
	private String inputProduct() {
		System.out.println("\n[보험상품 선택]");
		System.out.println("1. HEALTH  (건강보험)");
		System.out.println("2. AUTO    (자동차보험)");
		System.out.println("3. LIFE    (생명보험)");
		System.out.print("선택 (1~3): ");
		
		String choice = scanner.nextLine().trim();
		
		switch (choice) {
			case "1":
				return "HEALTH";
			case "2":
				return "AUTO";
			case "3":
				return "LIFE";
			default:
				throw new IllegalArgumentException("1~3 중에서 선택해주세요.");
		}
	}
	
	/**
	 * 성별 입력
	 */
	private String inputGender() {
		System.out.println("\n[성별 선택]");
		System.out.println("1. 남성 (M)");
		System.out.println("2. 여성 (F)");
		System.out.print("선택 (1~2): ");
		
		String choice = scanner.nextLine().trim();
		
		switch (choice) {
			case "1":
				return "M";
			case "2":
				return "F";
			default:
				throw new IllegalArgumentException("1~2 중에서 선택해주세요.");
		}
	}
	
	/**
	 * 나이 입력
	 */
	private int inputAge() {
		System.out.println("\n[나이 입력]");
		System.out.print("나이를 입력하세요 (0~120): ");
		
		String input = scanner.nextLine().trim();
		int age = Integer.parseInt(input);
		
		if (age < 0 || age > 120) {
			throw new IllegalArgumentException("나이는 0~120 사이여야 합니다.");
		}
		
		return age;
	}
	
	/**
	 * 계산 결과 출력
	 */
	private void displayResult(String product, String gender, int age, double premium) {
		String productName = getProductName(product);
		String genderName = getGenderName(gender);
		String ageGroup = calculator.getAgeGroup(age);
		
		System.out.println("\n========================================");
		System.out.println("           보험료 계산 결과");
		System.out.println("========================================");
		System.out.println("보험상품: " + productName);
		System.out.println("고객성별: " + genderName);
		System.out.println("고객나이: " + age + "세 (" + ageGroup + ")");
		System.out.println("----------------------------------------");
		System.out.println("💰 보험료: " + String.format("%,.2f원", premium));
		System.out.println("========================================");
	}
	
	/**
	 * 보험상품명 반환
	 */
	private String getProductName(String product) {
		switch (product) {
			case "HEALTH":
				return "건강보험";
			case "AUTO":
				return "자동차보험";
			case "LIFE":
				return "생명보험";
			default:
				return product;
		}
	}
	
	/**
	 * 성별명 반환
	 */
	private String getGenderName(String gender) {
		return "M".equals(gender) ? "남성" : "여성";
	}
	
	/**
	 * 메인 메서드 - 프로그램 시작점
	 */
	public static void main(String[] args) {
		InsurancePremiumService service = new InsurancePremiumService();
		service.start();
	}
}

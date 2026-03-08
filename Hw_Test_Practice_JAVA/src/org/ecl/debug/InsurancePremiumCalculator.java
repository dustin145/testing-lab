package org.ecl.debug;

/**
 * 보험료 계산기
 * 보험상품, 고객성별, 나이에 따라 보험료가 다르게 계산됨
 */
public class InsurancePremiumCalculator {
	
	/**
	 * 보험료 계산
	 * @param product 보험상품 (HEALTH, AUTO, LIFE)
	 * @param gender 성별 (M: 남성, F: 여성)
	 * @param age 나이
	 * @return 보험료
	 */
	public double calculatePremium(String product, String gender, int age) {
		// 입력값 검증
		if (product == null || product.isEmpty()) {
			throw new IllegalArgumentException("보험상품은 필수입니다.");
		}
		if (!gender.equals("M") && !gender.equals("F")) {
			throw new IllegalArgumentException("성별은 M 또는 F이어야 합니다.");
		}
		if (age < 0 || age > 120) {
			throw new IllegalArgumentException("나이는 0~120 사이여야 합니다.");
		}
		
		// 기본 보험료
		double basePremium = getBasePremium(product);
		
		// 성별별 할인/할증률
		double genderRate = getGenderRate(gender);
		
		// 나이별 할증률
		double ageRate = getAgeRate(age);
		
		// 최종 보험료 계산
		double finalPremium = basePremium * genderRate * ageRate;
		
		return Math.round(finalPremium * 100) / 100.0; // 소수점 둘째자리까지 반올림
	}
	
	/**
	 * 보험상품별 기본 보험료
	 */
	private double getBasePremium(String product) {
		switch (product) {
			case "HEALTH":
				return 50000; // 건강보험 기본료
			case "AUTO":
				return 100000; // 자동차보험 기본료
			case "LIFE":
				return 150000; // 생명보험 기본료
			default:
				throw new IllegalArgumentException("지원하지 않는 보험상품입니다: " + product);
		}
	}
	
	/**
	 * 성별별 할인/할증률
	 * 남성: 1.1 (할증 10%)
	 * 여성: 0.9 (할인 10%)
	 */
	private double getGenderRate(String gender) {
		if ("M".equals(gender)) {
			return 1.1; // 남성 할증
		} else if ("F".equals(gender)) {
			return 0.9; // 여성 할인
		}
		return 1.0;
	}
	
	/**
	 * 나이별 할증률
	 * 20대 이하: 1.0 (기본)
	 * 30대: 1.1 (할증 10%)
	 * 40대: 1.2 (할증 20%)
	 * 50대: 1.4 (할증 40%)
	 * 60대 이상: 1.7 (할증 70%)
	 */
	private double getAgeRate(int age) {
		if (age <= 20) {
			return 1.0;
		} else if (age <= 30) {
			return 1.1;
		} else if (age <= 40) {
			return 1.2;
		} else if (age <= 50) {
			return 1.4;
		} else {
			return 1.7;
		}
	}
	
	/**
	 * 나이에 따른 구간 이름 반환
	 */
	public String getAgeGroup(int age) {
		if (age <= 20) {
			return "20대 이하";
		} else if (age <= 30) {
			return "20-30대";
		} else if (age <= 40) {
			return "30-40대";
		} else if (age <= 50) {
			return "40-50대";
		} else {
			return "50대 이상";
		}
	}
}

package org.junit.practice;

/**
 * IDNumberValidator 클래스에 대한 간단한 테스트
 * 주민번호 검증 로직을 테스트합니다.
 */
class IDNumberValidatorTest {
    
    private IDNumberValidator validator;
    
    IDNumberValidatorTest() {
        validator = new IDNumberValidator();
    }
    
    // 테스트 결과를 출력하는 헬퍼 메서드
    private void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println("✓ PASS: " + testName);
        } else {
            System.out.println("✗ FAIL: " + testName);
        }
    }
    
    private void assertFalse(boolean condition, String testName) {
        if (!condition) {
            System.out.println("✓ PASS: " + testName);
        } else {
            System.out.println("✗ FAIL: " + testName);
        }
    }
    
    // ===== 유효한 주민번호 테스트 =====
    
    void testValidIDWithHyphen() {
        assertTrue(validator.isValid("900101-1234567"), "유효한 주민번호 - 하이픈 포함");
    }
    
    void testValidIDWithoutHyphen() {
        assertTrue(validator.isValid("9001011234567"), "유효한 주민번호 - 하이픈 없음");
    }
    
    void testValidIDOtherDate() {
        assertTrue(validator.isValid("850508-2345678"), "유효한 주민번호 - 다른 날짜");
    }
    
    // ===== null 및 빈 값 테스트 =====
    
    void testNullInput() {
        assertFalse(validator.isValid(null), "null 입력은 false 반환");
    }
    
    void testEmptyInput() {
        assertFalse(validator.isValid(""), "빈 문자열은 false 반환");
    }
    
    // ===== 길이 오류 테스트 =====
    
    void testTooShort() {
        assertFalse(validator.isValid("900101-123456"), "12자리는 false 반환");
    }
    
    void testTooLong() {
        assertFalse(validator.isValid("900101-12345678"), "14자리는 false 반환");
    }
    
    // ===== 숫자 포함 여부 테스트 =====
    
    void testWithLetters() {
        assertFalse(validator.isValid("90010a-1234567"), "문자가 포함되면 false 반환");
    }
    
    void testWithSpecialChars() {
        assertFalse(validator.isValid("900101#1234567"), "특수문자가 포함되면 false 반환");
    }
    
    // ===== 월(Month) 검증 테스트 =====
    
    void testInvalidMonth00() {
        assertFalse(validator.isValid("900001-1234567"), "월이 00이면 false 반환");
    }
    
    void testInvalidMonth13() {
        assertFalse(validator.isValid("901301-1234567"), "월이 13이면 false 반환");
    }
    
    void testValidMonth12() {
        assertTrue(validator.isValid("901215-1234567"), "유효한 월 12");
    }
    
    // ===== 일(Day) 검증 테스트 =====
    
    void testInvalidDay00() {
        assertFalse(validator.isValid("900100-1234567"), "일이 00이면 false 반환");
    }
    
    void testInvalidDay32() {
        assertFalse(validator.isValid("900132-1234567"), "일이 32이면 false 반환");
    }
    
    void testValidDay31() {
        assertTrue(validator.isValid("900131-1234567"), "유효한 일 31");
    }
    
    // 메인 메서드: 모든 테스트를 실행
    public static void main(String[] args) {
        System.out.println("========== 주민번호 검증 테스트 시작 ==========\n");
        IDNumberValidatorTest test = new IDNumberValidatorTest();
        
        System.out.println("[유효한 주민번호 테스트]");
        test.testValidIDWithHyphen();
        test.testValidIDWithoutHyphen();
        test.testValidIDOtherDate();
        
        System.out.println("\n[null 및 빈 값 테스트]");
        test.testNullInput();
        test.testEmptyInput();
        
        System.out.println("\n[길이 오류 테스트]");
        test.testTooShort();
        test.testTooLong();
        
        System.out.println("\n[숫자 포함 여부 테스트]");
        test.testWithLetters();
        test.testWithSpecialChars();
        
        System.out.println("\n[월(Month) 검증 테스트]");
        test.testInvalidMonth00();
        test.testInvalidMonth13();
        test.testValidMonth12();
        
        System.out.println("\n[일(Day) 검증 테스트]");
        test.testInvalidDay00();
        test.testInvalidDay32();
        test.testValidDay31();
        
        System.out.println("\n========== 테스트 완료 ==========");
    }

}

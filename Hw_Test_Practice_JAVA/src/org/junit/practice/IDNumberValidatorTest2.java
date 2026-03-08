package org.junit.practice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * IDNumberValidator 클래스에 대한 JUnit 테스트
 * 주민번호 검증 로직을 더 많은 테스트 케이스로 검증합니다.
 */
public class IDNumberValidatorTest2 {
	
	private IDNumberValidator validator;
	
	@Before
	public void setUp() {
		validator = new IDNumberValidator();
	}
	
	// ===== 유효한 주민번호 테스트 =====
	
	@Test
	public void testValidIDWithHyphen() {
		assertTrue(validator.isValid("900101-1234567"));
	}
	
	@Test
	public void testValidIDWithoutHyphen() {
		assertTrue(validator.isValid("9001011234567"));
	}
	
	@Test
	public void testValidIDMultipleDates() {
		assertTrue(validator.isValid("850508-2345678"));
		assertTrue(validator.isValid("790315-1012342"));
		assertTrue(validator.isValid("950101-0000001"));
	}
	
	// ===== 경계값 테스트 =====
	
	@Test
	public void testValidJanuary1st() {
		assertTrue(validator.isValid("900101-1234567"));
	}
	
	@Test
	public void testValidDecember31st() {
		assertTrue(validator.isValid("901231-1234567"));
	}
	
	// ===== null 및 빈 문자열 테스트 =====
	
	@Test
	public void testNullInput() {
		assertFalse(validator.isValid(null));
	}
	
	@Test
	public void testEmptyString() {
		assertFalse(validator.isValid(""));
	}
	
	@Test
	public void testBlankString() {
		assertFalse(validator.isValid("   "));
	}
	
	// ===== 길이 검증 테스트 =====
	
	@Test
	public void testTooShort() {
		assertFalse(validator.isValid("900101-123456"));
	}
	
	@Test
	public void testTooLong() {
		assertFalse(validator.isValid("900101-12345678"));
	}
	
	@Test
	public void testAfterHyphenRemovalTooShort() {
		assertFalse(validator.isValid("900101-12345"));
	}
	
	// ===== 숫자 유효성 테스트 =====
	
	@Test
	public void testWithLetters() {
		assertFalse(validator.isValid("90010a-1234567"));
		assertFalse(validator.isValid("900101-123456a"));
	}
	
	@Test
	public void testWithSpecialCharacters() {
		assertFalse(validator.isValid("900101#1234567"));
		assertFalse(validator.isValid("900101@1234567"));
	}
	
	@Test
	public void testWithKoreanCharacters() {
		assertFalse(validator.isValid("가00101-1234567"));
	}
	
	// ===== 월(Month) 검증 테스트 =====
	
	@Test
	public void testMonthZero() {
		assertFalse(validator.isValid("900001-1234567"));
	}
	
	@Test
	public void testMonthThirteen() {
		assertFalse(validator.isValid("901301-1234567"));
	}
	
	@Test
	public void testMonthTwenty() {
		assertFalse(validator.isValid("902001-1234567"));
	}
	
	@Test
	public void testAllValidMonths() {
		for (int month = 1; month <= 12; month++) {
			String monthStr = String.format("%02d", month);
			String idNumber = "90" + monthStr + "01-1234567";
			assertTrue("Month: " + month, validator.isValid(idNumber));
		}
	}
	
	// ===== 일(Day) 검증 테스트 =====
	
	@Test
	public void testDayZero() {
		assertFalse(validator.isValid("900100-1234567"));
	}
	
	@Test
	public void testDayThirtyTwo() {
		assertFalse(validator.isValid("900132-1234567"));
	}
	
	@Test
	public void testDayNinetyNine() {
		assertFalse(validator.isValid("900199-1234567"));
	}
	
	@Test
	public void testValidDays() {
		assertTrue(validator.isValid("900101-1234567"));  // 1일
		assertTrue(validator.isValid("900115-1234567"));  // 15일
		assertTrue(validator.isValid("900131-1234567"));  // 31일
	}
	
	// ===== 시리즈 번호 테스트 =====
	
	@Test
	public void testMinimumSeriesNumber() {
		assertTrue(validator.isValid("900101-0000001"));
	}
	
	@Test
	public void testMaximumSeriesNumber() {
		assertTrue(validator.isValid("900101-9999999"));
	}
	
	// ===== 다양한 포맷 테스트 =====
	
	@Test
	public void testWithExtraSpaces() {
		assertFalse(validator.isValid("900101 1234567"));
	}
	

}

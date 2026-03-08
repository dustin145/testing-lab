package org.junit.practice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * IDNumberValidator 클래스에 대한 JUnit 테스트
 * 의도적으로 실패하는 테스트 케이스들을 포함합니다.
 * (테스트 실패 사례를 보여주기 위한 파일)
 */
public class IDNumberValidatorTest3 {
	
	private IDNumberValidator validator;
	
	@Before
	public void setUp() {
		validator = new IDNumberValidator();
	}
	
	// ===== 의도적으로 실패하는 테스트 케이스 =====
	
	@Test
	public void testFailCase1_WrongAssertion() {
		// 실제로는 유효한 주민번호인데 false를 기대
		assertFalse("이 테스트는 실패합니다!", validator.isValid("900101-1234567"));
	}
	
	@Test
	public void testFailCase2_WrongExpectation() {
		// null을 true로 기대 (실제는 false)
		assertTrue("null을 유효한 주민번호로 기대할 수 없습니다!", validator.isValid(null));
	}
	
	@Test
	public void testFailCase3_InvalidMonthShouldBeTreatedAsValid() {
		// 월이 13인데 유효하다고 기대 (실제는 무효)
		assertTrue("13월도 유효해야 한다고 주장", validator.isValid("901301-1234567"));
	}
	
	@Test
	public void testFailCase4_InvalidDayShouldBeTreatedAsValid() {
		// 일이 32인데 유효하다고 기대 (실제는 무효)
		assertTrue("32일도 유효해야 한다고 주장", validator.isValid("900132-1234567"));
	}
	
	@Test
	public void testFailCase5_SpecialCharactersShouldBeValid() {
		// 특수문자가 포함되었지만 유효하다고 기대
		assertTrue("특수문자도 괜찮아야 한다고 주장", validator.isValid("900101#1234567"));
	}
	
	@Test
	public void testFailCase6_LettersAllowed() {
		// 문자가 포함되었지만 유효하다고 기대
		assertTrue("문자도 허용되어야 한다고 주장", validator.isValid("90010a-1234567"));
	}
	
	@Test
	public void testFailCase7_WrongLengthShouldBeValid() {
		// 12자리인데 유효하다고 기대
		assertTrue("12자리도 충분하다고 주장", validator.isValid("900101-123456"));
	}
	
	@Test
	public void testFailCase8_EmptyStringShouldBeValid() {
		// 빈 문자열이 유효하다고 기대
		assertTrue("빈 문자열도 유효해야 한다고 주장", validator.isValid(""));
	}
	
	@Test
	public void testFailCase9_SpacesShouldBeIgnored() {
		// 공백이 무시된다고 기대 (실제로는 무효)
		assertTrue("공백은 무시되어야 한다고 주장", validator.isValid("900101 1234567"));
	}
	
	@Test
	public void testFailCase10_MonthZeroShouldBeValid() {
		// 월이 00이 유효하다고 기대 (실제는 무효)
		assertTrue("0월도 유효해야 한다고 주장", validator.isValid("900001-1234567"));
	}
	
	@Test
	public void testFailCase11_DayZeroShouldBeValid() {
		// 일이 00이 유효하다고 기대 (실제는 무효)
		assertTrue("0일도 유효해야 한다고 주장", validator.isValid("900100-1234567"));
	}
	
	@Test
	public void testFailCase12_MultipleHyphensShouldBeValid() {
		// 여러 개의 하이픈이 있어도 유효하다고 기대
		assertTrue("여러 하이픈도 괜찮아야 한다고 주장", validator.isValid("9001-01-1234567"));
	}
	
	// ===== 혼합된 테스트 (일부 통과, 일부 실패) =====
	
	@Test
	public void testMixedCase1_ValidAndInvalid() {
		// 하나는 유효, 하나는 무효인데 모두 유효하다고 기대
		assertTrue("첫 번째", validator.isValid("900101-1234567"));  // PASS
		assertTrue("두 번째 (이 부분에서 실패)", validator.isValid("901301-1234567"));  // FAIL
	}
	
	@Test
	public void testMixedCase2_NullCheckFails() {
		assertNotNull("null은 아니어야 한다고 주장", validator.isValid(null));
	}
	
	@Test
	public void testMixedCase3_EqualityFail() {
		// 두 개의 유효한 주민번호가 같은 결과를 반환할 거라고 기대
		boolean result1 = validator.isValid("900101-1234567");
		boolean result2 = validator.isValid("850508-2345678");
		assertEquals("두 결과가 같아야 한다고 주장", result1, !result2);  // 반대로 기대
	}
	
	// ===== 경계값에서의 의도적 실패 =====
	
	@Test
	public void testBoundaryFail_Day31ShouldFail() {
		// 31일은 무효해야 한다고 기대 (실제는 유효)
		assertFalse("31일은 너무 크다고 주장", validator.isValid("900131-1234567"));
	}
	
	@Test
	public void testBoundaryFail_Month12ShouldFail() {
		// 12월은 무효해야 한다고 기대 (실제는 유효)
		assertFalse("12월은 없어야 한다고 주장", validator.isValid("901215-1234567"));
	}
	
	@Test
	public void testBoundaryFail_Day1ShouldFail() {
		// 1일은 무효해야 한다고 기대 (실제는 유효)
		assertFalse("1일은 유효하지 않아야 한다고 주장", validator.isValid("900101-1234567"));
	}
	
	@Test
	public void testBoundaryFail_Month1ShouldFail() {
		// 1월은 무효해야 한다고 기대 (실제는 유효)
		assertFalse("1월은 없어야 한다고 주장", validator.isValid("900115-1234567"));
	}
	
	// ===== 일관성 없는 검증 =====
	
	@Test
	public void testInconsistency1() {
		// 하이픈이 있는 것과 없는 것이 다른 결과를 반환할 거라고 기대
		boolean withHyphen = validator.isValid("900101-1234567");
		boolean withoutHyphen = validator.isValid("9001011234567");
		assertNotEquals("하이픈 유무에 따라 다른 결과여야 한다고 주장", withHyphen, withoutHyphen);
	}
	
	@Test
	public void testInconsistency2() {
		// 월이 13일 때와 1일 때가 같은 결과를 반환할 거라고 기대
		boolean invalid = validator.isValid("901301-1234567");
		boolean valid = validator.isValid("900101-1234567");
		assertTrue("같은 결과여야 한다고 주장", invalid == valid);
	}

}

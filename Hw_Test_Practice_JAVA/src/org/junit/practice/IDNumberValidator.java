package org.junit.practice;

/**
 * 주민번호 유효성 검증 클래스
 * 간단한 주민번호 형식 검증
 */
public class IDNumberValidator {
    
    /**
     * 주민번호가 유효한 형식인지 확인합니다.
     * 형식: YYMMDD-NNNNNNN (13자리)
     * 
     * @param idNumber 검증할 주민번호
     * @return 유효하면 true, 아니면 false
     */
    public boolean isValid(String idNumber) {
        // null 체크
        if (idNumber == null) {
            return false;
        }
        
        // 하이픈 제거
        String cleaned = idNumber.replace("-", "").trim();
        
        // 길이 확인 (13자리)
        if (cleaned.length() != 13) {
            return false;
        }
        
        // 숫자만 포함하는지 확인
        if (!cleaned.matches("\\d{13}")) {
            return false;
        }
        
        // 월 유효성 확인 (01-12)
        int month = Integer.parseInt(cleaned.substring(2, 4));
        if (month < 1 || month > 12) {
            return false;
        }
        
        // 일 유효성 확인 (01-31)
        int day = Integer.parseInt(cleaned.substring(4, 6));
        if (day < 1 || day > 31) {
            return false;
        }
        
        return true;
    }
}

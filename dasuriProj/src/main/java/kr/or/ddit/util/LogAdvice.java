package kr.or.ddit.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	/*
	 * logger.info() 로그기록해왔던 일
	 *  - 반복적이나 핵심 로직은 아님. 그래도 필요한 기능.
	 *  - 관심사로 간주.
	 *  - AOP의 Advice는 관심사를 실제로 구현한 코드. 
	 *  - LogAdvice : 로그를 기록해주는 Advice구현 코드
	 *  
	 * Component 어노테이션 : spring에서 빈(bean)으로 인식
	 */
	private static Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	@Pointcut("execution(* kr.or.ddit.cus..*(..))")
	public void logBefore() {}
	//Before, After, AfterReturning, AfterThrowing
	@Around("logBefore()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		//메서드의 명을 가져옴
		String signatureString = joinPoint.getSignature().toShortString();
		logger.info("==============================");
		logger.info(signatureString + " 시작");
		//메서드가 시작 시간
		long start = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			return result;
		}finally {
			//메서드가 종료한 시간
			long finish = System.currentTimeMillis();
			logger.info(signatureString + " 종료");
			logger.info(signatureString + " 실행 시간 : " + (finish - start) + "ms");
		}
	}
}







package it.clevercom.echo.common.logging.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Custom Aspect used to handle logging of classes annotated with {@link @Loggable}
 *
 */
@Aspect
public class MethodLogger {

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * This method triggers for every {@link @Loggable} annotated method.
	 * Logs method invocation with input parameters, proceeds with method execution and then logs method completition with result and execution time. 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* *(..)) && @annotation(it.clevercom.echo.common.logging.annotation.Loggable)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String fullyQualifiedMethodName = method.getDeclaringClass().getName() + "." +  method.getName();

		String inboundMessage = String.format("Invoking method : \"%s\" with input params : \"%s\"",
				fullyQualifiedMethodName,
				Arrays.toString(joinPoint.getArgs()));
		logger.info(inboundMessage);

		Object result = joinPoint.proceed();

		String outboundMessage = String.format("Executed method : \"%s\" with result : \"%s\", execution completed in %d msec",
				fullyQualifiedMethodName,
				result,
				System.currentTimeMillis() - startTime);
		logger.info(outboundMessage);

		return result;
	}
}

package hacker.thymeleaf.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceApp {

    @Around("execution(* hacker.thymeleaf..*(..))")
    public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            final long finish = System.currentTimeMillis();
            final long timeMs = finish - start;
            System.out.println("END: " + joinPoint + " " + timeMs + "ms");
        }
    }
}

package acme.sales.bookstore.domain.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

/**
 * @author vmuravlev
 */
public class VerboseAdministrationActionsAspect {

    private int callCount;

    public Object verboseAdministrationAction(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        System.out.printf("%s elapsed (ms): %d\n",
                joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());

        callCount++;
        return result;
    }

    public int getCallCount() {
        return this.callCount;
    }

    public void reset() {
        this.callCount = 0;
    }
}
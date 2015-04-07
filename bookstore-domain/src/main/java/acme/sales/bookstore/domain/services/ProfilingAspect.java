package acme.sales.bookstore.domain.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * @author vmuravlev
 */
public class ProfilingAspect {

    private int callCount;

    private Logger logger = LoggerFactory.getLogger("ProfilingAspect");

    public Object profilingAction(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        logger.info("{} elapsed (ms): {}",
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
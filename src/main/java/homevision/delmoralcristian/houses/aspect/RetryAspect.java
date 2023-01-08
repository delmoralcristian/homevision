package homevision.delmoralcristian.houses.aspect;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RetryAspect {

    @Autowired
    RetryTemplate retryTemplate;

    @Around("@annotation(homevision.delmoralcristian.houses.aspect.RetryAPICall)")
    public Object retry(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var logMessage = new StringBuilder("Executing retry - method name: ")
                .append(proceedingJoinPoint.getSignature());
        log.info(logMessage.toString());
        return retryTemplate.execute(retryCallback -> proceedingJoinPoint.proceed());
    }
}

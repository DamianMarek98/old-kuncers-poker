package deny.poker.poc.checker.validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class CheckerValidationAspect {

    @Around("@annotation(deny.poker.poc.checker.validation.CheckerValidator)")
    public Object checkerValidationPointcut(ProceedingJoinPoint pjp) throws Throwable {
        var methodSignature = (MethodSignature) pjp.getSignature();
        CheckerValidator validateRequestBody = Objects.requireNonNull(
                methodSignature.getMethod().getAnnotation(CheckerValidator.class),
                "No annotation on the method");

        if (validateRequestBody.figureRequired() && pjp.getArgs()[1] == null) {
            throw new FigureNotSpecifiedSetCheckerException(pjp.getSignature().getDeclaringTypeName());
        }

        if (validateRequestBody.colorRequired() && pjp.getArgs()[2] == null) {
            throw new ColorNotSpecifiedSetCheckerException(pjp.getSignature().getDeclaringTypeName());
        }

        return pjp.proceed();
    }
}

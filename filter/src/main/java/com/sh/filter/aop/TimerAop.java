package com.sh.filter.aop;

import com.sh.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class TimerAop {

    @Pointcut(value="within(com.sh.filter.controller.UserApiController)")
    public void timerPointCut(){

    }

    @Before(value="timerPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");
    }

    @After(value="timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    //예외 발생 안 함
    @AfterReturning(value="timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("after returning");
    }

    //예외 발생
    @AfterThrowing(value = "timerPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        System.out.println("after throwing");
    }



    //예외 발생/발생 안 함 모두 포함
    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("메소드 실행 이전");

        //해당 메소드의 모든 매개변수를 불러옴
        Arrays.stream(joinPoint.getArgs()).forEach(
                it->{
                    //전화번호 변환 (객체를 중간에 가로채서)
                    if(it instanceof UserRequest){
                        var tempUser = (UserRequest)it;
                        var phhoneNumber = tempUser.getPhoneNumber().replace("-","");
                        tempUser.setPhoneNumber(phhoneNumber);
                    }
                }
        );

        joinPoint.proceed();

//        /**
//         * 암/복호화, 로깅 할 때 이런식으로 사용
//         */
//        //빈 리스트를 만들어서 넘겨줬기에 값이 다 null로 처리 됨
//        var newObjs = Arrays.asList(
//                new UserRequest()
//        );
//        var stopWatch = new StopWatch();
//        stopWatch.start();
//        joinPoint.proceed(newObjs.toArray());
//        stopWatch.stop();
//        System.out.println("총 소요된 시간:"+stopWatch.getTotalTimeMillis());

        System.out.println("메소드 실행 이후");

    }
}

package com.jyx.jiaoshoujia.aop;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jyx.jiaoshoujia.annotation.LogAnnotation;
import com.jyx.jiaoshoujia.basecont.BaseCont;

import com.jyx.jiaoshoujia.enity.po.LogRecords;
import com.jyx.jiaoshoujia.mapper.LogOperationMapper;
import com.jyx.jiaoshoujia.menu.AopLogMenu;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
@Component
@Aspect
@Order(2)
public class LogAop {



    @Autowired
   private  ObjectMapper objectMapper;

    @Autowired
    private LogOperationMapper logOperationMapper;




    @Pointcut("@annotation(com.jyx.jiaoshoujia.annotation.LogAnnotation)")
    public void po()
    {}

    @After(value ="po()")
    public void AfterLongWrite(JoinPoint joinPoint)
    {
        LogRecords logRecords=new LogRecords();

        String params="";
         Long userId=null;

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);

        String className=joinPoint.getTarget().getClass().getName();

        String methodName = method.getName();

        methodName=methodName+className;

        Object[] args = joinPoint.getArgs();


        try
        {
             params = objectMapper.writeValueAsString(args);

        }
         catch(JsonProcessingException e){


        }

        String operation = annotation.operation();

        AopLogMenu aopLogMenu = annotation.aopLogMenu();

           try{userId = BaseCont.get().longValue(); }  //TODO 把登录人的id和注册人操作的id也传上去
           catch (NullPointerException e){
               System.out.print("有错误");
    }

          logRecords.setOperationUserId(userId);
          logRecords.setOperationMethodName(methodName);
          logRecords.setOperationName(operation);
          logRecords.setOperationTime(LocalDateTime.now());
          logRecords.setOperationBusinessType(aopLogMenu.toString());
          logRecords.setOperationParams(params);
          logOperationMapper.insertOperationLog(logRecords);

    }

}

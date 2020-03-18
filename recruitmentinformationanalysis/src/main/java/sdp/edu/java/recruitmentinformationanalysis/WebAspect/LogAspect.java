package sdp.edu.java.recruitmentinformationanalysis.WebAspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * spring Aop日志管理
 */
@Aspect
@Component
public class LogAspect {
    private static Logger logger = LogManager.getLogger(LogAspect.class);
    private String method;
    private String ip;
    private int port;
    private String host;
    private String url;
    private String req;

    @Pointcut("@annotation(sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.ReturnValue)")
    public void ReturnValueControllerAspect(){

    }
    @Pointcut("@annotation(sdp.edu.java.recruitmentinformationanalysis.CustomAnnotation.unReturnValue)")
    public void unReturnValueControllerAspect(){

    }

    @Before("ReturnValueControllerAspect()")
    public void beforeReturnControllerAspect(JoinPoint joinPoint){
        //取到request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ip = request.getRemoteAddr();//客户端ip
        host = request.getRemoteHost();//客户端主机
        method = request.getMethod();//客户端请求方法
        port = request.getRemotePort();//客户端请求port
        url=request.getRequestURL().toString();//请求url
        if(request.getParameter("workplace")!=null) {
            req = request.getParameter("workplace");//请求城市参数
            //记录client信息
            logger.info("client:ip=" + ip + ";host=" + host + ";method=" + method + ";port=" + port +";req="+req+";url="+url);
        }else {
            logger.info("client:ip=" + ip + ";host=" + host + ";method=" + method + ";port=" + port +";url="+url);
        }

    }
    /**
     * 方法环绕执行
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("ReturnValueControllerAspect()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object obj = proceedingJoinPoint.proceed();
        logger.info("return：" + obj);
        return obj;
    }

    @Before("unReturnValueControllerAspect()")
    public void beforeunReturnControllerAspect(JoinPoint joinPoint) {
        //取到request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ip = request.getRemoteAddr();//客户端ip
        host = request.getRemoteHost();//客户端主机
        method = request.getMethod();//客户端请求方法
        port = request.getRemotePort();//客户端请求port
        url = request.getRequestURL().toString();//请求url
        if (request.getParameter("workplace") != null) {
            req = request.getParameter("workplace");//请求城市参数
            //记录client信息
            logger.info("client:ip=" + ip + ";host=" + host + ";method=" + method + ";port=" + port + ";req=" + req + ";url=" + url);
        } else {
            logger.info("client:ip=" + ip + ";host=" + host + ";method=" + method + ";port=" + port + ";url=" + url);
        }
    }
    /**
     * 异常处理
     * @param e
     */
    @AfterThrowing(pointcut = "ReturnValueControllerAspect()", throwing = "e")
    public void doExceptionone(Throwable e) {
        if (e != null) {
            logger.error("doException系统异常：" + e.getMessage(), e);
        }
    }
    @AfterThrowing(pointcut = "unReturnValueControllerAspect()", throwing = "e")
    public void doExceptiontwo(Throwable e) {
        if (e != null) {
            logger.error("doException系统异常：" + e.getMessage(), e);
        }
    }
}

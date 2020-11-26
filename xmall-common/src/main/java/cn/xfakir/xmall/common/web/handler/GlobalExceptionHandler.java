package cn.xfakir.xmall.common.web.handler;

import cn.xfakir.xmall.common.web.XmResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {
    /**
     * 拦截捕捉 MissingServletRequestParameterException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public XmResult doMissingServletRequestParameterHandler(MissingServletRequestParameterException exception) {
        MissingServletRequestParameterException exs = (MissingServletRequestParameterException) exception;
        return XmResult.failure("空参",null);
    }

    /*@ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public XmResult ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Map map = new HashMap();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            System.err.println(cvl.getMessageTemplate());
            msgList.add(cvl.getMessageTemplate());
        }
        map.put("status", 500);
        map.put("msg", msgList);
        return map;
    }*/

    /**
     * 拦截捕捉 MethodArgumentNotValidException 异常
     */
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public XmResult  doMethodArgumentNotValidException2(MethodArgumentNotValidException ex){
        Map map = new HashMap();
        map.put("status", 500);
        map.put("msg", "请求异常");

        BindingResult result = ex.getBindingResult();
        List<String> msgList = new ArrayList<String>();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ObjectError error=errors.get(0);
            msgList.add(error.getDefaultMessage());
            String firstMsg=msgList.get(0);
            map.put("msg", msgList);
            return map ;
        }
        return map ;
    }*/
}

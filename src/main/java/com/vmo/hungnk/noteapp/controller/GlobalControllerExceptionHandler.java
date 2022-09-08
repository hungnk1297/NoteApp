package com.vmo.hungnk.noteapp.controller;

import static com.vmo.hungnk.noteapp.constant.Constant.TemplateConstant.ERROR;

import com.vmo.hungnk.noteapp.exception.CustomException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ModelAndView errorHandler(HttpServletRequest request, CustomException ce) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("status", ce.getApiErrorDetail().getHttpStatus());
        mav.addObject("message", ce.getApiErrorDetail().getMessage());
        mav.addObject("timestamp", ce.getApiErrorDetail().getTimestamp());
        mav.setViewName(ERROR);
        return mav;
    }
}

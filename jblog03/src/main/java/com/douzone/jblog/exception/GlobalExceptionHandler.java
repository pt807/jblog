package com.douzone.jblog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log Logger = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public String handlerException(Exception e, Model model) {
		// 1. 로깅(Logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));

		Logger.error(errors.toString());

		// 2. 사과페이지( 3.정상종료)
		
		model.addAttribute("msg", "죄송합니다. 잠시 후 다시 시도해 주세요");
		return "error/exception";
	}
	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	public String handle404(NoHandlerFoundException e, Model model) {
//		StringWriter errors = new StringWriter();
//		e.printStackTrace(new PrintWriter(errors));
//
//		Logger.error(errors.toString());
//		
//		model.addAttribute("msg", "죄송합니다. 해당 페이지를 찾을 수 없습니다");
//		return "error/exception";
//	}
}

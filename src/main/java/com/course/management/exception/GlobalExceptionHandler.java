package com.course.management.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Validation Errors");
        body.put("Errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException ex){
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
		
	}
	
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<?> handleDuplicateRecordException(DuplicateRecordException ex){
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(body);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(body);
		
	}
	
	
}

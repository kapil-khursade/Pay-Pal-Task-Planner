package com.taskPlanner.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.taskPlanner.DTO.MyErrorDetails;




@ControllerAdvice
public class GlobalExceptionHandler {

	// EXCEPTION HANDLER FOR INVET

		@ExceptionHandler(InviteException.class)
		public ResponseEntity<MyErrorDetails> InviteExceptionHandler(InviteException ce, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}

		// EXCEPTION HANDLER FOR MEMBER

		@ExceptionHandler(MemberException.class)
		public ResponseEntity<MyErrorDetails> MemberExceptionHandler(MemberException ie, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}

		// EXCEPTION HANDLER FOR PROJECT

		@ExceptionHandler(ProjectException.class)
		public ResponseEntity<MyErrorDetails> ProjectExceptionHandler(ProjectException ie, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}
		
		// EXCEPTION HANDLER FOR SPRINT

		@ExceptionHandler(SprintException.class)
		public ResponseEntity<MyErrorDetails> SprintExceptionHandler(SprintException ie, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}
		
		// EXCEPTION HANDLER FOR TASK

		@ExceptionHandler(TaskException.class)
		public ResponseEntity<MyErrorDetails> TaskExceptionHandler(TaskException ie, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}
		
		// EXCEPTION HANDLER FOR Team

		@ExceptionHandler(TeamException.class)
		public ResponseEntity<MyErrorDetails> TeamExceptionHandler(TeamException ie, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}

		// GENERIC EXCEPTION HANDLER

		@ExceptionHandler(Exception.class)
		public ResponseEntity<MyErrorDetails> genericExceptionHandler(Exception e, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(e.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}

		// IF NO EXCEPTION HANDLER IS FOUND

		@ExceptionHandler(NoHandlerFoundException.class)
		public ResponseEntity<MyErrorDetails> myExceptionHandler(NoHandlerFoundException nhfe, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(nhfe.getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}

		// IF METHOD ARGUEMENT IS NOT VALID

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException manve) {

			MyErrorDetails err = new MyErrorDetails();

			err.setTimestamp(LocalDateTime.now());
			err.setMessage(manve.getBindingResult().getFieldError().getDefaultMessage());
			err.setDetails("Validation error");

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}
		

		@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<MyErrorDetails> constrainVolationHandler(ConstraintViolationException ce, WebRequest req) {

			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getSQLException().getMessage());
			err.setDetails(req.getDescription(false));

			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}
		
}

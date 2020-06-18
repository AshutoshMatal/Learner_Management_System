package com.lms.exception;

public class DataNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		int errorCode;

		
		public DataNotFoundException(int errorCode, String message) {

			super(message);
			this.errorCode = errorCode;
		}



}

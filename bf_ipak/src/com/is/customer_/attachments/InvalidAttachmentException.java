package com.is.customer_.attachments;

public class InvalidAttachmentException extends RuntimeException {
	  public InvalidAttachmentException(String message){
          super(message);
      }

      @Override
      public  String toString(){
          return super.getMessage();
      }
}

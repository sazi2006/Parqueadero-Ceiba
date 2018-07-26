package co.com.ceiba.parqueadero.controller;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "mensaje")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	String msg;

	public Message(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}	
}

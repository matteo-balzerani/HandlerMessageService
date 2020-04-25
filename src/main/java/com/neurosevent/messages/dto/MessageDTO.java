package com.neurosevent.messages.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class MessageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String payload;

	private String publisher;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public MessageDTO(@NotBlank String payload, @NotBlank String publisher) {
		super();
		this.payload = payload;
		this.publisher = publisher;
	}

	
	
}

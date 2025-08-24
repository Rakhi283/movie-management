package com.Zymr.assessment.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {

	private static final long serialVersionUID = 1L;
	private int statusCode;
	private String status;
	private String message;
	private LocalDateTime timestamp;

}

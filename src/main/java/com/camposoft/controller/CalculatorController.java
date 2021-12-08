package com.camposoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camposoft.dto.CalculatorDTO;
import com.camposoft.dto.GenericRespuestaDTO;
import com.camposoft.enums.OperationEnum;
import com.camposoft.exceptions.GenericException;
import com.camposoft.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	@Autowired
	private CalculatorService calculatorService;

	@GetMapping("/operation")
	public <T> ResponseEntity<?> operation(@RequestParam(value = "operation") String operation) {
		OperationEnum operationEnum = OperationEnum.stream().filter(op -> op.toString().equals(operation.toUpperCase()))
				.findFirst().orElse(null);
		if (operationEnum != null) {
			CalculatorDTO calculatorDTO = calculatorService.operation(operation);
			return new ResponseEntity<CalculatorDTO>(calculatorDTO, HttpStatus.OK);
		} else {
			throw new GenericException(
					new GenericRespuestaDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
							"BE".concat(Integer.toString(HttpStatus.BAD_REQUEST.value())),
							"La operación envaida no hace parte de los valores válidos."),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/sendOperand")
	public ResponseEntity<GenericRespuestaDTO> sendOperand(@RequestBody CalculatorDTO calculatorDTO) {
		ResponseEntity<GenericRespuestaDTO> responseEntity = null;
		try {
			if (isNumberRegEx(calculatorDTO.getOperand())) {
				calculatorService.sendOperand(calculatorDTO);
				responseEntity = new ResponseEntity<GenericRespuestaDTO>(
						new GenericRespuestaDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
								Integer.toString(HttpStatus.OK.value()), "El operando Se agrego correctamente."),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new GenericException(
					new GenericRespuestaDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
							"BE".concat(Integer.toString(HttpStatus.BAD_REQUEST.value())),
							"El operando envaido no es un número."),
					HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	public static boolean isNumberRegEx(Integer number) {
		return number.toString().matches("^-?\\d*\\.{0,1}\\d+$");
	}
}

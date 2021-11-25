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
import com.camposoft.enums.OperationEnum;
import com.camposoft.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	@Autowired
	private CalculatorService calculatorService;

	@GetMapping("/operation")
	public <T> ResponseEntity<CalculatorDTO> operation(@RequestParam(value = "operation") String operation) {
		System.out.println();OperationEnum.stream().filter(op -> op.equals(operation));
		
		// if (operation. )
		CalculatorDTO calculatorDTO = calculatorService.operation(operation);
		 return new ResponseEntity<CalculatorDTO>(calculatorDTO, HttpStatus.OK);
	}

	@PostMapping("/sendOperand")
	public ResponseEntity<String> sendOperand(@RequestBody CalculatorDTO calculatorDTO) {
		calculatorService.sendOperand(calculatorDTO);
		return new ResponseEntity<String>("El operando se agrego correctamente...", HttpStatus.OK);
	}
}

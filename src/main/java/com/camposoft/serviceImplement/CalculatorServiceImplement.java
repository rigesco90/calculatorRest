package com.camposoft.serviceImplement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.camposoft.dto.CalculatorDTO;
import com.camposoft.dto.GenericRespuestaDTO;
import com.camposoft.exceptions.GenericException;
import com.camposoft.service.CalculatorService;

@Service
public class CalculatorServiceImplement implements CalculatorService {

	protected CalculatorDTO calculatorDTO = new CalculatorDTO();
	private static final Logger logger = LogManager.getLogger(CalculatorServiceImplement.class);

	@Override
	public CalculatorDTO operation(String operation) {
		switch (operation) {
		case "Sum":
			calculatorDTO.setResult(sum());
			calculatorDTO.setOperation(operation);
			return calculatorDTO;

		case "Multiply":
			calculatorDTO.setResult(multiply());
			calculatorDTO.setOperation(operation);
			return calculatorDTO;

		case "Substraction":
			calculatorDTO.setResult(substraction());
			calculatorDTO.setOperation(operation);
			return calculatorDTO;

		case "Division":
			calculatorDTO.setResult(division());
			calculatorDTO.setOperation(operation);
			return calculatorDTO;
		default:
			break;
		}
		return null;
	}

	@Override
	public CalculatorDTO sendOperand(CalculatorDTO calculatorDTO) {
		calculatorDTO.addOperand(calculatorDTO.getOperand());
		return calculatorDTO;
	}

	protected Integer sum() {
		try {
			return calculatorDTO.getOperands().stream().reduce(0, Integer::sum);
		} catch (Exception e) {
			logger.info("Error en Suma: " + e.getMessage());
			throw new GenericException(new GenericRespuestaDTO(500, "Business Exception", "BE500", "Error al sumar"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	protected Integer multiply() {
		try {
			return calculatorDTO.getOperands().stream().mapToInt(x -> x).reduce(1, Math::multiplyExact);
		} catch (Exception e) {
			logger.info("Error en Multiplicacion: " + e.getMessage());
			throw new GenericException(
					new GenericRespuestaDTO(500, "Business Exception", "BE500", "Error al Multiplicar"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	protected Integer division() {
		try {
			Integer result = calculatorDTO.getOperands().get(0);
			for (Integer i = 1; i < calculatorDTO.getOperands().size(); i++) {
				result = result / calculatorDTO.getOperands().get(i);
			}
			return result;

		} catch (Exception e) {
			logger.info("Error en Division: " + e.getMessage());
			throw new GenericException(new GenericRespuestaDTO(500, "Business Exception", "BE500", "Error al Dividir"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	protected Integer substraction() {
		try {
			Integer result = calculatorDTO.getOperands().get(0);
			for (Integer i = 1; i < calculatorDTO.getOperands().size(); i++) {
				result -= calculatorDTO.getOperands().get(i);
			}
			return result;
		} catch (Exception e) {
			logger.info("Error en Resta: " + e.getMessage());
			throw new GenericException(new GenericRespuestaDTO(500, "Business Exception", "BE500", "Error al Restar"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

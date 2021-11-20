package com.camposoft.serviceImplement;

import java.util.List;
import org.springframework.stereotype.Service;
import com.camposoft.service.CalculatorService;

@Service
public class CalculatorServiceImplement implements CalculatorService {

	@Override
	public Integer add(List<Integer> nums) {
		return nums.stream().reduce(0, Integer::sum);
	}

}

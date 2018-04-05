package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

//melhor arquitetura: isolar o código de validação e sempre que precisarmos, usá-lo.
//Spring tem uma classe chamada ValidationUtils, com alguns métodos que validam dados.
public class ProdutoValidation implements Validator {

	@Override
	public void validate(Object target, // objeto alvo da validação
			Errors errors // contém os erros da validação, gerenciado pelo Spring
	) {
		ValidationUtils.rejectIfEmpty(errors, // o objeto errors terá as informações se a validação falhou ou não
				"titulo", // nome do campo que iremos validar
				"field.required" // para informar ao Spring que aquele campo é obrigatório.
		);
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");

		Produto produto = (Produto) target; // cast

		if (produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required"); // usaremos o objeto errors para rejeitar o valor
		}
	}

	@Override
	// indica a qual classe a validação dará suporte (produto).
	// verificar se o objeto recebido para a validação tem uma assinatura da classe
	// Produto. Com isso o Spring pode verificar se o objeto é uma instância daquela
	// classe ou não.
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

}

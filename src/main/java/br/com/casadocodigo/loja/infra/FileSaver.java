package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// enviar o arquivo e deixa-lo hospedado no servidor. 
@Component // classe reconhecida pelo Spring para que ele consiga fazer os injects de
			// FileSaver corretamente
public class FileSaver {

	@Autowired // para que o Spring faça o inject desse atributo.
	private HttpServletRequest request;// partir deste objeto, conseguimos extrair o contexto atual em que o usuário se
										// encontra e então conseguir o caminho absoluto desse diretório em nosso
										// servidor.

	// fará a transferência do arquivo e retornará o caminho onde o arquivo foi
	// salvo.
	public String write(String baseFolder, // local onde o arquivo será salvo
			MultipartFile file) { // arquivo em si

		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			String path = realPath + "/" + file.getOriginalFilename(); // monta o caminho do arquivo
			System.out.println(path);
			file.transferTo(new File(path)); // metodo responsavel por transferir o arquiv p/ o servidor
			return baseFolder + "/" + file.getOriginalFilename();// retornar o caminho relativo ao nosso sistema. em vez
																	// de guardamos o caminho completo até o arquivo,
																	// armazenamos apenas uma parte. Isso fará com que
																	// fique mais simples a exibição das imagens
																	// posteriormente.

		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}

	}

}

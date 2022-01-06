package ia.development.dio.bancodigital.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

public interface Conta {
	
	BigDecimal getSaldo();

	boolean depositar(BigDecimal valor) throws IllegalArgumentException;

	boolean sacar(BigDecimal valor) throws IllegalArgumentException;

	boolean transferir(BigDecimal valor, Conta destino) throws IllegalArgumentException;
	
	Cliente getCliente();

	void imprimirExtrato(OutputStream saida) throws IOException;
}

package ia.development.dio.bancodigital;

import java.io.IOException;
import java.math.BigDecimal;

import ia.development.dio.bancodigital.model.Banco;
import ia.development.dio.bancodigital.model.Cliente;
import ia.development.dio.bancodigital.model.Conta;

public class BancoDigitalDioApplication {

	public static void main(String[] args) throws IOException {
		Banco banco = new Banco(1);

		Cliente cliente = Cliente.builder().nome("Fulano de tal").build();

		Conta poupanca = banco.novaContaPoupanca(cliente);
		Conta corrente = banco.novaContaCorrente(cliente);
		// saldo inicial
		corrente.imprimirExtrato(System.out);
		poupanca.imprimirExtrato(System.out);

		corrente.depositar(BigDecimal.valueOf(100d));
		corrente.transferir(corrente.getSaldo(), poupanca);
		// saldo após a transferência
		corrente.imprimirExtrato(System.out);
		poupanca.imprimirExtrato(System.out);

		// sacando
		poupanca.sacar(BigDecimal.valueOf(50d));
		// extrado poupança
		poupanca.imprimirExtrato(System.out);
		corrente.imprimirExtrato(System.out);
	}

}

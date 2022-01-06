package ia.development.dio.bancodigital.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

public class Banco {

	@Getter
	private final int agencia;
	private static int contadorConta = 0;
	private Set<Conta> contas;

	public Banco(int agencia) {
		this.agencia = agencia;
		this.contas = new HashSet<>();
	}

	/**
	 * adiciona uma conta no banco
	 * 
	 * @param conta
	 * @return
	 */
	public boolean adicionarConta(Conta conta) {
		return this.contas.add(conta);
	}

	/**
	 * lista as contas do banco
	 * 
	 * @return
	 */

	public Set<Conta> listarContas() {
		return contas.stream().collect(Collectors.toUnmodifiableSet());
	}

	/**
	 * lista os clientes do banco
	 * 
	 * @return
	 */
	public Set<Cliente> listarClientes() {
		return contas.stream().map(c -> c.getCliente()).collect(Collectors.toUnmodifiableSet());
	}

	/**
	 * nova poupança
	 * 
	 * @param cliente
	 * @return
	 */
	public Conta novaContaPoupanca(Cliente cliente) {
		Conta c = ContaPoupanca.builder()
				// saldo inicial
				.saldo(BigDecimal.ZERO)
				// cliente
				.cliente(cliente)
				// agencia
				.agencia(agencia)
				// numero da conta poupança
				.numero(++contadorConta).build();
		this.adicionarConta(c);
		return c;
	}

	/**
	 * nova conta corrente
	 * 
	 * @param cliente
	 * @return
	 */
	public Conta novaContaCorrente(Cliente cliente) {
		Conta c = ContaCorrente.builder()
				// saldo inicial
				.saldo(BigDecimal.ZERO)
				// cliente
				.cliente(cliente)
				// agencia
				.agencia(agencia)
				// numero de conta corrente
				.numero(++contadorConta).build();
		this.adicionarConta(c);
		return c;
	}
}

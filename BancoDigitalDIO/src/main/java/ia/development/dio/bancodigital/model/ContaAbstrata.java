package ia.development.dio.bancodigital.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ContaAbstrata implements Conta {

	private Cliente cliente;

	private int numero;
	private int agencia;
	private BigDecimal saldo;

	@Override
	public boolean depositar(BigDecimal valor) throws IllegalArgumentException {
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Valor de depósito deve ser maior que zero");
		}
		this.saldo = this.saldo.add(valor);
		return true;
	}

	@Override
	public boolean sacar(BigDecimal valor) throws IllegalArgumentException {
		if (valor.compareTo(this.saldo) > 0) {
			throw new IllegalArgumentException("Saldo insuficiente");
		}
		this.saldo = this.saldo.subtract(valor);
		return true;
	}

	@Override
	public boolean transferir(BigDecimal valor, Conta destino) throws IllegalArgumentException {
		sacar(valor);
		destino.depositar(valor);
		return true;
	}

	@Override
	public void imprimirExtrato(OutputStream saida) throws IOException {
		saida.write(String.format("Titular: %s\nAgência: %d\nNúmero: %d\nSaldo: %s\n\n", this.cliente.getNome(),
				this.agencia, this.numero, this.getSaldo().toString()).getBytes());
	}

}

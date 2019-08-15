/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import SaldoInsuficienteException.SaldoInsuficienteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Aline
 */
public class BancoNovo {

    private List<Conta> contas;

    public BancoNovo() {
        contas = new LinkedList();
    }

    /**
     * Iniciar uma nova conta
     */
    public void abrirContaCorrente(String titular) {
        Conta novaConta = new ContaCorrente(titular);
        contas.add(novaConta);

    }

    /**
     * Iniciar uma nova conta
     */
    public void abrirContaPoupanca(String titular) {
        Conta novaConta = new ContaPoupanca(titular);
        contas.add(novaConta);

    }

    public void abrirContaCorrente(String titular, double saldo, double limite) {
        Conta novaConta = new ContaCorrente(titular);
        novaConta.deposita(saldo);
        novaConta.setLimite(limite);
        contas.add(novaConta);
    }

    public void abrirContaPoupanca(String titular, double saldo, double limite) {
        Conta novaConta = new ContaPoupanca(titular);
        novaConta.deposita(saldo);
        novaConta.setLimite(limite);
        contas.add(novaConta);
    }

    public Conta getConta(int numero) {
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero() == numero) {
                return contas.get(i);
            }
        }
        return null;
    }

    public boolean sacar(int numero, double valor) throws IllegalArgumentException, SaldoInsuficienteException {
        try {
            Conta conta = getConta(numero);
            if (conta != null) {
                return conta.saca(valor);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Número de conta inexistente");
            return false;
        }
        return false;
    }

    public boolean depositar(int numero, double valor) throws IllegalArgumentException {
        try {
            Conta conta = getConta(numero);
            if (conta != null) {
                //completar
                return conta.deposita(valor);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Número de conta inexistente");
            return false;
        }
        return false;
    }

    public double consultarSaldo(int numero) throws Exception {
        Conta conta = getConta(numero);
        if (conta != null) {
            return conta.getSaldo();
        }
        throw new Exception("Conta inexistente");
    }

    public void transferir(int numeroDebito, int numeroCredito, double valor) throws Exception {
        Conta contaCredito = getConta(numeroCredito); // depositar valor
        Conta contaDebito = getConta(numeroDebito); // sacar valor
        if (contaDebito == null) {
            throw new Exception("Conta Debito inexistente");
        }
        if (contaCredito == null) {
            throw new Exception("Conta Credito inexistente");
        }
        if (contaDebito == contaCredito) {
            throw new Exception("Conta Debito e Credito iguais");
        }
        if (contaDebito.saca(valor)) {
            contaCredito.deposita(valor);
        } else {
            throw new Exception("Saldo indisponivel");

        }

    }

    public String listarContas() {
        String dados = "";
        for (Conta conta : contas) {
            dados += conta.toString();
        }
        return dados;

    }

    public String listarContasCorrente() {
        String dados = "";
        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente) {
                dados += conta.toString();
            }
        }
        return dados;

    }

    public List<Conta> listarContasValor() {
        List<Conta> aux = new LinkedList<>();
        aux.addAll(contas);
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = aux.get(i);
            for (int j = 0; j < contas.size(); j++) {
                if (contas.get(j).getSaldo() > contas.get(j + 1).getSaldo()) {
                    aux.set(j + 1, aux.get(j));
                    aux.set(j, conta);
                }
            }
        }

        return aux;
    }

    public String listarContasPoupanca() {
        String dados = "";
        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                dados += conta.toString();
            }
        }
        return dados;

    }

}

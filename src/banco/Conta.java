/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import SaldoInsuficienteException.SaldoInsuficienteException;

/**
 *
 * @author Aline
 */
public abstract class Conta {
    protected static int numeroGeral = 10000;
    protected int numero;
    protected String titular;
    protected double saldo;
    protected double limite;
    
    public Conta(String titular){
       this.numero = numeroGeral;
       numeroGeral++;
       this.titular = titular;
    }
    
    public String getTitular(){
        return titular;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public double getLimite(){
        return limite;
    }

    
    public void setLimite(double novoLimite){
        limite = novoLimite;
    }
    
    public boolean saca(double valor) throws SaldoInsuficienteException {
        if (saldo + limite >= valor) {
            saldo = saldo - valor;
            return true;
        } else if (valor < 0){
            throw new IllegalArgumentException ("Valor Negativo");
        } else if (saldo < valor){
            throw new SaldoInsuficienteException("Saldo Insuficiente");
        }
        return false;
    }
   
    @Override
    public String toString(){
        String dado = "Conta\n";
        dado += "Numero: "+ getNumero()+"\n";
        dado += "Titular: "+ getTitular()+"\n";
        dado += "Saldo: "+ saldo+"\n";
        dado += "Limite: "+limite+"\n";
        return dado;
    }
    
    public boolean deposita(double valor) {
        try {
            if (valor > 0) {
                saldo = saldo + valor;
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Valor negativo");
        }
        return false;
    }
    
    public void setTitular(String novoNome){
        titular = novoNome;
    }
    
    public void atualiza(double taxa){
        saldo = saldo * taxa;
    
    }
    
    
    
}

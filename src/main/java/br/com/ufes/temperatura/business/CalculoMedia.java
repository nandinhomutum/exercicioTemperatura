/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufes.temperatura.business;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author nandi
 */
public class CalculoMedia {
    
    private CalculoMedia() {
    }
    
    public static CalculoMedia getInstance() {
        return CalculoMediaHolder.INSTANCE;
    }
    
    private static class CalculoMediaHolder {

        private static final CalculoMedia INSTANCE = new CalculoMedia();
    }
    
    public BigDecimal calculaMedia(ArrayList<BigDecimal> numeros){
        BigDecimal total = BigDecimal.ZERO;
        for(BigDecimal numero : numeros){
            total = total.add(numero);
        }
        return total.divide(new BigDecimal(numeros.size()));
    }
    
}

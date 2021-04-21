/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufes.temperatura.presente;

import br.com.ufes.temperatura.business.CalculoMedia;
import br.com.ufes.temperatura.view.TelaPrincipalView;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TelaPrincipalPreseter {
    private TelaPrincipalView tela;

    public TelaPrincipalPreseter() {
        this.tela = new TelaPrincipalView(this);
        this.tela.setVisible(true);
          
    }
    
    public void apertouBotao(){
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTbRegistro().getModel();
        
        tabela.addRow(new Object[] {
            this.tela.getjTData().getText(),
            this.tela.getjTTemperatura().getText(),
            this.tela.getjTHumidade().getText(),
            this.tela.getjTPressao().getText(),
        });
        
        
        
        this.tela.getjLabelHumidadeU().setText(this.tela.getjTHumidade().getText());
        this.tela.getjLabelPressaoU().setText(this.tela.getjTPressao().getText());
        this.tela.getjLabelTempU().setText(this.tela.getjTTemperatura().getText());
        this.tela.getjLabelDataU().setText(this.tela.getjTData().getText());
        this.tela.getjTbRegistro().setModel(tabela);
    }
    
    public void removerTabela(){
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTbRegistro().getModel();
        BigDecimal teste = new BigDecimal(this.tela.getjTbRegistro().getSelectedRow());
       if(null != teste && !(teste.compareTo(new BigDecimal(-1)) == 0))
       {
           tabela.removeRow(this.tela.getjTbRegistro().getSelectedRow());
       }
            
        
        
        this.tela.getjTbRegistro().setModel(tabela);
    }
    
    public void prencheMedia(){
        JTable tabela = this.tela.getjTbRegistro();
        LocalDate data = LocalDate.now();
  
        //temperatura
        ArrayList<BigDecimal> temperaturas = new ArrayList<>();
        for(int i =0; i< tabela.getRowCount(); i++){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataRegistro = LocalDate.parse(tabela.getValueAt(i, 0).toString(), format);

            if (tela.getjCBPeriodo().getSelectedItem().toString().equals("DIA")){
                if(dataRegistro.equals(data)){
                   temperaturas.add(new BigDecimal(tabela.getValueAt( i,1).toString())); 
                }
            } else if (tela.getjCBPeriodo().getSelectedItem().toString().equals("MENSAL")){
                    if(dataRegistro.equals(data.getMonth())){
                   temperaturas.add(new BigDecimal(tabela.getValueAt( i,1).toString())); 
                }
              }else if (tela.getjCBPeriodo().getSelectedItem().toString().equals("SEMANAL")){
                    if(dataRegistro.equals(data)){
                   temperaturas.add(new BigDecimal(tabela.getValueAt( i,1).toString())); 
                }
              }
            
            
            }
        BigDecimal mediaTemp = CalculoMedia.getInstance().calculaMedia(temperaturas);
        
        //Pressao
        ArrayList<BigDecimal> pressoes = new ArrayList<>();
        for(int i =0; i< tabela.getRowCount(); i++){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataRegistro = LocalDate.parse(tabela.getValueAt(i, 0).toString(), format);
             if (tela.getjCBPeriodo().getSelectedItem().toString().equals("DIA")){
                if(dataRegistro.equals(data)){
            pressoes.add(new BigDecimal(tabela.getValueAt(i,2).toString()));
            }
            } else if (tela.getjCBPeriodo().getSelectedItem().toString().equals("MENSAL")){
                if(dataRegistro.equals(data.getMonth())){
            pressoes.add(new BigDecimal(tabela.getValueAt(i,2).toString()));
            }
            } else if (tela.getjCBPeriodo().getSelectedItem().toString().equals("SEMANAL")){
                if(dataRegistro.equals(data)){
            pressoes.add(new BigDecimal(tabela.getValueAt(i,2).toString()));
            }
            }
        }
        BigDecimal mediaPressoes = CalculoMedia.getInstance().calculaMedia(pressoes);
        
        //humidade
        
        ArrayList<BigDecimal> humidades = new ArrayList<>();
        for(int i =0; i< tabela.getRowCount(); i++){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataRegistro = LocalDate.parse(tabela.getValueAt(i, 0).toString(), format);
           if (tela.getjCBPeriodo().getSelectedItem().toString().equals("DIA")){
             if(dataRegistro.equals(data)){
            humidades.add(new BigDecimal(tabela.getValueAt( i,3).toString()));
            }
           } else if (tela.getjCBPeriodo().getSelectedItem().toString().equals("MENSAL")){
             if(dataRegistro.equals(data.getMonth())){
            humidades.add(new BigDecimal(tabela.getValueAt( i,3).toString()));
            }
           } else if (tela.getjCBPeriodo().getSelectedItem().toString().equals("MENSAL")){
             if(dataRegistro.equals(data)){
            humidades.add(new BigDecimal(tabela.getValueAt( i,3).toString()));
            }
           }
        }
        BigDecimal mediaHumidade = CalculoMedia.getInstance().calculaMedia(humidades);
        
        tela.getjLabelHumidade().setText(mediaHumidade.toString());
        tela.getjLabelPressao().setText(mediaPressoes.toString());
        tela.getjLabelTemp().setText(mediaTemp.toString());
        tela.getjLabelRegistro().setText(new BigDecimal(tabela.getRowCount()).toString());
                
    }
    
}

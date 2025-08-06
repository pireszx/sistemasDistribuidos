/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author laboratorio
 */
public class Movimenta {
    public static void cima(JButton botao) {
        if(botao.getY()>0){
            botao.setBounds(botao.getX(), botao.getY() -10, botao.getWidth(), botao.getHeight());
        }
    }
    
    public static void baixo(JButton botao, JFrame frame) {
        int limiteInferior = frame.getContentPane().getHeight() - botao.getHeight();
        if (botao.getY){
            
        }
    }
    
    public static void esquerda(JButton botao, JFrame frame) {
        
    }
    
    public static void direita(JButton botao, JFrame frame) {
        
    }
}

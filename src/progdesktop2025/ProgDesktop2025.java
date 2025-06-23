/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progdesktop2025;

import javax.swing.JFrame;
import progdesktop2025.view.MainView;

/**
 *
 * @author jogos
 */
public class ProgDesktop2025 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(
                System.getProperty("java.version")
        );
        System.out.println(
                System.getProperty("java.runtime.version")
        );
        System.out.println(
                System.getProperty("java.vendor")
        );
        
        MainView mainView = new MainView();
        
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainView.setVisible(true);
    }
    
}

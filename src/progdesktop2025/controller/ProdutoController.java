/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progdesktop2025.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import progdesktop2025.model.ProdutoModel;
import util.Util;

/**
 *
 * @author jogos
 */
public class ProdutoController {
    
    public static int criarID(TableModel model) {
        int max = 0;

        for (int row = 0; row < model.getRowCount(); row++) {
            Integer value = Util.tryParseInt(
                    model.getValueAt(row, 2).toString()
            );

            if (value == null) {
                continue;
            }

            max = Math.max(value, max);
        }

        return max + 1;
    }

    public static TableModel importar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar");

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File file = fileChooser.getSelectedFile();

        ArrayList<ProdutoModel> lista = null;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {

            Object obj = ois.readObject();

            lista = (ArrayList<ProdutoModel>) obj;

            if (lista == null) {
                return null;
            }

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nome");
            model.addColumn("Preço");
            model.addColumn("ID");

            for (ProdutoModel produto : lista) {
                model.addRow(new Object[]{
                    produto.getNome(),
                    produto.getPreco(),
                    produto.getId()
                });
            }

            return model;

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo importação: " + e.getMessage(),
                    "FileNotFoundException",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo importação: " + e.getMessage(),
                    "IOException",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo importação: " + e.getMessage(),
                    "ClassNotFoundException",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo importação: " + e.getMessage(),
                    "ClassCastException",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo importação: " + e.getMessage(),
                    "Exception",
                    JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public static void exportar(TableModel model) {
        ArrayList<ProdutoModel> lista = new ArrayList<>();

        for (int row = 0; row < model.getRowCount(); row++) {
            ProdutoModel produto = new ProdutoModel(
                    model.getValueAt(row, 0).toString(),
                    (Double) Util.tryParseDouble(
                            model.getValueAt(row, 1).toString().trim()
                    ),
                    (Integer) Util.tryParseInt(
                            model.getValueAt(row, 2).toString().trim()
                    )
            );

            lista.add(produto);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar");

        // default
        fileChooser.setSelectedFile(new File("productData.bin"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToSave = fileChooser.getSelectedFile();

        ObjectOutputStream oos = null;

        try {
            FileOutputStream fos = new FileOutputStream(fileToSave);
            oos = new ObjectOutputStream(fos);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo exportação: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        try {
            oos.writeObject(lista);
            oos.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro fazendo exportação: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

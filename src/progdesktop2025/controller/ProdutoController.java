/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progdesktop2025.controller;

import javax.swing.table.*;
import java.sql.*;
import java.util.List;
import progdesktop2025.model.ProdutoModel;
import util.Util;

/**
 *
 * @author jogos
 */
public class ProdutoController extends ControllerBase<ProdutoModel> {
    
    // ProdutoController tem lógica diferente em "preço",
    // Já que no view é uma string

    @Override
    protected String getTableName() {
        return "produtos";
    }

    @Override
    protected String getCreateTableSql() {
        return "CREATE TABLE IF NOT EXISTS produtos ("
                + "nome TEXT NOT NULL, "
                + "preco REAL NOT NULL, " // real = double
                + "id INTEGER NOT NULL UNIQUE PRIMARY KEY"
                + ")";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO produtos (nome, preco, id) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(
            PreparedStatement preparedStatement,
            ProdutoModel produto
    ) throws SQLException {
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setDouble(2, produto.getPreco());
        preparedStatement.setInt(3, produto.getId());
    }

    @Override
    protected ProdutoModel mapTableRowToData(TableModel model, int row) {
        String nome = (String) model.getValueAt(row, 0);
        String precoValor = (String) model.getValueAt(row, 1);
        double preco = (Double) Double.parseDouble(precoValor);
        int id = (Integer) model.getValueAt(row, 2);

        return new ProdutoModel(nome, preco, id);
    }

    @Override
    protected ProdutoModel mapResultSetToData(ResultSet resultSet) throws SQLException {
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(resultSet.getString("nome"));
        produto.setPreco(resultSet.getDouble("preco"));
        produto.setId(resultSet.getInt("id"));
        
        return produto;
    }

    @Override
    protected TableModel createTableModel(List<ProdutoModel> lista) {
        String[] columnNames = {
            "Nome",
            "Preço",
            "ID"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (ProdutoModel produto : lista) {
            model.addRow(new Object[]{
                produto.getNome(),
                "" + produto.getPreco(),
                produto.getId()
            });
        }

        return model;
    }
    
    public int criarID(
            TableModel model
    ) {
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
}

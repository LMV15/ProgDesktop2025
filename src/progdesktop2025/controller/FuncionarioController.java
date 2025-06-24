/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progdesktop2025.controller;

import javax.swing.table.*;
import java.sql.*;
import java.util.List;
import progdesktop2025.model.FuncionarioModel;
import util.Util;

/**
 *
 * @author jogos
 */
public class FuncionarioController extends ControllerBase<FuncionarioModel> {

    @Override
    protected String getTableName() {
        return "funcionarios";
    }

    @Override
    protected String getCreateTableSql() {
        return "CREATE TABLE IF NOT EXISTS funcionarios ("
                + "nome TEXT NOT NULL, "
                + "cpf TEXT NOT NULL UNIQUE, "
                + "id INTEGER NOT NULL UNIQUE PRIMARY KEY"
                + ")";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO funcionarios (nome, cpf, id) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(
            PreparedStatement preparedStatement,
            FuncionarioModel funcionario
    ) throws SQLException {
        preparedStatement.setString(1, funcionario.getNome());
        preparedStatement.setString(2, funcionario.getCpf());
        preparedStatement.setInt(3, funcionario.getId());
    }

    @Override
    protected FuncionarioModel mapTableRowToData(TableModel model, int row) {
        String nome = (String) model.getValueAt(row, 0);
        String cpf = (String) model.getValueAt(row, 1);
        int id = (Integer) model.getValueAt(row, 2);

        return new FuncionarioModel(nome, cpf, id);
    }

    @Override
    protected FuncionarioModel mapResultSetToData(ResultSet resultSet) throws SQLException {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome(resultSet.getString("nome"));
        funcionario.setCpf(resultSet.getString("cpf"));
        funcionario.setId(resultSet.getInt("id"));
        
        return funcionario;
    }

    @Override
    protected TableModel createTableModel(List<FuncionarioModel> lista) {
        String[] columnNames = {
            "Nome",
            "Cpf",
            "ID"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (FuncionarioModel funcionario : lista) {
            model.addRow(new Object[]{
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getId()
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

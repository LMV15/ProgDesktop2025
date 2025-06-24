/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progdesktop2025.controller;

import javax.swing.table.*;
import java.sql.*;
import java.util.List;
import progdesktop2025.model.ClienteModel;

/**
 *
 * @author jogos
 */
public class ClienteController extends ControllerBase<ClienteModel> {

    @Override
    protected String getTableName() {
        return "clientes";
    }

    @Override
    protected String getCreateTableSql() {
        return "CREATE TABLE IF NOT EXISTS clientes ("
                + "nome TEXT NOT NULL, "
                + "cpf TEXT NOT NULL UNIQUE PRIMARY KEY"
                + ")";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(
            PreparedStatement preparedStatement,
            ClienteModel cliente
    ) throws SQLException {
        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getCpf());
    }

    @Override
    protected ClienteModel mapTableRowToData(TableModel model, int row) {
        String nome = (String) model.getValueAt(row, 0);
        String cpf = (String) model.getValueAt(row, 1);

        return new ClienteModel(nome, cpf);
    }

    @Override
    protected ClienteModel mapResultSetToData(ResultSet resultSet) throws SQLException {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(resultSet.getString("nome"));
        cliente.setCpf(resultSet.getString("cpf"));

        return cliente;
    }

    @Override
    protected TableModel createTableModel(List<ClienteModel> lista) {
        String[] columnNames = {
            "Nome",
            "Cpf"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (ClienteModel cliente : lista) {
            model.addRow(new Object[]{
                cliente.getNome(),
                cliente.getCpf()
            });
        }

        return model;
    }
}

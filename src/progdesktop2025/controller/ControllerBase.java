/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progdesktop2025.controller;

import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jogos
 */
public abstract class ControllerBase<T> {

    protected String dbPath = "mydatabase.db";

    public ControllerBase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sql = getCreateTableSql();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Erro inicializando banco de dados: " + e.getMessage());

        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);

    }

    public void exportar(TableModel model) throws SQLException {
        try (Connection connection = getConnection()) {

            // Deleta data para a exportação
            try (Statement statement = connection.createStatement()) {
                statement.execute("DELETE FROM " + getTableName());
            }

            String sql = getInsertSql();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int row = 0; row < model.getRowCount(); row++) {
                    T data = mapTableRowToData(model, row);

                    setInsertParameters(preparedStatement, data);

                    preparedStatement.addBatch();

                }

                preparedStatement.executeBatch();

            }
        } catch (SQLException e) {
            throw e;

        }
    }

    public TableModel importar() throws SQLException {
        List<T> lista = new ArrayList<>();

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + getTableName())
        ) {
            while (resultSet.next()) {
                T data = mapResultSetToData(resultSet);
                lista.add(data);
                
            }
        } catch (SQLException e) {
            throw e;
            
        }
        return createTableModel(lista);
    }

    // Cada controller implementa
    protected abstract String getTableName();

    protected abstract String getCreateTableSql();

    protected abstract String getInsertSql();

    protected abstract void setInsertParameters(PreparedStatement preparedStatement, T data) throws SQLException;

    protected abstract T mapTableRowToData(TableModel model, int row);

    protected abstract T mapResultSetToData(ResultSet resultSet) throws SQLException;

    protected abstract TableModel createTableModel(List<T> lista);
}

package com.exercicio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exercicio.model.Cachorro;

public class CachorroDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/exercicio2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public CachorroDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void inserir(Cachorro cachorro) {
        String sql = "INSERT INTO cachorro (nome, raca, data_nascimento) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cachorro.getNome());
            stmt.setString(2, cachorro.getRaca());
            stmt.setDate(3, Date.valueOf(cachorro.getDataNascimento()));
            stmt.executeUpdate();

            System.out.println("Cachorro inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cachorro: " + e.getMessage());
        }
    }

    public void atualizar(Cachorro cachorro) {
        String sql = "UPDATE cachorro SET nome = ?, raca = ?, data_nascimento = ? WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cachorro.getNome());
            stmt.setString(2, cachorro.getRaca());
            stmt.setDate(3, Date.valueOf(cachorro.getDataNascimento()));
            stmt.setInt(4, cachorro.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cachorro atualizado com sucesso.");
            } else {
                System.out.println("Nenhum cachorro encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cachorro: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM cachorro WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cachorro excluído com sucesso.");
            } else {
                System.out.println("Nenhum cachorro encontrado com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cachorro: " + e.getMessage());
        }
    }

    public List<Cachorro> listar() {
        List<Cachorro> lista = new ArrayList<>();
        String sql = "SELECT * FROM cachorro ORDER BY id";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String raca = rs.getString("raca");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                Cachorro cachorro = new Cachorro(id, nome, raca, dataNascimento);
                lista.add(cachorro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar cachorros: " + e.getMessage());
        }
        return lista;
    }
}

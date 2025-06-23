/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progdesktop2025.model;

import java.io.Serializable;

/**
 *
 * @author jogos
 */
public class ProdutoModel implements Serializable {
    
    private static final long serialVersionUID = 3333_1L;

    private String nome;
    private double preco;
    private int id;

    public ProdutoModel() {
        this.nome = "";
        this.preco = 0;
        this.id = 0;
    }

    public ProdutoModel(
            String nome,
            double preco,
            int id
    ) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

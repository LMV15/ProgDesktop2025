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
public class FuncionarioModel implements Serializable {
    
    private static final long serialVersionUID = 2222_1L;

    private String nome;
    private String cpf;
    private int id;

    public FuncionarioModel() {
        this.nome = "";
        this.cpf = "";
        this.id = 0;
    }

    public FuncionarioModel(
            String nome,
            String cpf,
            int id
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

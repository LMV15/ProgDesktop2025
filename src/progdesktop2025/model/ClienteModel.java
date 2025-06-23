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
public class ClienteModel implements Serializable {

    private static final long serialVersionUID = 1111_1L;
    
    private String nome;
    private String cpf;

    public ClienteModel() {
        this.nome = "";
        this.cpf = "";
    }

    public ClienteModel(
            String nome,
            String cpf
    ) {
        this.nome = nome;
        this.cpf = cpf;
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
}

package br.com.caelum.recomendadordecarros.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by matheusbrandino on 2/8/17.
 */

@Entity
public class Carro implements Serializable {

    @Id
    private Long id;
    @NotNull
    private String modelo;
    @NotNull
    private String marca;

    private String cor;
    private Integer ano;

    public static final long serialVersionUID = 536871008;

    @Generated(hash = 1511236404)
    public Carro(Long id, @NotNull String modelo, @NotNull String marca, String cor,
            Integer ano) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.ano = ano;
    }

    @Generated(hash = 45579899)
    public Carro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}

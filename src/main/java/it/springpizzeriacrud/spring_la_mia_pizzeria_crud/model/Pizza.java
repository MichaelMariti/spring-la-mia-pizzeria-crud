package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descrizione;
    private String photoUrl;
    private double prezzo;

    // Costruttori
    public Pizza(){
    }

    public Pizza(String nome, String descrizione, String photoUrl, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.photoUrl = photoUrl;
        this.prezzo = prezzo;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public double getPrezzo() {
        return prezzo;
    }


    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }


    
}

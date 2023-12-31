package com.example.myapplication;

class Shape
{
    private String id;
    private String adresse;
    private String ville;
    private String prix_valeur;
    private String prix_nom;

    public Shape(String id, String adresse, String ville, String prix_valeur, String prix_nom) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.prix_valeur = prix_valeur;
        this.prix_nom = prix_nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPrix_valeur() {
        return prix_valeur;
    }

    public void setPrix_valeur(String prix_valeur) {
        this.prix_valeur = prix_valeur;
    }

    public String getPrix_nom() {
        return prix_nom;
    }

    public void setPrix_nom(String prix_nom) {
        this.prix_nom = prix_nom;
    }
}

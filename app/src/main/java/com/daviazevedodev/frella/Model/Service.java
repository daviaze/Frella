package com.daviazevedodev.frella.Model;

import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Service {

    private String name_service;
    private String name_person;
    private String portifolio;
    private String telephone;
    private String description;
    private String area;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    private String id_user;

    public Service () {}

    public Service(String name_service, String name_person, String portifolio, String telephone, String description, String area, String id_user){
        this.name_service = name_service;
        this.name_person = name_person;
        this.portifolio = portifolio;
        this.telephone = telephone;
        this.description = description;
        this.area = area;
        this.id_user=id_user;

    }


    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public String getName_person() {
        return name_person;
    }

    public void setName_person(String name_person) {
        this.name_person = name_person;
    }

    public String getPortifolio() {
        return portifolio;
    }

    public void setPortifolio(String portifolio) {
        this.portifolio = portifolio;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public static void remover(Service service){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        FirebaseDatabase.getInstance().getReference().child("Services").removeValue();    }

}

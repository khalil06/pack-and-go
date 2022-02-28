/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Activite;
import models.Ticket;
import models.User;

import java.util.List;

/**
 *
 * @author Sami Brahim BEN FADHL
 */
public interface IService <T>{
    public void ajouterActivité(T t);
    public void ajouterTicket(Ticket t, Activite t1, User t2);
    //spublic T getById(int id);
    public List<T> getAll();
    public void modifier(T t);

    void modifier(Activite a, Ticket t, User u);

    public void supprimer(T t);


}

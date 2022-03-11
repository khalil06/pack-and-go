package services;

import models.Ticket;




import models.Activite;
import models.User;
import utils.DataSource;
import utils.JavaMail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceTicket implements IServiceS<Ticket> {
    JavaMail jvm = new JavaMail();
    Connection cnx = DataSource.getInstance().getCnx();
    public void ajouterTicket( Activite activite, User user) {

        try {

            String req = "INSERT INTO `ticket`( `id_activite`,`id_user` ) VALUES ('"+activite.getId_activite()+"','"+ user.getId_user()+"')  ";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            jvm.sendTextMail("Ticket", "samibenfadhl@gmail.com", "Votre Reservation est bien effectuée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }


    @Override
    public void ajouterActivite(Ticket ticket) {

    }



    @Override
    public List<Ticket> getAll() {
        List<Ticket> list = new ArrayList<>();
        try {
            String req = "Select * from ticket";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Ticket ticket = new Ticket(rs.getInt(1));
                list.add(ticket);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void modifier(Ticket ticket) {

    }

    @Override
    public void modifier(Activite a, Ticket t, User u) {
        try {


            String req= "update `ticket` set `id_activite`=?, `id_user`=? where `num_ticket`=?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(3, t.getNum_ticket());
            ps.setLong(2, u.getId_user());

            ps.setInt(1, a.getId_activite());



            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Ticket ticket) {
        try {

            String req = "DELETE FROM `ticket` where `id_activite` ='" + ticket.getNum_ticket()  + "' ";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }


}

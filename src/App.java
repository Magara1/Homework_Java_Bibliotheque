import bibliotheque.models.Book;

import java.sql.*;

public class App {

    public static Connection dbConnexion;
    public static void main(String[] args) {

        ConnexionBD connect = new ConnexionBD();
        dbConnexion = connect.connect();

        //Insertion de livres

        Book book = new Book();
        book.setTitle("la voie de ma rue");
        book.setAuthor(" Amadou");
        book.setIsbn("book00010002");
        insertBook(book);
    }

    //mise en place des methodes pour manipuler les donnees

    public static void insertBook(Book book){
       final String SQL_INSERT_BOOK ="INSERT INTO book(title,author,isbn)" + "VALUES(? , ? , ?)";

       try {
           PreparedStatement statement = dbConnexion.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);

           //usage de getters et setters

           statement.setString(1, book.getTitle());
           statement.setString(2, book.getAuthor());
           statement.setString(3, book.getIsbn());

           //verification de l'insertion de donnees

           int nbreLigneAffecte = statement.executeUpdate();

           if (nbreLigneAffecte != 0){
               try{
                   ResultSet rs = statement.getGeneratedKeys();

                   if (rs.next()){
                       Long id = rs.getLong(1);
                       System.out.println("Book inséré avec succès");
                       System.out.println("ID GENERE = "+id);
                   }

               } catch (SQLException ex){
                   ex.printStackTrace();
                   System.out.println("Aucun ID genere");
               }
           }

       } catch (SQLException ex) {

           ex.printStackTrace();
           System.out.println("Il y a un probleme lors de l'insertion");

       }

    }

    public static void UpdateBook(Book book){

    }


}
package application;


import db.DB;
import db.DBException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class transactions {

    public static void main(String[] args){
        // Transações são atômicas: ou executa tudo, ou não executada nada. Para implementar isso, usar o setAutoCommit(false) e só fazer o commit() depois de tudo feito.
        // Além disso, é importante chamar o rollback() em caso de erros
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Connection conn = null;
        Statement st = null;

        try{
            conn = DB.getConnection();

            conn.setAutoCommit(false); // Ou seja, as ações no banco de dados não serão confirmadas de cara, elas aguardarão uma confirmação explícita do programador

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            // Simulando um erro no meio do caminho
//            int x = 1;
//            if (x < 2){
//                throw new SQLException("Fake error");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();// Só depois de tentar executar tudo o que precisa no banco de dados é que as ações feitas serão confirmadas

            System.out.println("rows1 " + rows1);
            System.out.println("rows2 " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DBException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DBException("Error trying to rollback! Caused by: " + ex.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }

}

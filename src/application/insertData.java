package application;


import db.DB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class insertData {

    public static void main(String[] args){
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            // Cria a query para inserção de dados, e adiciona a opção de retornar os IDs dos registros gerados (argumento Statement.RETURN_GENERATED_KEYS)
            st = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS); // O número de ? deve sempre ser igual ao número de campos informado na query

            // O set escolhido deve sempre estar de acordo com o tipo de dados esperado pelo campo do banco de dados
            st.setString(1, "Carl Purple");// O primeiro parâmetro indica qual dos ? da query deve ser substituído pelo valor informado
            st.setString(2, "carl@gmail.com");
            st.setDate(3, java.sql.Date.valueOf(LocalDate.parse("22/04/1985", dtFormatter))); // Para informar datas, devemos usar java.sql.Date, e não java.util.Date!
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            }
            else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }

}

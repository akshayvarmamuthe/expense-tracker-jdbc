import java.sql.*;
public class UserOperations{
    private final String url="jdbc:postgresql://localhost:5432/JDBCproject";
    private final String username="postgres";
    private final String  password="akshay";
    public void addExpense(double amount,String category) {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into expenses(amount,category) values(?,?)"
            );
            ps.setDouble(1, amount);
            ps.setString(2, category);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            System.out.println(e.getMessage());
        }
    }
    public void viewAllExpenses() {
        try(Connection con=DriverManager.getConnection(url,username,password)){
            PreparedStatement ps=con.prepareStatement("select id,category,amount from expenses");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.printf("%-5d %-15s %-10.2f%n",
                        rs.getInt("id"),rs.getString("category"),rs.getDouble("amount"));
            }
        }
        catch (SQLException e){
            System.out.println("Connection Failed");
        }
    }
    public int deleteExpense(int id) {
        try(Connection con=DriverManager.getConnection(url,username,password)){
            PreparedStatement ps=con.prepareStatement(
                    "delete from expenses where id=?"
            );
            ps.setInt(1,id);
            int row=ps.executeUpdate();
            return row;
        }
        catch (SQLException e){
            System.out.println("Connection Failed");
        }
        return 0;
    }
    public int updateExpense(int id, double amount, String category) {
        try(Connection con=DriverManager.getConnection(url,username,password)){
            PreparedStatement ps=con.prepareStatement(
                    "update expenses set category=?,amount=? where id=?"
            );
            ps.setString(1,category);
            ps.setDouble(2,amount);
            ps.setInt(3,id);
            int row=ps.executeUpdate();
            return row;
        }
        catch (SQLException e){
            System.out.println("Connection Failed");
        }
        return 0;
    }
    public double totalSpendings() {
        try(Connection con=DriverManager.getConnection(url,username,password)){
            PreparedStatement ps=con.prepareStatement(
                    "select SUM(amount) from expenses"
            );
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return rs.getDouble(1);
            }
        }
        catch (SQLException e){
            System.out.println("Connection Failed");
        }
        return 0.0;
    }
    public double getCategorySpending(String category) {
        try(Connection con=DriverManager.getConnection(url,username,password)){
            PreparedStatement ps=con.prepareStatement(
                    "select sum(amount) from expenses where lower(category)=lower(?)"
            );
            ps.setString(1,category);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return rs.getDouble(1);
            }
        }
        catch (SQLException e){
            System.out.println("Connection Failed");
        }
        return 0.0;
    }
}

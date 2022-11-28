package tk.thekillerregs.tkspigot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {
    private TkSpigot tkSpigot;


    private UUID uuid;
    private String rank;
    private int coins;

    public CustomPlayer(TkSpigot tkSpigot, UUID uuid) throws SQLException
    {
        this.uuid=uuid;

        try( Connection connection = tkSpigot.getDb().getHikari().getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT RANK, COINS FROM players WHERE UUID = ?;");
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                rank = rs.getString("RANK");
                coins = rs.getInt("COINS");
            }
            else{
                rank = "GUEST";
                coins = 0;
                PreparedStatement insert = connection.prepareStatement(
                        "INSERT INTO players (ID, UUID, RANK, COINS) " +
                                "VALUES (default," +
                                "'"+ uuid + "'," +
                                "'" + rank +"'," + coins+");"
                );
                insert.executeUpdate();
            }
        } catch(SQLException e)
        {

        }


    }
/*
    public void setRank(String rank) {
        this.rank = rank;
        PreparedStatement st = null;
        try {
            st = tkSpigot.getDb().getConnection().prepareStatement("UPDATE players SET RANK =  '" + rank + "' WHERE UUID = '" + uuid +"';");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

       public void setCoins(int coins) {
        this.coins = coins;
        PreparedStatement st = null;
        try {
            st = tkSpigot.getDb().getConnection().prepareStatement("UPDATE players SET COINS = "+ coins+" WHERE UUID = '" + uuid +"';");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    public UUID getUuid() {
        return uuid;
    }

    public String getRank() {
        return rank;
    }

    public int getCoins() {
        return coins;
    }


}

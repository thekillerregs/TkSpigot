package tk.thekillerregs.tkspigot;

import java.util.Date;

public class Data {

    private String playerName;
    private boolean value;
    private Date date;

    public Data(String playerName, boolean value, Date date)
    {
        this.playerName=playerName;
        this.value=value;
        this.date=date;

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

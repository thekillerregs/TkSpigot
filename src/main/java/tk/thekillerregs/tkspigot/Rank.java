package tk.thekillerregs.tkspigot;

public enum Rank {

    ADMIN("&4&lADMIN ", 'a'), HELPER("&a&lHELPER ", 'b'), MEMBER("&e&lMEMBER", 'c');

    private String display;
    private char orderSymbol;

    Rank(String display, char orderSymbol) {
        this.display = display;
        this.orderSymbol=orderSymbol;
    }

    public String getDisplay() {return display;}
    public char getOrderSymbol(){return orderSymbol}
}

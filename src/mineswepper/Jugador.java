
package mineswepper;

public class Jugador {
    private String username;
    private String password;
    private int score;
    private int roundsWon;
    private int roundsLost;

    public Jugador(String username, String password, int score, int roundsWon, int roundsLost) {
        this.username = username;
        this.password = password;
        this.score = score;
        this.roundsWon = roundsWon;
        this.roundsLost = roundsLost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }

    public int getRoundsLost() {
        return roundsLost;
    }

    public void setRoundsLost(int roundsLost) {
        this.roundsLost = roundsLost;
    }
    
    
    
}

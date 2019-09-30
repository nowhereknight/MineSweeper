package mineswepper;

public class Casilla {
    private boolean hasMine=false;
    private boolean isPressed=false;
    private boolean hasFlag=false;
    private int i;
    private int j;
    private int value=0;

    public Casilla(){}
    
    public Casilla(int i, int j){
        this.i=i;
        this.j=j;
    }
    
    public Casilla(boolean hasMine) {
        this.hasMine = hasMine;
    }
    
    public boolean isPressed(){
        return this.isPressed;
    }
    
    public void press(){
        this.isPressed=true;
    }

    public boolean hasFlag(){
        return this.hasFlag;
    }
    
    public void setFlag(){
        this.hasFlag=true;
    }
    public void unsetFlag(){
        this.hasFlag=false;
    }
    public boolean hasMine() {
        return hasMine;
    }

    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public void incrementValue(){
        this.value+=1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}

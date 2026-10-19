public class Item {
    private String nombre;
    private Tipo tipo;
    private int ATK;
    private int DEF;
    private int HP;

    public Item(String nom, Tipo tipo, int atk, int def, int hp){
        this.nombre=nom;
        this.tipo=tipo;
        this.ATK=atk;
        this.DEF=def;
        this.HP=hp;
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getATK() {
        return ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public int getHP() {
        return HP;
    }

    //TOSTRING
    public String toString(){
        String aux="";
        aux+= this.nombre + " - tipo: " + this.tipo + " (ATK: " + this.ATK + ", DEF: " +this.DEF + ", HP: " + this.HP + ")";
        return aux;
    }

    public String mostrarInfo(){
        String aux="";
        aux+= this.nombre + " (ATK: " + this.ATK + ", DEF: " +this.DEF + ", HP: " + this.HP + ")";
        return aux;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setATK(int aTK) {
        ATK = aTK;
    }

    public void setDEF(int dEF) {
        DEF = dEF;
    }

    public void setHP(int hP) {
        HP = hP;
    }

    

}

public abstract class Personaje implements Comparable<Personaje>{
    protected String nombre;
    protected int ATK;
    protected int DEF;
    protected int HP;

    public Personaje(String nom, int atk, int def, int hp){
        this.nombre=nom;
        this.ATK= atk;
        this.DEF= def;
        this.HP= hp;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    //TOSTRING
    public String mostrarInfo(){
        String aux= "";
        aux+= "Nombre: " + this.nombre + " - Ataque: " + this.ATK + " - Defensa: " + this.DEF + " - HP: " + this.HP;
        return aux;
    }

    public String mostrarInfo2(){
        String aux= "";
        aux+= this.nombre + " - Ataque: " + this.ATK + " - Defensa: " + this.DEF + " - HP: " + this.HP;
        return aux;
    }

    //GETTERS
    public String getNombre() {
        return nombre;
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
    
    @Override
    public int compareTo(Personaje otroPersonaje){
        return this.nombre.compareTo(otroPersonaje.getNombre());
    }
    
}

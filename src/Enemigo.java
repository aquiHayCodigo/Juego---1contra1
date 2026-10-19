public class Enemigo extends Personaje{
    private int derrotas;
    private int oro;

    public Enemigo(String nom, int atk, int def, int hp, int oro) {
        super(nom, atk, def, hp);
        this.derrotas = 0;
        this.oro = oro;
    }

    //GETTERS

    public int getDerrotas() {
        return derrotas;
    }

    public int getOro() {
        return oro;
    }

    //TOSTRING
    @Override
    public String mostrarInfo(){
        String aux= super.mostrarInfo();
        aux+= ", Oro que entrega: " + this.oro + ", Derrotas: " + this.derrotas;
        return aux;
    }

    //SETTERS

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    /* @Override //La comparable ya está aplicada en la clase Personaje
    public int compareTo(Enemigo otroEnemigo){
        return this.nombre.compareTo(otroEnemigo.getNombre());
    }  */
}

import java.util.ArrayList;

public class Protagonista extends Personaje  {
    private int oro;
    private Item item;
    private ArrayList<Item> items;
    private int partidasGanadas;

    public Protagonista(String nom, int atk, int def, int hp) {
        super(nom, atk, def, hp);
        this.oro = 0;
        this.item = null;
        this.items =  new ArrayList<>();
        this.partidasGanadas=0;
    }

    //GETTERS

    public Item getItem() {
        return item;
    }

    public ArrayList<Item> getItems() {
        for(int i=0;i<items.size();i++){
            System.out.println("* " + items.get(i));
        }
        return items;
    }

    public void mostrarItemCurativo(){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getTipo()==Tipo.Curativa){
                System.out.println("- " + i + " " + items.get(i));
            }
        }
    }

    public int getPartidasGanas(){
        return partidasGanadas;
    }

    public void sumaPartida(){
        partidasGanadas++;
    }

    // TOSTRING
    @Override
    public String mostrarInfo() {
        String aux = super.mostrarInfo();
        aux += ", Oro: " + this.oro;
        return aux;
    }

    //SETTERS
    
    public void setOro(int oro) {
        this.oro = oro;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setpartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    /* @Override
    public int compareTo(Protagonista otroProta) {
        return this.nombre.compareTo(otroProta.getNombre());
    } */
}

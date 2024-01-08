public class GraSzachowa {
    private int liczbaTur;
    private boolean ruchWykonany;

    public GraSzachowa() {
        this.liczbaTur = 0;
        this.ruchWykonany = false;
    }

    public boolean wykonajTure() {
        if (ruchWykonany) {
            return false; // Jeśli ruch został już wykonany, nie wykonuj kolejnej tury
        }

        System.out.println("Tura: " + (++liczbaTur));
        // Tutaj możesz dodać logikę związaną z przebiegiem tury

        // Ustaw flagę, że ruch został wykonany
        ruchWykonany = true;

        return true;
    }

    // Dodaj metodę do resetowania flagi po zakończeniu tury
    public void resetujRuch() {
        ruchWykonany = false;
    }
}

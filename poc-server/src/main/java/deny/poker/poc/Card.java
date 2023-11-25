package deny.poker.poc;

public record Card(Color color, Figure figure) {
    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", figure1=" + figure +
                '}';
    }
}

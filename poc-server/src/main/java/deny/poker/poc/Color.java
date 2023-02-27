package deny.poker.poc;

public enum Color implements CardFeature {
    BLACK_SPADE(3),
    RED_HEART(2),
    RED_DIAMOND(1),
    BLACK_CLUB(0);

    Color(int value) {
        this.value = value;
    }

    private final int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean has(Card card) {
        return card.color().getValue() == value;
    }
}

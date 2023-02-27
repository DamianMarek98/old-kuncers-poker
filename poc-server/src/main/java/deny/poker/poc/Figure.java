package deny.poker.poc;

public enum Figure implements CardFeature {
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    Figure(int value) {
        this.value = value;
    }

    private final int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean has(Card card) {
        return card.figure().getValue() == value;
    }
}

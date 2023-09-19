package deny.poker.poc;

public enum SetRank {
    HIGH_CARD(1, "High card"),
    PAIR(2, "Pair"),
    TWO_PAIRS(3, "Two pairs"),
    STRAIGHT(4, "Straight"),
    THREE_OF_A_KIND(5, "Three of a kind"),
    FULL_HOUSE(6, "Full house"),
    FOUR_OF_A_KIND(7, "Four of a kind"),
    COLOR(8, "Color"),
    POKER(9, "Poker");

    private final int rank;
    private final String name;

    SetRank(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }
}

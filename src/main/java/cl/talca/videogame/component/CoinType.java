package cl.talca.videogame.component;

public enum CoinType {
    LIVE, POINTS_25, POINTS_50;

    public static CoinType get(int number) {
        switch(number) {
            case 0  : return LIVE;
            case 1 : return POINTS_25;
            case 2 : return POINTS_50;
        }
        return null;
    }
}

package cl.talca.videogame.resources;


import cl.talca.videogame.component.Coins;
import cl.talca.videogame.component.CoinType;

public class GameStatistics {

    private int liveCount = 3;
    private int points = 0;
    private int asteroidDestroyed = 0;

    public void destroyAircraft() {
        this.liveCount--;
    }

    public void destroyAsteroid() {
        this.asteroidDestroyed++;
        this.addPoint(10);
    }

    private void addPoint(int points) {
        this.points+=points;
        if(this.points % 100 == 0){
            this.liveCount++;
        }
    }

    public void addLive() {
        this.liveCount++;
    }

    public void processCoin(Coins coin) {

        if(coin.getType().equals(CoinType.LIVE)) {
            this.liveCount++;
        }else if(coin.getType().equals(CoinType.POINTS_25)){
            this.addPoint(25);
        }else{
            this.addPoint(50);
        }

    }

    public boolean hasLives(){
        return (this.liveCount > 0);
    }

    public int getLives(){
        return this.liveCount;
    }

    public int getScore(){
        return this.points;
    }





}

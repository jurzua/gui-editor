package cl.talca.videogame.resources;


public class GameStatistics {

    private int liveCount = 3;
    private int points = 0;
    private int asteroidDestroyed = 0;

    public void destroyAircraft() {
        this.liveCount--;
    }

    public void destroyAsteroid() {
        this.asteroidDestroyed++;
        this.points+=10;
        if(this.points % 100 == 0){
            this.liveCount++;
        }
    }

    public void lifeCoin() {
        this.liveCount++;
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

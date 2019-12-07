package cl.talca.videogame;

import cl.talca.videogame.component.*;
import cl.talca.videogame.resources.GameStatistics;
import cl.talca.videogame.resources.ResourcesManager;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class GamePanel extends JPanel implements ActionListener {
    //tontera
    static int SCREEN_WIDE = 600;
    static int SCREEN_HIGH = 400;

    ResourcesManager resourcesManager = new ResourcesManager();
    List<ShapeInterface> shapeList = new ArrayList<ShapeInterface>();
    private Aircraft aircraft;
    private GameStatistics gameStatistics;
    private StatisticsPanel statisticsPanel;

    private Timer timer = new Timer(5, this);

    /*
    * jurzua: esto no lo entiendo. Mi recomendación era usar un contandor de tipo "int" que se incremente cada vez
    * que el methodo "actionPerformed" es llamado por el timer.
    * El el mismo método "actionPerformed" tu puedes tener algo asi como if(coinCounter % 1000) {//crear un LifeCoin}
    *
    * Crear el CoinLife en el constructor no es una buena idea. Porque no queremos que apareza inmediatamente, si no luego de un tiempo
    * */
    int coinCounter = 0;
    double x = 0, y = 0, vx = 2, vy = 2;
    int asteroid = 8;


    public GamePanel(GameStatistics gameStatistics, StatisticsPanel statisticsPanel) {

        this.gameStatistics = gameStatistics;
        this.statisticsPanel = statisticsPanel;
        for(int index=0;index<asteroid;index++){
            shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,2),
                    this.resourcesManager.get(ResourcesManager.ASTEROID_IMG),this));
        }
        //I would like to modify the background so that when the game is loaded it has something like a spatial background
        setBackground(Color.LIGHT_GRAY);
        this.aircraft = new Aircraft(SCREEN_HIGH -10,
                this.resourcesManager.get(ResourcesManager.AIRCRAFT_IMG), this);
        shapeList.add(this.aircraft);
    }

    public void createBullet(){
        if(this.aircraft != null) {
            Point initialPosition = this.aircraft.getPosition();
            Bullet myBullet = new Bullet(initialPosition.x, initialPosition.y);
            shapeList.add(myBullet);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(ShapeInterface shape : shapeList) {
            shape.draw(g);
        }
        this.timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDE, SCREEN_HIGH);
    }

    //call with timer
    public void actionPerformed(ActionEvent e) {
        coinCounter ++;
        for(Bullet bullet : getBullets()){
            //each bullet compare with all asteroid's position
            for(Asteroid asteroid : getAsteroids()){
                if(bullet.collidesWith(asteroid)){
                    asteroid.destroyYourself();
                    this.gameStatistics.destroyAsteroid();
                    break;
                }
            }
        }

        //comparison of the aircraft with each asteroid
        if(this.aircraft != null && !this.aircraft.isInDestruction()) {
            for(Asteroid asteroid : getAsteroids()){
                    if (asteroid.collidesWith(aircraft)) {
                        aircraft.destroyYourself();
                        this.gameStatistics.destroyAircraft();
                        break;
                }
            }
        }

        /**
         * jurzua: excelente!!!!
         */
        //comparison of the aircraft with coin
        if(this.aircraft != null && !this.aircraft.isInDestruction()) {
            for(LifeCoin lifeCoin : getLifeCoin()){
                if (lifeCoin.collidesWith(aircraft)) {
                    lifeCoin.destroyYourself();
                    this.gameStatistics.lifeCoin();
                    break;
                }
            }
        }

        if(coinCounter % 1000 == 0){
            shapeList.add(new LifeCoin(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,2),
                    this.resourcesManager.get(ResourcesManager.LIFECOIN_IMG),this));
        }

        List<ShapeInterface> listToDelete = new ArrayList<ShapeInterface>();
        for(ShapeInterface shape : shapeList) {

            shape.updatePosition();
            if(!shape.isVisible()){
                listToDelete.add(shape);
            }
        }
        for(ShapeInterface shapeToDelete : listToDelete) {
            shapeList.remove(shapeToDelete);
            if(shapeToDelete instanceof Asteroid){
                shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathHelper.randomNumber(1,2),
                        this.resourcesManager.get(ResourcesManager.ASTEROID_IMG),this));
            } else if(shapeToDelete instanceof Aircraft) {
                if(this.gameStatistics.hasLives()) {
                    this.aircraft = new Aircraft(SCREEN_HIGH -10,
                            this.resourcesManager.get(ResourcesManager.AIRCRAFT_IMG), this);
                    shapeList.add(this.aircraft);
                } else {
                    this.aircraft = null;
                }
            }
        }
        this.statisticsPanel.update();
        this.repaint();
        //this.printState();
    }

    private void printState() {
        System.out.println(String.format("Asteroids = %d, Bullets = %d", this.getAsteroids().size(), this.getBullets().size()));
    }

    private List<Asteroid> getAsteroids(){
        List<Asteroid> list = new ArrayList<Asteroid>();
        for(ShapeInterface shape : this.shapeList){
            if(shape instanceof Asteroid){
                list.add((Asteroid) shape);
            }
        }
        return list;
    }

    private List<Bullet> getBullets() {
        List<Bullet> list = new ArrayList<Bullet>();
        for(ShapeInterface shape : this.shapeList) {
            if(shape instanceof Bullet) {
                list.add((Bullet)shape);
            }
        }
        return list;
    }

    private List<LifeCoin> getLifeCoin() {
        List<LifeCoin> list = new ArrayList<LifeCoin>();
        for(ShapeInterface shape : this.shapeList) {
            if(shape instanceof LifeCoin) {
                list.add((LifeCoin)shape);
            }
        }
        return list;
    }

    public void aircraftMoveRight() {
        if(this.aircraft != null){
            this.aircraft.moveRight();
        }
    }

    public void aircraftMoveLeft() {
        if(this.aircraft != null){
            this.aircraft.moveLeft();
        }
    }




}

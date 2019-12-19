package cl.talca.videogame;

import cl.talca.jdk8.FunctionBulletTitle;
import cl.talca.videogame.component.*;
import cl.talca.videogame.resources.GameStatistics;
import cl.talca.videogame.resources.ResourcesManager;
import cl.talca.videogame.utils.MathUtils;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamePanel extends JPanel implements ActionListener {
    //tontera
    static int SCREEN_WIDE = 600;
    static int SCREEN_HIGH = 400;

    ResourcesManager resourcesManager = new ResourcesManager();
    List<ShapeInterface> shapeList = new ArrayList<ShapeInterface>();
    private Aircraft aircraft;
    private GameStatistics gameStatistics;
    private StatisticsPanel statisticsPanel;

    private Timer timer = new Timer(7, this);
    int coinCounter = 0;
    double x = 0, y = 0, vx = 2, vy = 2;
    int asteroid = 5;


    public GamePanel(GameStatistics gameStatistics, StatisticsPanel statisticsPanel) {

        this.gameStatistics = gameStatistics;
        this.statisticsPanel = statisticsPanel;
        for(int index=0;index<asteroid;index++){
            shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathUtils.randomNumber(1,2),
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

        /*
        for(Bullet bullet : getBullets()){
            //each bullet compare with all asteroid's position
            for(Asteroid asteroid : getAsteroids()){
                if(bullet.collidesWith(asteroid)){
                    asteroid.destroyYourself();
                    this.gameStatistics.destroyAsteroid();
                    break;
                }
            }
        }*/

        getBullets().stream().forEach(bullet -> {
            getAsteroids().stream()
                    .filter(asteroid -> bullet.collidesWith(asteroid))
                    .findFirst()
                    .ifPresent(asteroid -> {
                        asteroid.destroyYourself();
                        this.gameStatistics.destroyAsteroid();
                    });
        });

        //comparison of the aircraft with each asteroid
        getAsteroids().stream()
                .filter(aircraft -> this.aircraft != null && !this.aircraft.isInDestruction())
                .filter(asteroid -> asteroid.collidesWith(aircraft))
                .findFirst()
                .ifPresent(asteroid -> {
                    aircraft.destroyYourself();
                    this.gameStatistics.destroyAircraft();
                });

        //comparison of the aircraft with coin
        getCoin().stream()
                .filter(aircraft -> aircraft != null && !this.aircraft.isInDestruction())
                .filter(coin -> coin.collidesWith(aircraft))
                .findFirst()
                .ifPresent(coin -> {
                    coin.destroyYourself();
                    this.gameStatistics.processCoin(coin);
                });

        if(coinCounter % 1000 == 0){
            CoinType coinType = CoinType.get(MathUtils.randomNumber(0, 2));
            shapeList.add(new Coins(coinType, SCREEN_WIDE, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                    this.resourcesManager.get(coinType.toString()),this));
        }


        /*List<ShapeInterface> listToDelete = shapeList.stream().forEach(shape -> {
            shapeList.stream()
            .forEach(shape -> shape.updatePosition())
            .filter(shape -> !shape.isVisible())

                });*/


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
                shapeList.add(new Asteroid(SCREEN_WIDE, SCREEN_HIGH, MathUtils.randomNumber(1,2),
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

        return this.shapeList.stream()
                .filter(Asteroid.class::isInstance)
                .map(Asteroid.class::cast)
                .collect(Collectors.toList());
    }

    private List<Bullet> getBullets() {

        return this.shapeList.stream()
                //.filter(shapeInterface -> shapeInterface instanceof Bullet)
                .filter(Bullet.class::isInstance)
                //.map(shapeInterface -> (Bullet)shapeInterface)
                .map(Bullet.class::cast)
                .collect(Collectors.toList());
        /*
        List<Bullet> list = new ArrayList<Bullet>();
        for(ShapeInterface shape : this.shapeList) {
            if(shape instanceof Bullet) {
                list.add((Bullet)shape);
            }
        }
        return list;
        */
    }

    private List<Coins> getCoin() {
        return this.shapeList.stream()
                .filter(Coins.class::isInstance)
                .map(Coins.class::cast)
                .collect(Collectors.toList());
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

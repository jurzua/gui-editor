package cl.talca.videogame;

import cl.talca.videogame.component.*;
import cl.talca.videogame.resources.GameStatistics;
import cl.talca.videogame.resources.ResourcesManager;
import cl.talca.videogame.utils.MathUtils;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamePanel extends JPanel implements ActionListener {

    static int SCREEN_WIDTH = 800;
    static int SCREEN_HIGH = 400;

    ResourcesManager resourcesManager = new ResourcesManager();
    List<ShapeInterface> shapeList = new ArrayList<ShapeInterface>();
    private Aircraft aircraft;
    private GameStatistics gameStatistics;
    private StatisticsPanel statisticsPanel;

    private Timer timer = new Timer(7, this);
    int coinCounter = 0;
    int asteroidAmount = 5;
    int starAmount = 30;

    public GamePanel(GameStatistics gameStatistics, StatisticsPanel statisticsPanel) throws IOException {

        this.gameStatistics = gameStatistics;
        this.statisticsPanel = statisticsPanel;
        //I would like to modify the background so that when the game is loaded it has something like a spatial background
        setBackground(Color.BLACK);
        for(int index = 0; index< starAmount; index++){
            shapeList.add(new Star(SCREEN_WIDTH, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                    this.resourcesManager.get(ResourcesManager.STAR_IMG),this, false));
        }
        for(int index = 0; index< asteroidAmount; index++){
            shapeList.add(new Asteroid(SCREEN_WIDTH, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                    this.resourcesManager.get(ResourcesManager.ASTEROID_IMG),this));
        }
        this.aircraft = new Aircraft(SCREEN_HIGH -10,
                this.resourcesManager.get(ResourcesManager.AIRCRAFT_IMG), this);
        shapeList.add(this.aircraft);
    }
/*
    private void drawBackground(Graphics2D g2) {
        g2.drawImage(resourcesManager.get(ResourcesManager.BACKGROUND_IMG), 0, 0, null);
        g2.setColor(Color.RED);
        g2.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HIGH);

    }*/

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
        //this.drawBackground(g2);
        for(ShapeInterface shape : shapeList) {
            shape.draw(g);
        }
        this.timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HIGH);
    }

    //call with timer
    public void actionPerformed(ActionEvent e) {
        coinCounter ++;


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
        if(this.aircraft != null && !this.aircraft.isInDestruction()) {
            getAsteroids().stream()
                    .filter(asteroid -> asteroid.collidesWith(aircraft))
                    .findFirst()
                    .ifPresent(asteroid -> {
                        aircraft.destroyYourself();
                        this.gameStatistics.destroyAircraft();
                    });

            getCoin().stream()
                    .filter(coin -> !coin.isInDestruction()  && coin.collidesWith(aircraft))
                    .findFirst()
                    .ifPresent(coin -> {
                        coin.destroyYourself();
                        this.gameStatistics.processCoin(coin);
                    });
        }

        if(coinCounter % 1000 == 0){
            CoinType coinType = CoinType.get(MathUtils.randomNumber(0, 2));
            shapeList.add(new Coins(coinType, SCREEN_WIDTH, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                    this.resourcesManager.get(coinType.toString()),this));
        }

        List<ShapeInterface> listToDelete = shapeList.stream()
                .peek(shapeInterface -> shapeInterface.updatePosition())
                .filter(shapeInterface -> !shapeInterface.isVisible())
                .collect(Collectors.toList());

        for(ShapeInterface shapeToDelete : listToDelete) {
            shapeList.remove(shapeToDelete);
            if(shapeToDelete instanceof Asteroid){
                shapeList.add(new Asteroid(SCREEN_WIDTH, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                        this.resourcesManager.get(ResourcesManager.ASTEROID_IMG),this));
            } else if(shapeToDelete instanceof Star){
                shapeList.add(new Star(SCREEN_WIDTH, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                        this.resourcesManager.get(ResourcesManager.STAR_IMG),this, true));
            } else if(shapeToDelete instanceof Star){
                shapeList.add(new Star(SCREEN_WIDTH, SCREEN_HIGH, MathUtils.randomNumber(1,2),
                        this.resourcesManager.get(ResourcesManager.STAR_IMG),this, true));
            }else if(shapeToDelete instanceof Aircraft) {
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
                .filter(Bullet.class::isInstance)
                .map(Bullet.class::cast)
                .collect(Collectors.toList());
    }

    private List<Coins> getCoin() {
        return this.shapeList.stream()
                .filter(Coins.class::isInstance)
                .map(Coins.class::cast)
                .collect(Collectors.toList());
    }

    private List<Star> getStarAmount() {
        return this.shapeList.stream()
                .filter(Star.class::isInstance)
                .map(Star.class::cast)
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

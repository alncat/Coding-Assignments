package controller;

import java.awt.EventQueue;

import adapters.IViewToModel;
import view.View;
import model.Model;

public class Controller {

    private Model model;
    private View view;
    
    /**
     * Constructor that builds the system.
     */
    public Controller() {
        model = new Model();
        view = new View(new IViewToModel() {
         
            @Override
            public void loadFile(String fileName) {
                model.loadFile(fileName);
            }
            
            @Override
            public String parseFile() {
                return model.parseFile();
            }
            
            @Override
            public void playMusic() {
                model.playMusic();
            }
            
        });
    }
    
    /**
     * Starts the system.
     */
    public void start() {
        model.start();
        view.start();
    }
    
    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Controller controller = new Controller();
                    controller.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}

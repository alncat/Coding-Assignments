package model;

import adapters.IModelToView;
import model.visitors.PlaySongVisitor;
import model.visitors.ToStringVisitor;
import provided.abcParser.ABCParser;
import provided.music.IPhrase;
import provided.music.NESeqList;
import provided.util.ABCUtil;

/**
 * The Model for this system.
 */
public class Model {
    
    @SuppressWarnings("unused")
    private IModelToView modelToView; // adapter to the view, does not do anything but can be modified in future if necessary (remove unused warning suppression if so)
    
    private ABCParser abcParser; // parses inputted music files
    private IPhrase phrase; // represents the music file loaded

    /**
     * Constructor
     */
    public Model(IModelToView modelToView) {
        this.modelToView = modelToView;
    }
    
    /**
     * Loads file and returns file contents.
     * 
     * @param fileName
     * @return contents of file
     */
    public String loadFile(String fileName) {
        abcParser = new ABCParser("/songs/" + fileName + ".abc");
        return ABCUtil.Singleton.getFileContents("/songs/" + fileName + ".abc");
    }
    
    /**
     * Parses IPhrase and returns its String representation.
     * 
     * @return string representation of phrase
     */
    public String parseFile() {
        phrase = abcParser.parse();
        return phrase.toString();
    }
    
    /**
     * Calls the PlaySongVisitor to play the music encoded in phrase using SequencePlayer.
     */
    public void playMusic() {
        phrase.execute(new PlaySongVisitor());
    }
    
    /**
     * Starts the model by installing a ToStringAlgo to NESeqList.
     */
    public void start() {
        NESeqList.setToStringAlgo(new ToStringVisitor());
    }
    
}

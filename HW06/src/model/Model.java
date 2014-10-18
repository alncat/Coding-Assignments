package model;

import model.visitors.PlaySongVisitor;
import model.visitors.ToStringVisitor;
import provided.abcParser.ABCParser;
import provided.music.IPhrase;
import provided.music.NESeqList;
import provided.music.Note;
import provided.player.ISequencePlayerStatus;
import provided.player.SequencePlayer;

public class Model {
    
    private IPhrase phrase;

    /**
     * Constructor
     */
    public Model() {
        
    }
    
    /**
     * Loads 
     * @param fileName
     */
    public void loadFile(String fileName) {
       phrase = new ABCParser("/songs/" + fileName + ".abc").parse();
    }
    
    public String parseFile() {
       return phrase.toString();
    }
    
    public void playMusic() {
        phrase = new Note('G', 0, 0, 1.0, false);
        SequencePlayer sequencePlayer = (SequencePlayer) phrase.execute(new PlaySongVisitor());
        sequencePlayer.play(ISequencePlayerStatus.NULL);
    }
    
    public void start() {
        NESeqList.setToStringAlgo(new ToStringVisitor());
    }
    
}

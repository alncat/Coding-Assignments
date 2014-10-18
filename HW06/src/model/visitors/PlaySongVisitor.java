package model.visitors;

import provided.music.APhraseVisitor;
import provided.music.IPhrase;
import provided.music.IPhraseVisitorCmd;
import provided.music.Note;
import provided.player.SequencePlayer;

public class PlaySongVisitor extends APhraseVisitor {
    
    private SequencePlayer sequencePlayer = new SequencePlayer(16, 0);
    
    /**
     * Constructor
     */
    public PlaySongVisitor() {
        // default
        super(new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
                // TODO Auto-generated method stub
                return null;
            } 
        });
        
        // note
        this.addCmd("Note", new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
                sequencePlayer.addNote((Note) host, 0);
                return sequencePlayer;
            }
        });
    }
    
}

package model.visitors;

import provided.music.APhraseVisitor;
import provided.music.IPhrase;
import provided.music.IPhraseVisitor;
import provided.music.IPhraseVisitorCmd;
import provided.music.MTSeqList;
import provided.music.NESeqList;

/**
 * Visitor capable of parsing IPhrase host for its String representation.
 */
public class ToStringVisitor extends APhraseVisitor {
    
    private IPhraseVisitor toStringVisitor = this; // self-referential variable for recursive use
    
    /**
     * Constructor - loads a default command and commands for empty and non-empty sequence lists
     */
    public ToStringVisitor() {
        /*
         * Default
         */
        super(new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
                return host.toString();
            }
        });
        
        /*
         * Empty sequence list
         */
        this.addCmd(MTSeqList.ID, new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
               return "}";
            }
        });
        
        /*
         * Non-empty sequence list
         */
        this.addCmd(NESeqList.ID, new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
                String s = (String) params[0];
                NESeqList neSeqList = (NESeqList) host;
                return s + (String) neSeqList.getRest().execute(toStringVisitor, ", " + neSeqList.getFirst().toString());
            }
        });
    }
    
}

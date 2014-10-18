package model.visitors;

import provided.music.APhraseVisitor;
import provided.music.IPhrase;
import provided.music.IPhraseVisitor;
import provided.music.IPhraseVisitorCmd;
import provided.music.NESeqList;

public class ToStringVisitor extends APhraseVisitor {
    
    /**
     * Constructor - loads a default command and commands for empty and non-empty sequence lists
     */
    public ToStringVisitor() {
        // default
        super(new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
                return host.toString();
            }
        });
        
        IPhraseVisitor toStringVisitor = this;
        
        // empty sequence
        this.addCmd("MTSeqList", new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
               return "}";
            }
        });
        
        // non empty sequence
        this.addCmd("NESeqList", new IPhraseVisitorCmd() {
            @Override
            public Object apply(String id, IPhrase host, Object... params) {
                String s = (String) params[0];
                NESeqList neSeqList = (NESeqList) host;
                System.out.println("JESVIN " + neSeqList.getFirst().toString());
                return s + (String) neSeqList.getRest().execute(toStringVisitor, ", " + neSeqList.getFirst().toString());
            }
        });
    }
    
}

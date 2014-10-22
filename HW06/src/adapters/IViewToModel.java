package adapters;

/**
 * The View to Model adapter interface.
 */
public interface IViewToModel {
    
    /**
     * Loads user-specified music file.
     * 
     * @param fileName
     * @return contents of file
     */
    public String loadFile(String fileName);

    /**
     * Parses previously inputted music file to retrieve its string representation.
     */
    public String parseFile();
    
    /**
     * Plays music from previously inputted music file.
     */
    public void playMusic();
    
}
